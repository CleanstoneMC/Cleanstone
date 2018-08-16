import {State} from './state';

export class Block {
  _className: string;
  _display_name: string;
  _field: string;
  _hardness: string;
  _max_state_id: string;
  _min_state_id: string;
  _num_states: string;
  _numeric_id: string;
  _states: State[];
  readonly _text_id: string;

  constructor(
    className: string,
    display_name: string,
    field: string,
    hardness: string,
    max_state_id: string,
    min_state_id: string,
    num_states: string,
    numeric_id: string,
    states: any,
    text_id: string,
  ) {
    this._className = className;
    this._display_name = display_name;
    this._field = field;
    this._hardness = hardness;
    this._max_state_id = max_state_id;
    this._min_state_id = min_state_id;
    this._num_states = num_states;
    this._numeric_id = numeric_id;
    this._states = [];

    states.forEach(value => {
      this._states.push(new State(value));
    });

    this._text_id = text_id;
  }

  get blockString(): string {
    let stateString = '';
    this._states.forEach(value => {
      stateString += value.propertyString + ', ';
    });

    if (stateString !== '') {
      stateString = stateString.slice(0, -2);
      stateString = ', new Property[]{' + stateString + '}';
    }

    return (
      this._text_id.toUpperCase() + '("' + this._text_id + '"' + stateString + ')'
    );
    // ACACIA_BUTTON("minecraft:acacia_button", new Property[]{new PropertyEnum<Facing>("facing"), new PropertyBoolean("powered"), new PropertyEnum<Face>("face")}),
  }
}
