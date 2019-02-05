<?php


class Property
{


    /**
     * @var self[]
     */
    public static $properties = [];

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
     * @throws Exception
     */
    public function __construct(string $blockName, string $propertyName, array $values, array $states)
    {
        $this->blockName = $blockName;
        $this->propertyName = $propertyName;
        $this->values = $values;
        $this->states = $states;

        if (isset(self::$properties[$this->getBlockPropertiesName()])) {
            /** @var Property $duplicate */
            $duplicate = self::$properties[$this->getBlockPropertiesName()];

            if ($this->getLongPropertyType() !== $duplicate->getLongPropertyType()) {
                throw new Exception('Override!!!1elf ' . $blockName . ':' . $this->getBlockPropertiesName());
            }

//            $cachePropertyName = strtoupper($blockName) . '_' . $cachePropertyName;
        }

        self::$properties[$this->getBlockPropertiesName()] = $this;

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

    private function getEnumClass(): string
    {
        if (isset(self::BLOCK_NAME_ENUM_OVERRIDE[$this->blockName][$this->propertyName])) {
            return ucfirst(self::BLOCK_NAME_ENUM_OVERRIDE[$this->blockName][$this->propertyName]);
        }

        return ucfirst($this->propertyName);
    }

    public function getShortPropertyType(): string
    {
        if ($this->isInteger()) {
            return 'PropertyInteger';
        }

        if ($this->isBoolean()) {
            return 'PropertyBoolean';
        }

        return 'PropertyEnum<>';
    }

    public function getLongPropertyType(): string
    {
        if ($this->isInteger()) {
            return 'PropertyInteger';
        }

        if ($this->isBoolean()) {
            return 'PropertyBoolean';
        }

        return 'PropertyEnum<' . $this->getEnumClass() . '>';
    }

    public function getBlockPropertiesName(): string
    {
        $name = $this->propertyName;

        if (isset(self::PROPERTY_NAME_OVERRIDE[$this->blockName][$this->propertyName])) {
            $name = self::PROPERTY_NAME_OVERRIDE[$this->blockName][$this->propertyName];
        }

        if (isset(self::$properties[$name]) && self::$properties[$name]->getLongPropertyType() !== $this->getLongPropertyType()) {
            throw new \Exception('override: ' . $name);
        }

        return $this->from_camel_case($name);
    }

    public function getDefaultState(): array
    {
        foreach ($this->states as $state) {
            if (isset($state['default']) && $state['default'] === true) {
                return $state;
            }
        }

        throw new Exception('Could not found default State for ' . $this->propertyName);
    }

    public function getDefaultValue(): string
    {
        $defaultState = $this->getDefaultState();
        $defaultValue = $defaultState['properties'][$this->propertyName];

        if ($this->isInteger()) {
            return ', ' . $defaultValue;
        }

        if ($this->isBoolean()) {
            return ', ' . $defaultValue;
        }

        return ', ' . $this->getEnumClass() . '.' . strtoupper($defaultValue);
    }

    public function getPropertiesStringDefaultValue(): string
    {
        $defaultState = $this->getDefaultState();
        $defaultValue = $defaultState['properties'][$this->propertyName];

        if ($this->isInteger()) {
            return ', ' . $this->values[0] . ', ' . array_reverse($this->values)[0];
        }

        if ($this->isBoolean()) {
            return '';
        }

        return ', ' . $this->getEnumClass() . '.class';
    }

    public function getPropertyName(): string
    {
        return strtoupper($this->getBlockPropertiesName());
    }

    public function __toString()
    {
        return 'definitionOf(' . $this->getPropertyName() . $this->getDefaultValue() . ')';
    }

    public function getBlockPropertiesString(): string
    {
        return 'new ' . $this->getShortPropertyType() . '("' . $this->propertyName . '"' . $this->getPropertiesStringDefaultValue() . ')';
    }

    /**
     * Borrowed from https://stackoverflow.com/a/1993772
     * @param $input
     * @return string
     */
    private function from_camel_case($input)
    {
        preg_match_all('!([A-Z][A-Z0-9]*(?=$|[A-Z][a-z0-9])|[A-Za-z][a-z0-9]+)!', $input, $matches);
        $ret = $matches[0];
        foreach ($ret as &$match) {
            $match = $match == strtoupper($match) ? strtolower($match) : lcfirst($match);
        }
        return implode('_', $ret);
    }
}