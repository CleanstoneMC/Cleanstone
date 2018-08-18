<?php


class Property
{
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
     * Block constructor.
     * @param string $propertyName
     * @param array $values
     * @param array $states
     */
    public function __construct(string $propertyName, array $values, array $states)
    {
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

        return 'PropertyEnum<' . ucfirst($this->propertyName) . '>';
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

        return ', ' . ucfirst($this->propertyName) . '.' . strtoupper($defaultValue);
    }

    public function __toString()
    {
        return 'new ' . $this->getPropertyTypeString() . '("' . $this->propertyName . '"' . $this->getDefaultValue() . ')';
    }
}