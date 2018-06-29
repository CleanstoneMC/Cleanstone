import {State} from "./state";

export class Block {
    private _className: string;
    private _display_name: string;
    private _field: string;
    private _hardness: string;
    private _max_state_id: string;
    private _min_state_id: string;
    private _num_states: string;
    private _numeric_id: string;
    private _states: State[];
    private _text_id: string;


    constructor(className: string, display_name: string, field: string, hardness: string, max_state_id: string, min_state_id: string, num_states: string, numeric_id: string, states: any, text_id: string) {
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
            this._states.push(new State(value))
        });

        this._text_id = text_id;
    }


    get className(): string {
        return this._className;
    }

    set className(value: string) {
        this._className = value;
    }

    get display_name(): string {
        return this._display_name;
    }

    set display_name(value: string) {
        this._display_name = value;
    }

    get field(): string {
        return this._field;
    }

    set field(value: string) {
        this._field = value;
    }

    get hardness(): string {
        return this._hardness;
    }

    set hardness(value: string) {
        this._hardness = value;
    }

    get max_state_id(): string {
        return this._max_state_id;
    }

    set max_state_id(value: string) {
        this._max_state_id = value;
    }

    get min_state_id(): string {
        return this._min_state_id;
    }

    set min_state_id(value: string) {
        this._min_state_id = value;
    }

    get num_states(): string {
        return this._num_states;
    }

    set num_states(value: string) {
        this._num_states = value;
    }

    get numeric_id(): string {
        return this._numeric_id;
    }

    set numeric_id(value: string) {
        this._numeric_id = value;
    }

    get states(): State[] {
        return this._states;
    }

    set states(value: State[]) {
        this._states = value;
    }

    get text_id(): string {
        return this._text_id;
    }

    set text_id(value: string) {
        this._text_id = value;
    }

    get blockString(): string {

        let stateString = "";
        this.states.forEach(value => {
            stateString += value.propertyString + ', ';
        });


        if (stateString !== "") {
            stateString = stateString.slice(0, -2);
            stateString = ", new Property[]{" + stateString + "}";
        }

        return this.text_id.toUpperCase() + "(\"" + this.text_id + "\"" + stateString + ")";
        // ACACIA_BUTTON("minecraft:acacia_button", new Property[]{new PropertyEnum<Facing>("facing"), new PropertyBoolean("powered"), new PropertyEnum<Face>("face")}),
    }
}