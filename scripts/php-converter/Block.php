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

    /**
     * Block constructor.
     * @param string $minecraftBlockName
     * @param array $blockData
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
    }

    public function __toString()
    {
        $string = '    ' . strtoupper($this->blockName) . '("' . $this->blockName . '"';

        if (count($this->properties) !== 0) {
            $string .= ', new Property[]{' . rtrim(implode(', ', $this->properties), ', ') . '}';
        }

        $string .= ')';

        return $string;
    }
}