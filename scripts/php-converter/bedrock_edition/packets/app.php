<?php

// You can get the packets.md with the following command
// wget https://raw.githubusercontent.com/NiclasOlofsson/MiNET/master/src/MiNET/MiNET/Net/MCPE%20Protocol%20Documentation.md -O packets.md
$fullMarkdown = file_get_contents('packets.md');

const PACKET_SPLIT = '-----------------------------------------------------------------------';
const PACKET_NAME_RE = '/### (.*) \((0x[a-zA-Z0-9]{2,2})\)/m';
const PACKET_FIELDS_SPLIT = '#### Fields';
const PACKET_BOUND_RE =  '/\*\*Sent from server:\*\* (true|false).*
\*\*Sent from client:\*\* (true|false)/m';

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

    $keys = [];
    $values = [];
    foreach ($fieldDataArray as $i => $row) {
        if ($i === 0) {
            $keys = array_filter(array_map('trim', explode('|', $row)), function ($input) {
                return !empty($input);
            });

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

        $rowValues = array_filter(array_map('trim', explode('|', $row)), function ($input) {
            return !empty($input);
        });

        foreach ($keys as $iKey => $value) {
            $values[$iKey][] = $rowValues[$iKey] ?? null;
        }
    }

    $fields = array_combine($keys, $values);

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

file_put_contents('test.php', "<?php\n" . var_export($packetArray, true));