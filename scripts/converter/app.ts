import * as fs from "fs";
import {Block} from "./block";

let basefile = fs.readFileSync("1.13-pre5.json");

let parsedFile = JSON.parse(basefile.toString());

let rawBlockArray = parsedFile[0]["blocks"]["block"];

let enums = {};
let blocks: Block[];
blocks = [];
Object.values(rawBlockArray).forEach(value => {
    blocks.push(new Block(
        value["class"],
        value["display_name"],
        value["field"],
        value["hardness"],
        value["max_state_id"],
        value["min_state_id"],
        value["num_states"],
        value["numeric_id"],
        value["states"],
        value["text_id"]
    ));
});


let materialString = "package rocks.cleanstone.game.material;\n" +
    "\n" +
    "import rocks.cleanstone.game.block.state.property.Property;\n" +
    "import rocks.cleanstone.game.block.state.property.PropertyBoolean;\n" +
    "import rocks.cleanstone.game.block.state.property.PropertyEnum;\n" +
    "import rocks.cleanstone.game.block.state.property.PropertyInteger;\n" +
    "import rocks.cleanstone.game.block.state.types.*;\n" +
    "\n" +
    "import javax.annotation.Nullable;\n" +
    "import java.util.Arrays;\n" +
    "\n" +
    "public enum VanillaMaterial implements Material {\n";

blocks.forEach(block => {

    materialString += "    " + block.blockString + ",\n";

    block.states.forEach(state => {
        if (state.type === "enum") {
            enums[state.mappedEnumName] = state.enumString;
        }
    })
});


materialString = materialString.slice(0, -2) + ";\n";

materialString +=
    "\n" +
    "    private final String minecraftID;\n" +
    "    private final Property[] properties;\n" +
    "\n" +
    "    VanillaMaterial(String minecraftID) {\n" +
    "        this(minecraftID, new Property[0]);\n" +
    "    }\n" +
    "\n" +
    "\n" +
    "    VanillaMaterial(String minecraftID, Property[] properties) {\n" +
    "        this.minecraftID = minecraftID;\n" +
    "        this.properties = properties;\n" +
    "    }\n" +
    "\n" +
    "    @Nullable\n" +
    "    public static VanillaMaterial byID(String minecraftID) {\n" +
    "        return Arrays.stream(values()).filter(material -> material.getMinecraftID().equals(minecraftID)).findAny().orElse(null);\n" +
    "    }\n" +
    "\n" +
    "    public String getMinecraftID() {\n" +
    "        return minecraftID;\n" +
    "    }\n" +
    "\n" +
    "    public Property[] getProperties() {\n" +
    "        return properties;\n" +
    "    }\n" +
    "}";

try {
    fs.mkdirSync('data');
} catch (e) {
    //I dont care
}

fs.writeFileSync("data/VanillaMaterial.java", materialString);

Object.entries(enums).forEach(([name, content]) => {
    try {
        fs.mkdirSync('data/types');
    } catch (e) {
        //I dont care
    }

    fs.writeFileSync('data/types/' + name + ".java", content);
});


