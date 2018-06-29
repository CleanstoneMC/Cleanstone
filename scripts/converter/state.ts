export class State {
    private static mappings = {
        'bkw': 'Face',
        'ble': 'DoorHalf',
        'bld': 'HingePosition',
        'eo$a': 'Axis',
        'bln': 'SlabType',
        'blg': 'HalfBlockPosition',
        'blo': 'StairShape',
        'bll': 'ActivatorRailShape',
        'bkx': 'BedPart',
        'bla': 'ChestType',
        'blb': 'ComparatorMode',
        'blj': 'PistonType',
        'bli': 'NoteBlockInstrument',
        'blm': 'RedstonePosition',
        'blp': 'StructureBlockType',
        'bky': 'Facing'
    };

    private _declared_in: string;
    private _field_name: string;
    private _enum_class: string;
    private _name: string;
    private _num_values: number;
    private _type: string;
    private _values: string[];

    constructor(value: object) {
        Object.entries(value).forEach(([key, value]) => {
            if (key === "declared_in") {
                this._declared_in = value;
            } else if (key === "field_name") {
                this._field_name = value;
            } else if (key === "name") {
                this._name = value;
            } else if (key === "num_values") {
                this._num_values = Number(value);
            } else if (key === "type") {
                if (value === "enum" || value === "direction") {
                    this._type = "enum";
                } else {
                    this._type = value;
                }
            } else if (key === "values") {
                this._values = value;
            } else if (key === "enum_class") {
                this._enum_class = value;
            }
        })
    }

    get declared_in(): string {
        return this._declared_in;
    }

    set declared_in(value: string) {
        this._declared_in = value;
    }

    get field_name(): string {
        return this._field_name;
    }

    set field_name(value: string) {
        this._field_name = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get num_values(): number {
        return this._num_values;
    }

    set num_values(value: number) {
        this._num_values = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    get values(): string[] {
        return this._values;
    }

    set values(value: string[]) {
        this._values = value;
    }


    get enum_class(): string {
        return this._enum_class;
    }

    set enum_class(value: string) {
        this._enum_class = value;
    }

    get mappedEnumName(): string {
        let name: string;
        if (this._enum_class !== undefined) {
            name = State.mappings[this._enum_class]
        }

        if (name === undefined) {
            name = State.mappings[this._declared_in];
        }

        if (name === undefined) {
            console.log(this);
        }

        return name;
    }

    get propertyString(): string {
        switch (this.type) {
            case "bool":
                return "new PropertyBoolean(\"" + this.name + "\")";
            case "enum":
            case "direction":
                return "new PropertyEnum<" + this.mappedEnumName + ">(\"" + this.name + "\")";
            case "int":
                return "new PropertyInteger(\"" + this.name + "\")";
            default:
                console.log(this.type);
                return "";
        }
    }

    get enumString(): string {
        if (this._type !== "enum" && this._type !== "direction") {
            return "";
        }

        let template = "public enum " + this.mappedEnumName + " {\n";

        this.values.forEach(value => {
            template += "    " + value + ",\n";
        });

        template = template.slice(0, -2);

        template += "\n}";

        return template;
    }
}