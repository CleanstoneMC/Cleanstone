import * as fs from 'fs';
import { Block } from './block';

const basefile = fs.readFileSync(process.argv[2] || '1.13.json');

const parsedFile = JSON.parse(basefile.toString());

const rawBlockArray = parsedFile[0]['blocks']['block'];

const blocks: Block[] = Object.values(rawBlockArray).map(value => {
  return new Block(
    value['class'],
    value['display_name'],
    value['field'],
    value['hardness'],
    value['max_state_id'],
    value['min_state_id'],
    value['num_states'],
    value['numeric_id'],
    value['states'],
    value['text_id'],
  );
});

let materialString = `package rocks.cleanstone.game.material;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyBoolean;
import rocks.cleanstone.game.block.state.property.PropertyEnum;
import rocks.cleanstone.game.block.state.property.PropertyInteger;
import rocks.cleanstone.game.block.state.types.*;

import javax.annotation.Nullable;
import java.util.Arrays;

public enum VanillaMaterial implements Material {
`;

materialString += blocks.map(block => `    ${block.blockString}`).join(',\n');

materialString += ';\n';

materialString +=
`
    private final String minecraftID;
    private final Property[] properties;

    VanillaMaterial(String minecraftID) {
        this(minecraftID, new Property[0]);
    }

    VanillaMaterial(String minecraftID, Property[] properties) {
        this.minecraftID = minecraftID;
        this.properties = properties;
    }

    @Nullable
    public static VanillaMaterial byID(String minecraftID) {
        return Arrays.stream(values()).filter(material -> material.getMinecraftID().equals(minecraftID)).findAny().orElse(null);
    }

    public String getMinecraftID() {
        return minecraftID;
    }

    public Property[] getProperties() {
        return properties;
    }
}`;

try {
    fs.mkdirSync('data');
} catch (e) {
    //I dont care
}

fs.writeFileSync('data/VanillaMaterial.java', materialString);

const enums = {};

blocks.forEach(block => {
    block._states.forEach(state => {
        if (state._type === 'enum') {
            enums[state.mappedEnumName] = state.enumString;
        }
    });
});

Object.entries(enums).forEach(([name, content]) => {
  try {
    fs.mkdirSync('data/types');
  } catch (e) {
    //I dont care
  }

  fs.writeFileSync('data/types/' + name + '.java', content);
});
