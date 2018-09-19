<?php

// You can get the packets.md with the following command
// wget https://raw.githubusercontent.com/NiclasOlofsson/MiNET/master/src/MiNET/MiNET/Net/MCPE%20Protocol%20Documentation.md -O packets.md
$fullMarkdown = file_get_contents('packets.md');

const PACKET_SPLIT = '-----------------------------------------------------------------------';
const PACKET_NAME_RE = '/### (.*) \((0x[a-zA-Z0-9]{2,2})\)/m';
const PACKET_FIELDS_SPLIT = '#### Fields';
const PACKET_BOUND_RE = '/\*\*Sent from server:\*\* (true|false).*
\*\*Sent from client:\*\* (true|false)/m';

const TYPE_REPLACE_MAPPING = [
    'string' => 'String',
];

function camelCase($str, array $noStrip = [])
{
    // non-alpha and non-numeric characters become spaces
    $str = preg_replace('/[^a-z0-9' . implode("", $noStrip) . ']+/i', ' ', $str);
    $str = trim($str);
    // uppercase the first character of each word
    $str = ucwords($str);
    $str = str_replace(" ", "", $str);
    $str = lcfirst($str);

    return $str;
}

function mapType($type)
{
    return TYPE_REPLACE_MAPPING[$type] ?? $type;
}

$packetsMarkdown = explode('## Packets', $fullMarkdown)[1];
$packets = explode(PACKET_SPLIT, $packetsMarkdown);

$packetArray = [];
foreach ($packets as $packet) {
    if (empty(trim($packet))) {
        continue;
    }

    preg_match_all(PACKET_NAME_RE, $packet, $matches, PREG_SET_ORDER, 0);

    [, $packetName, $packetID] = $matches[0];
    unset($matches);

    [$otherData, $fieldData] = explode(PACKET_FIELDS_SPLIT, $packet);

    $fieldData = trim($fieldData);
    $fieldDataArray = explode("\n", $fieldData);

    $fields = [];
    $keys = [];
    $values = [];
    foreach ($fieldDataArray as $i => $row) {
        if ($i === 0) {
            $keys = array_values(array_filter(array_map('trim', explode('|', $row)), function ($input) {
                return !empty($input);
            }));

            continue;
        }

        if (count($fieldDataArray) === 2) {
            foreach ($keys as $iKey => $value) {
                $values[$iKey][] = null;
            }
        }

        if ($i === 1) {
            continue;
        }

        $rowValues = array_values(array_filter(array_map('trim', explode('|', $row)), function ($input) {
            return !empty($input);
        }));

        $fieldRow = [];
        for ($a = 0, $aMax = count($keys); $a < $aMax; $a++) {
            $fieldRow[$keys[$a]] = $rowValues[$a] ?? null;
        }

        $fields[] = $fieldRow;
    }

    preg_match_all(PACKET_BOUND_RE, $packet, $matches, PREG_SET_ORDER, 0);

    [, $outbound, $inbound] = $matches[0];
    unset($matches);

    $packetArray[] = [
        'name' => $packetName,
        'id' => $packetID,
        'fields' => $fields,
        'outbound' => $outbound === 'true',
        'inbound' => $inbound === 'true',
    ];
}

foreach ($packetArray as $i => $packet) {
    $className = str_replace(' ', '', $packet['name']) . 'Packet';
    $fileName = $className . '.java';

    $packageName = 'rocks.cleanstone.net.mcpe.packet.' . ($packet['inbound'] === true ? 'inbound' : 'outbound');

    $classContent = 'package ' . $packageName . ';

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.ClientStatus;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ' . $className . ' implements Packet {

';

    foreach ($packet['fields'] as $field) {
        $classContent .= '    private final ' . mapType($field['Type']) . ' ' . camelCase($field['Name']) . ";\n";
    }

    $classContent .= '
    public ' . $className . '(' . implode(', ', array_map(function ($field) {
            return mapType($field['Type']) . ' ' . camelCase($field['Name']);
        }, $packet['fields'])) . ') {
' . implode("\n", array_map(function ($field) {
            return '        ' . 'this.' . camelCase($field['Name']) . ' = ' . ' ' . camelCase($field['Name']) . ';';
        }, $packet['fields'])) . '
    }

' . implode("\n", array_map(function ($field) {
            return '    public ' . mapType($field['Type']) . ' ' . camelCase('get' . $field['Name']) . '() {
        return ' . camelCase($field['Name']) . ';
    }
';
        }, $packet['fields'])) . '
    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CLIENT_STATUS;
    }
}

';

    if (!is_dir('packets') && !mkdir('packets') && !is_dir('packets')) {
        throw new \RuntimeException(sprintf('Directory "%s" was not created', 'packets'));
    }
    if (!is_dir('packets/inbound') && !mkdir('packets/inbound') && !is_dir('packets/inbound')) {
        throw new \RuntimeException(sprintf('Directory "%s" was not created', 'packets/inbound'));
    }
    if (!is_dir('packets/outbound') && !mkdir('packets/outbound') && !is_dir('packets/outbound')) {
        throw new \RuntimeException(sprintf('Directory "%s" was not created', 'packets/outbound'));
    }

    file_put_contents('packets/' . ($packet['inbound'] === true ? 'inbound' : 'outbound') . '/' . $fileName, $classContent);
}

$inboundPacketType = 'package rocks.cleanstone.net.mcpe.packet;

import rocks.cleanstone.net.mcpe.packet.outbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

import javax.annotation.Nullable;

public enum MCPEInboundPacketType implements PacketType {
' . implode(",\n", array_map(function ($packet) {
        return '    ' .  strtoupper(str_replace(' ', '_', $packet['name'])) .'(' . str_replace(' ', '', $packet['name']) . '.class' . ')';
    }, array_filter($packetArray, function ($packet) {
        return $packet['inbound'] === true;
    }))) . ';

    private final Class<? extends Packet> packetClass;

    MinecraftInboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    @Nullable
    public static MCPEInboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (MCPEInboundPacketType type : values()) {
            if (type.getPacketClass() == packetClass) return type;
        }
        return null;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
';


file_put_contents('packets/MCPEOutboundPacketType.java', $inboundPacketType);

$outboundPacketType = 'package rocks.cleanstone.net.mcpe.packet;

import rocks.cleanstone.net.mcpe.packet.outbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

import javax.annotation.Nullable;

public enum MCPEOutboundPacketType implements PacketType {
' . implode(",\n", array_map(function ($packet) {
        return '    ' .  strtoupper(str_replace(' ', '_', $packet['name'])) .'(' . str_replace(' ', '', $packet['name']) . '.class' . ')';
    }, array_filter($packetArray, function ($packet) {
        return $packet['outbound'] === true;
    }))) . ';

    private final Class<? extends Packet> packetClass;

    MinecraftOutboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    @Nullable
    public static MCPEOutboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (MCPEOutboundPacketType type : values()) {
            if (type.getPacketClass() == packetClass) return type;
        }
        return null;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
';


file_put_contents('packets/MCPEOutboundPacketType.java', $outboundPacketType);

//file_put_contents('test.php', "<?php\n" . var_export($packetArray, true));