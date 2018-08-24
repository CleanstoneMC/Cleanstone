export class State {
  private static mappings = {
    blp: 'Face',
    blx: 'BlockHalf',
    blw: 'HingePosition',
    ep$a: 'Axis',
    bmg: 'SlabType',
    blz: 'HalfBlockPosition',
    bmh: 'StairShape',
    bme: 'RailShape',
    blq: 'BedPart',
    blt: 'ChestType',
    blu: 'ComparatorMode',
    bmc: 'PistonType',
    bmb: 'NoteBlockInstrument',
    dontknow: 'RedstonePosition',
    bmi: 'StructureBlockMode',
    blr: 'Facing',
  };

  _declared_in: string;
  _field_name: string;
  _enum_class: string;
  _name: string;
  _num_values: number;
  _type: string;
  _values: string[];

  constructor(value: object) {
    Object.entries(value).forEach(([key, value]) => {
      if (key === 'declared_in') {
        this._declared_in = value;
      } else if (key === 'field_name') {
        this._field_name = value;
      } else if (key === 'name') {
        this._name = value;
      } else if (key === 'num_values') {
        this._num_values = Number(value);
      } else if (key === 'type') {
        if (value === 'enum' || value === 'direction') {
          this._type = 'enum';
        } else {
          this._type = value;
        }
      } else if (key === 'values') {
        this._values = value;
      } else if (key === 'enum_class') {
        this._enum_class = value;
      }
    });
  }

  get mappedEnumName(): string {
    let name: string;
    if (this._enum_class !== undefined) {
      name = State.mappings[this._enum_class];
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
    switch (this._type) {
      case 'bool':
        return 'new PropertyBoolean("' + this._name + '")';
      case 'enum':
      case 'direction':
        return (
          'new PropertyEnum<' + this.mappedEnumName + '>("' + this._name + '")'
        );
      case 'int':
        return 'new PropertyInteger("' + this._name + '")';
      default:
        console.log(this._type);
        return '';
    }
  }

  get enumString(): string {
    if (this._type !== 'enum' && this._type !== 'direction') {
      return '';
    }

    let template = 'public enum ' + this.mappedEnumName + ' {\n';

    this._values.forEach(value => {
      template += '    ' + value + ',\n';
    });

    template = template.slice(0, -2);

    template += '\n}';

    return template;
  }
}
