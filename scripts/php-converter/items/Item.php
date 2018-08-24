<?php


class Item
{
    private $itemName;
    private $minecraftItemName;
    private $protocolID;

    /**
     * Item constructor.
     * @param string $minecraftItemName
     * @param array $data
     */
    public function __construct(string $minecraftItemName, array $data)
    {
        $this->minecraftItemName = $minecraftItemName;
        $this->itemName = explode(':', $minecraftItemName)[1];
        $this->protocolID = $data['protocol_id'];
    }

    /**
     * @return mixed
     */
    public function getItemName()
    {
        return $this->itemName;
    }

    /**
     * @return string
     */
    public function getMinecraftItemName(): string
    {
        return $this->minecraftItemName;
    }

    /**
     * @return mixed
     */
    public function getProtocolID()
    {
        return $this->protocolID;
    }

    public function getItemEnumName(): string
    {
        return strtoupper($this->itemName);
    }

    public function __toString()
    {
        return '    ' . $this->getItemEnumName() . '("' . $this->itemName . '")';
    }
}