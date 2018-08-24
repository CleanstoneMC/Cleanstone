<?php


class Item
{
    const REPLACE = [
        ' ' => '_',
        '_WOOD_PLANK' => '_PLANKS',
        'LAPIS_LAZULI_' => 'LAPIS_',
        '(' => '',
        ')' => '',
        '\'' => '_',
        '13_DISC' => 'MUSIC_DISC_13',
        'CAT_DISC' => 'MUSIC_DISC_CAT',
        'BLOCKS_DISC' => 'MUSIC_DISC_BLOCKS',
        'CHIRP_DISC' => 'MUSIC_DISC_CHIRP',
        'FAR_DISC' => 'MUSIC_DISC_FAR',
        'MALL_DISC' => 'MUSIC_DISC_MALL',
        'MELLOHI_DISC' => 'MUSIC_DISC_MELLOHI',
        'STAL_DISC' => 'MUSIC_DISC_STAL',
        'STRAD_DISC' => 'MUSIC_DISC_STRAD',
        'WARD_DISC' => 'MUSIC_DISC_WARD',
        '11_DISC' => 'MUSIC_DISC_11',
        'WAIT_DISC' => 'MUSIC_DISC_WAIT',
        'DRAGON_S_BREATH' => 'DRAGON_BREATH',
        'RABBIT_S_FOOT' => 'RABBIT_FOOT',
        'BOTTLE_O__ENCHANTING' => 'EXPERIENCE_BOTTLE',
    ];

    /**
     * Item constructor.
     */
    public function __construct(array $data)
    {
        $this->minecraftItemName = $data['minecraftID'];
        $this->englishName = $data['name'];
        $this->itemName = explode(':', $this->minecraftItemName)[1];
        $this->protocolID = $data['id'];
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

    /**
     * @return mixed
     */
    public function getEnglishName()
    {
        return $this->englishName;
    }

    public function getItemEnumName()
    {
        $name = strtoupper($this->englishName);
        $name = str_replace(array_keys(self::REPLACE), array_values(self::REPLACE), $name);
        return $name;
    }

    public function __toString()
    {
        return '    ' . $this->getItemEnumName() . '("' . $this->itemName . '")';
    }
}