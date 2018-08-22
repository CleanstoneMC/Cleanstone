<?php


class Item
{


    /**
     * Item constructor.
     */
    public function __construct(string $minecraftItemName, array $data)
    {
        $this->minecraftItemName = $minecraftItemName;
        $this->itemName = explode(':', $minecraftItemName)[1];
        $this->protocolID = $data['protocol_id'];
    }

    public function __toString()
    {
        return '    ' . strtoupper($this->itemName) . '("' . $this->itemName . '")';
    }
}