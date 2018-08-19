<?php


class Block
{
    /**
     * @var string
     */
    private $minecraftBlockName;

    /**
     * @var string
     */
    private $blockName;

    /**
     * @var array
     */
    private $blockData;

    /**
     * @var array
     */
    private $properties = [];

    /** @var int */
    private $baseID;

    /**
     * Block constructor.
     * @param string $minecraftBlockName
     * @param array $blockData
     * @throws Exception
     */
    public function __construct(string $minecraftBlockName, array $blockData)
    {
        $this->minecraftBlockName = $minecraftBlockName;
        $this->blockName = explode(':', $minecraftBlockName)[1];
        $this->blockData = $blockData;

        if (isset($blockData['properties'])) {
            foreach ($blockData['properties'] as $propertyName => $propertyData) {
                $this->properties[] = new Property($this->blockName, $propertyName, $propertyData, $blockData['states']);
            }
        }

        $minID = null;
        foreach ($blockData['states'] as $state) {
            if ($minID === null || $minID > $state['id']) {
                $minID = $state['id'];
            }
        }
        
        $this->baseID = $minID;
    }

    public function __toString()
    {
        $string = '    ' . $this->getBlockEnumName() . '("' . $this->blockName . '"';

        if (count($this->properties) !== 0) {
            $string .= ', new Property[]{' . rtrim(implode(', ', $this->properties), ', ') . '}';
        }

        $string .= ')';

        return $string;
    }

    public function getBlockEnumName(): string
    {
        return strtoupper($this->blockName);
    }

    public function getBaseID(): int
    {
        return $this->baseID;
    }
}