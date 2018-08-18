<?php


class Property
{
    const BLOCK_NAME_ENUM_OVERRIDE = [
        'powered_rail' => ['shape' => 'RailShape'],
        'detector_rail' => ['shape' => 'RailShape'],
        'rail' => ['shape' => 'RailShape'],
        'activator_rail' => ['shape' => 'RailShape'],

        'moving_piston' => ['type' => 'PistonType'],
        'piston_head' => ['type' => 'PistonType'],

        'white_bed' => ['part' => 'BedPart'],
        'orange_bed' => ['part' => 'BedPart'],
        'magenta_bed' => ['part' => 'BedPart'],
        'light_blue_bed' => ['part' => 'BedPart'],
        'yellow_bed' => ['part' => 'BedPart'],
        'lime_bed' => ['part' => 'BedPart'],
        'pink_bed' => ['part' => 'BedPart'],
        'gray_bed' => ['part' => 'BedPart'],
        'light_gray_bed' => ['part' => 'BedPart'],
        'cyan_bed' => ['part' => 'BedPart'],
        'purple_bed' => ['part' => 'BedPart'],
        'blue_bed' => ['part' => 'BedPart'],
        'brown_bed' => ['part' => 'BedPart'],
        'green_bed' => ['part' => 'BedPart'],
        'red_bed' => ['part' => 'BedPart'],
        'black_bed' => ['part' => 'BedPart'],

        'chest' => ['type' => 'ChestType'],
        'trapped_chest' => ['type' => 'ChestType'],

        'oak_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],
        'iron_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],

        'oak_trapdoor' => ['half' => 'HalfBlockPosition'],
        'spruce_trapdoor' => ['half' => 'HalfBlockPosition'],
        'birch_trapdoor' => ['half' => 'HalfBlockPosition'],
        'jungle_trapdoor' => ['half' => 'HalfBlockPosition'],
        'acacia_trapdoor' => ['half' => 'HalfBlockPosition'],
        'dark_oak_trapdoor' => ['half' => 'HalfBlockPosition'],
        'iron_trapdoor' => ['half' => 'HalfBlockPosition'],

        'brick_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'cobblestone_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'stone_brick_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'nether_brick_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'sandstone_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'spruce_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'birch_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'jungle_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'quartz_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'acacia_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'dark_oak_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'oak_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'prismarine_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'prismarine_brick_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'dark_prismarine_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'red_sandstone_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],
        'purpur_stairs' => ['half' => 'StairHalf', 'shape' => 'StairShape'],

        'tall_seagrass' => ['half' => 'BlockHalf'],
        'sunflower' => ['half' => 'BlockHalf'],
        'lilac' => ['half' => 'BlockHalf'],
        'rose_bush' => ['half' => 'BlockHalf'],
        'peony' => ['half' => 'BlockHalf'],
        'tall_grass' => ['half' => 'BlockHalf'],
        'large_fern' => ['half' => 'BlockHalf'],

        'spruce_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],
        'birch_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],
        'jungle_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],
        'acacia_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],
        'dark_oak_door' => ['half' => 'BlockHalf', 'hinge' => 'HingePosition'],

        'comparator' => ['mode' => 'ComparatorMode'],
        'redstone_wire' => ['east' => 'RedstonePosition', 'north' => 'RedstonePosition', 'south' => 'RedstonePosition', 'west' => 'RedstonePosition'],
        'structure_block' => ['mode' => 'StructureBlockMode'],

        'prismarine_slab' => ['type' => 'HalfBlockPosition'],
        'prismarine_brick_slab' => ['type' => 'HalfBlockPosition'],
        'dark_prismarine_slab' => ['type' => 'HalfBlockPosition'],
        'oak_slab' => ['type' => 'HalfBlockPosition'],
        'spruce_slab' => ['type' => 'HalfBlockPosition'],
        'birch_slab' => ['type' => 'HalfBlockPosition'],
        'jungle_slab' => ['type' => 'HalfBlockPosition'],
        'acacia_slab' => ['type' => 'HalfBlockPosition'],
        'dark_oak_slab' => ['type' => 'HalfBlockPosition'],
        'stone_slab' => ['type' => 'HalfBlockPosition'],
        'sandstone_slab' => ['type' => 'HalfBlockPosition'],
        'petrified_oak_slab' => ['type' => 'HalfBlockPosition'],
        'cobblestone_slab' => ['type' => 'HalfBlockPosition'],
        'brick_slab' => ['type' => 'HalfBlockPosition'],
        'stone_brick_slab' => ['type' => 'HalfBlockPosition'],
        'nether_brick_slab' => ['type' => 'HalfBlockPosition'],
        'quartz_slab' => ['type' => 'HalfBlockPosition'],
        'red_sandstone_slab' => ['type' => 'HalfBlockPosition'],
        'purpur_slab' => ['type' => 'HalfBlockPosition'],
    ];

    /**
     * @var string
     */
    private $propertyName;
    /**
     * @var array
     */
    private $values;
    /**
     * @var array
     */
    private $states;
    /**
     * @var string
     */
    private $blockName;

    /**
     * Block constructor.
     * @param string $propertyName
     * @param array $values
     * @param array $states
     */
    public function __construct(string $blockName, string $propertyName, array $values, array $states)
    {
        $this->blockName = $blockName;
        $this->propertyName = $propertyName;
        $this->values = $values;
        $this->states = $states;
    }

    private function isInteger(): bool
    {
        foreach ($this->values as $value) {
            if (!\is_numeric($value)) {
                return false;
            }
        }

        return true;
    }

    private function isBoolean(): bool
    {
        if (count($this->values) !== 2) {
            return false;
        }

        foreach ($this->values as $value) {
            if ($value !== 'true' && $value !== 'false' && $value !== '1' && $value !== '0') {
                return false;
            }
        }

        return true;
    }

    private function getPropertyTypeString(): string
    {
        if ($this->isInteger()) {
            return 'PropertyInteger';
        }

        if ($this->isBoolean()) {
            return 'PropertyBoolean';
        }

        return 'PropertyEnum<>';
    }

    private function getDefaultState(): array
    {
        foreach ($this->states as $state) {
            if (isset($state['default']) && $state['default'] === true) {
                return $state;
            }
        }

        throw new Exception('Could not found default State for ' . $this->propertyName);
    }

    private function getDefaultValue(): string
    {
        $defaultState = $this->getDefaultState();
        $defaultValue = $defaultState['properties'][$this->propertyName];

        if ($this->isInteger()) {
            return ', ' . $this->values[0] . ', ' . array_reverse($this->values)[0] . ', ' . $defaultValue;
        }

        if ($this->isBoolean()) {
            return ', ' . $defaultValue;
        }

        if (isset(self::BLOCK_NAME_ENUM_OVERRIDE[$this->blockName][$this->propertyName])) {
            return ', ' . ucfirst(self::BLOCK_NAME_ENUM_OVERRIDE[$this->blockName][$this->propertyName]) . '.' . strtoupper($defaultValue);
        }

        return ', ' . ucfirst($this->propertyName) . '.' . strtoupper($defaultValue);
    }

    public function __toString()
    {
        return 'new ' . $this->getPropertyTypeString() . '("' . $this->propertyName . '"' . $this->getDefaultValue() . ')';
    }
}