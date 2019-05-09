package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.block.state.mapping.LegacyMaterialMapping;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemType;

import static rocks.cleanstone.game.material.item.vanilla.VanillaItemType.*;

/**
 * Maps Cleanstone's internal item types to the Minecraft client's legacy pre-1.13 format
 */
@Component("protocolItemTypeMapping_v1_12_2")
public class ProtocolItemTypeMapping extends LegacyMaterialMapping {
    public ProtocolItemTypeMapping() {
        super(VanillaItemType.STONE);
        setID(AIR, 0);
        setID(STONE, 1);
        setID(GRANITE, 1, 1); //TODO, Add Metadata workaround
        setID(POLISHED_GRANITE, 1, 2); //TODO, Add Metadata workaround
        setID(DIORITE, 1, 3); //TODO, Add Metadata workaround
        setID(POLISHED_DIORITE, 1, 4); //TODO, Add Metadata workaround
        setID(ANDESITE, 1, 5); //TODO, Add Metadata workaround
        setID(POLISHED_ANDESITE, 1, 6); //TODO, Add Metadata workaround
        setID(GRASS_BLOCK, 2);
        setID(DIRT, 3);
        setID(COARSE_DIRT, 3, 1); //TODO, Add Metadata workaround
        setID(PODZOL, 3, 2); //TODO, Add Metadata workaround
        setID(COBBLESTONE, 4);
        setID(OAK_PLANKS, 5);
        setID(SPRUCE_PLANKS, 5, 1); //TODO, Add Metadata workaround
        setID(BIRCH_PLANKS, 5, 2); //TODO, Add Metadata workaround
        setID(JUNGLE_PLANKS, 5, 3); //TODO, Add Metadata workaround
        setID(ACACIA_PLANKS, 5, 4); //TODO, Add Metadata workaround
        setID(DARK_OAK_PLANKS, 5, 5); //TODO, Add Metadata workaround
        setID(OAK_SAPLING, 6);
        setID(SPRUCE_SAPLING, 6, 1); //TODO, Add Metadata workaround
        setID(BIRCH_SAPLING, 6, 2); //TODO, Add Metadata workaround
        setID(JUNGLE_SAPLING, 6, 3); //TODO, Add Metadata workaround
        setID(ACACIA_SAPLING, 6, 4); //TODO, Add Metadata workaround
        setID(DARK_OAK_SAPLING, 6, 5); //TODO, Add Metadata workaround
        setID(BEDROCK, 7);
//        setID(FLOWING_WATER, 8);
//        setID(STILL_WATER, 9);
//        setID(FLOWING_LAVA, 10);
//        setID(STILL_LAVA, 11);
        setID(SAND, 12);
        setID(RED_SAND, 12, 1); //TODO, Add Metadata workaround
        setID(GRAVEL, 13);
        setID(GOLD_ORE, 14);
        setID(IRON_ORE, 15);
        setID(COAL_ORE, 16);
        setID(OAK_WOOD, 17);
        setID(SPRUCE_WOOD, 17, 1); //TODO, Add Metadata workaround
        setID(BIRCH_WOOD, 17, 2); //TODO, Add Metadata workaround
        setID(JUNGLE_WOOD, 17, 3); //TODO, Add Metadata workaround
        setID(OAK_LEAVES, 18);
        setID(SPRUCE_LEAVES, 18, 1); //TODO, Add Metadata workaround
        setID(BIRCH_LEAVES, 18, 2); //TODO, Add Metadata workaround
        setID(JUNGLE_LEAVES, 18, 3); //TODO, Add Metadata workaround
        setID(SPONGE, 19);
        setID(WET_SPONGE, 19, 1); //TODO, Add Metadata workaround
        setID(GLASS, 20);
        setID(LAPIS_ORE, 21);
        setID(LAPIS_BLOCK, 22);
        setID(DISPENSER, 23);
        setID(SANDSTONE, 24);
        setID(CHISELED_SANDSTONE, 24, 1); //TODO, Add Metadata workaround
        setID(SMOOTH_SANDSTONE, 24, 2); //TODO, Add Metadata workaround
        setID(NOTE_BLOCK, 25);
        setID(RED_BED, 26);
        setID(POWERED_RAIL, 27);
        setID(DETECTOR_RAIL, 28);
        setID(STICKY_PISTON, 29);
        setID(COBWEB, 30);
//        setID(DEAD_SHRUB, 31);
        setID(GRASS, 31, 1); //TODO, Add Metadata workaround
        setID(FERN, 31, 2); //TODO, Add Metadata workaround
        setID(DEAD_BUSH, 32);
        setID(PISTON, 33);
//        setID(PISTON_HEAD, 34);
        setID(WHITE_WOOL, 35);
        setID(ORANGE_WOOL, 35, 1); //TODO, Add Metadata workaround
        setID(MAGENTA_WOOL, 35, 2); //TODO, Add Metadata workaround
        setID(LIGHT_BLUE_WOOL, 35, 3); //TODO, Add Metadata workaround
        setID(YELLOW_WOOL, 35, 4); //TODO, Add Metadata workaround
        setID(LIME_WOOL, 35, 5); //TODO, Add Metadata workaround
        setID(PINK_WOOL, 35, 6); //TODO, Add Metadata workaround
        setID(GRAY_WOOL, 35, 7); //TODO, Add Metadata workaround
        setID(LIGHT_GRAY_WOOL, 35, 8); //TODO, Add Metadata workaround
        setID(CYAN_WOOL, 35, 9); //TODO, Add Metadata workaround
        setID(PURPLE_WOOL, 35, 10); //TODO, Add Metadata workaround
        setID(BLUE_WOOL, 35, 11); //TODO, Add Metadata workaround
        setID(BROWN_WOOL, 35, 12); //TODO, Add Metadata workaround
        setID(GREEN_WOOL, 35, 13); //TODO, Add Metadata workaround
        setID(RED_WOOL, 35, 14); //TODO, Add Metadata workaround
        setID(BLACK_WOOL, 35, 15); //TODO, Add Metadata workaround
        setID(DANDELION, 37);
        setID(POPPY, 38);
        setID(BLUE_ORCHID, 38, 1); //TODO, Add Metadata workaround
        setID(ALLIUM, 38, 2); //TODO, Add Metadata workaround
        setID(AZURE_BLUET, 38, 3); //TODO, Add Metadata workaround
        setID(RED_TULIP, 38, 4); //TODO, Add Metadata workaround
        setID(ORANGE_TULIP, 38, 5); //TODO, Add Metadata workaround
        setID(WHITE_TULIP, 38, 6); //TODO, Add Metadata workaround
        setID(PINK_TULIP, 38, 7); //TODO, Add Metadata workaround
        setID(OXEYE_DAISY, 38, 8); //TODO, Add Metadata workaround
        setID(BROWN_MUSHROOM, 39);
        setID(RED_MUSHROOM, 40);
        setID(GOLD_BLOCK, 41);
        setID(IRON_BLOCK, 42);
//        setID(DOUBLE_STONE_SLAB, 43);
// setID(DOUBLE_SANDSTONE_SLAB, 43, 1); //TODO, Add Metadata workaround
// setID(DOUBLE_WOODEN_SLAB, 43, 2); //TODO, Add Metadata workaround
// setID(DOUBLE_COBBLESTONE_SLAB, 43, 3); //TODO, Add Metadata workaround
// setID(DOUBLE_BRICK_SLAB, 43, 4); //TODO, Add Metadata workaround
// setID(DOUBLE_STONE_BRICK_SLAB, 43, 5); //TODO, Add Metadata workaround
// setID(DOUBLE_NETHER_BRICK_SLAB, 43, 6); //TODO, Add Metadata workaround
// setID(DOUBLE_QUARTZ_SLAB, 43, 7); //TODO, Add Metadata workaround
        setID(STONE_SLAB, 44);
        setID(SANDSTONE_SLAB, 44, 1); //TODO, Add Metadata workaround
//setID(WOODEN_SLAB, 44, 2); //TODO, Add Metadata workaround
        setID(COBBLESTONE_SLAB, 44, 3); //TODO, Add Metadata workaround
        setID(BRICK_SLAB, 44, 4); //TODO, Add Metadata workaround
        setID(STONE_BRICK_SLAB, 44, 5); //TODO, Add Metadata workaround
        setID(NETHER_BRICK_SLAB, 44, 6); //TODO, Add Metadata workaround
        setID(QUARTZ_SLAB, 44, 7); //TODO, Add Metadata workaround
        setID(BRICKS, 45);
        setID(TNT, 46);
        setID(BOOKSHELF, 47);
//        setID(MOSS_STONE, 48);
        setID(OBSIDIAN, 49);
        setID(TORCH, 50);
//        setID(FIRE, 51);
//        setID(MONSTER_SPAWNER, 52);
//        setID(OAK_WOOD_STAIRS, 53);
        setID(CHEST, 54);
//        setID(REDSTONE_WIRE, 55);
        setID(DIAMOND_ORE, 56);
        setID(DIAMOND_BLOCK, 57);
        setID(CRAFTING_TABLE, 58);
//        setID(WHEAT_CROPS, 59);
        setID(FARMLAND, 60);
        setID(FURNACE, 61);
//        setID(BURNING_FURNACE, 62);
//        setID(STANDING_SIGN_BLOCK, 63);
//        setID(OAK_DOOR_BLOCK, 64);
        setID(LADDER, 65);
        setID(RAIL, 66);
        setID(COBBLESTONE_STAIRS, 67);
//        setID(WALL_MOUNTED_SIGN_BLOCK, 68);
        setID(LEVER, 69);
        setID(STONE_PRESSURE_PLATE, 70);
//        setID(IRON_DOOR_BLOCK, 71);
//        setID(WOODEN_PRESSURE_PLATE, 72);
        setID(REDSTONE_ORE, 73);
//        setID(GLOWING_REDSTONE_ORE, 74);
//        setID(REDSTONE_TORCH_OFF, 75);
//        setID(REDSTONE_TORCH_ON, 76);
        setID(STONE_BUTTON, 77);
        setID(SNOW, 78);
        setID(ICE, 79);
        setID(SNOW_BLOCK, 80);
        setID(CACTUS, 81);
        setID(CLAY, 82);
        setID(JUKEBOX, 84);
        setID(OAK_FENCE, 85);
        setID(PUMPKIN, 86);
        setID(NETHERRACK, 87);
        setID(SOUL_SAND, 88);
        setID(GLOWSTONE, 89);
//        setID(NETHER_PORTAL, 90);
        setID(JACK_O_LANTERN, 91);
        setID(CAKE, 92);
//        setID(REDSTONE_REPEATER_BLOCK_OFF, 93);
//        setID(REDSTONE_REPEATER_BLOCK_ON, 94);
        setID(WHITE_STAINED_GLASS, 95);
        setID(ORANGE_STAINED_GLASS, 95, 1);
        setID(MAGENTA_STAINED_GLASS, 95, 2);
        setID(LIGHT_BLUE_STAINED_GLASS, 95, 3);
        setID(YELLOW_STAINED_GLASS, 95, 4);
        setID(LIME_STAINED_GLASS, 95, 5);
        setID(PINK_STAINED_GLASS, 95, 6);
        setID(GRAY_STAINED_GLASS, 95, 7);
        setID(LIGHT_GRAY_STAINED_GLASS, 95, 8);
        setID(CYAN_STAINED_GLASS, 95, 9);
        setID(PURPLE_STAINED_GLASS, 95, 10);
        setID(BLUE_STAINED_GLASS, 95, 11);
        setID(BROWN_STAINED_GLASS, 95, 12);
        setID(GREEN_STAINED_GLASS, 95, 13);
        setID(RED_STAINED_GLASS, 95, 14);
        setID(BLACK_STAINED_GLASS, 95, 15);
//        setID(WOODEN_TRAPDOOR, 96);
//        setID(STONE_MONSTER_EGG, 97);
        setID(INFESTED_COBBLESTONE, 97, 1);
        setID(INFESTED_STONE_BRICKS, 97, 2);
        setID(INFESTED_MOSSY_STONE_BRICKS, 97, 3);
        setID(INFESTED_CRACKED_STONE_BRICKS, 97, 4);
        setID(INFESTED_CHISELED_STONE_BRICKS, 97, 5);
        setID(STONE_BRICKS, 98);
        setID(MOSSY_STONE_BRICKS, 98, 1);
        setID(CRACKED_STONE_BRICKS, 98, 2);
        setID(CHISELED_STONE_BRICKS, 98, 3);
        setID(BROWN_MUSHROOM_BLOCK, 99);
        setID(RED_MUSHROOM_BLOCK, 100);
        setID(IRON_BARS, 101);
        setID(GLASS_PANE, 102);
//        setID(MELON_BLOCK, 103);
//        setID(PUMPKIN_STEM, 104);
//        setID(MELON_STEM, 105);
//        setID(VINES, 106);
        setID(OAK_FENCE_GATE, 107);
        setID(BRICK_STAIRS, 108);
        setID(STONE_BRICK_STAIRS, 109);
        setID(MYCELIUM, 110);
        setID(LILY_PAD, 111);
        setID(NETHER_BRICK, 112);
        setID(NETHER_BRICK_FENCE, 113);
        setID(NETHER_BRICK_STAIRS, 114);
        setID(NETHER_WART, 115);
//        setID(ENCHANTMENT_TABLE, 116);
        setID(BREWING_STAND, 117);
        setID(CAULDRON, 118);
//        setID(END_PORTAL, 119);
        setID(END_PORTAL_FRAME, 120);
        setID(END_STONE, 121);
        setID(DRAGON_EGG, 122);
//        setID(REDSTONE_LAMP_INACTIVE, 123);
//        setID(REDSTONE_LAMP_ACTIVE, 124);
//        setID(DOUBLE_OAK_WOOD_SLAB, 125);
// setID(DOUBLE_SPRUCE_WOOD_SLAB, 125, 1); //TODO, Add Metadata workaround
// setID(DOUBLE_BIRCH_WOOD_SLAB, 125, 2); //TODO, Add Metadata workaround
// setID(DOUBLE_JUNGLE_WOOD_SLAB, 125, 3); //TODO, Add Metadata workaround
// setID(DOUBLE_ACACIA_WOOD_SLAB, 125, 4); //TODO, Add Metadata workaround
// setID(DOUBLE_DARK_OAK_WOOD_SLAB, 125, 5); //TODO, Add Metadata workaround
//        setID(OAK_WOOD_SLAB, 126);
// setID(SPRUCE_WOOD_SLAB, 126, 1); //TODO, Add Metadata workaround
// setID(BIRCH_WOOD_SLAB, 126, 2); //TODO, Add Metadata workaround
// setID(JUNGLE_WOOD_SLAB, 126, 3); //TODO, Add Metadata workaround
// setID(ACACIA_WOOD_SLAB, 126, 4); //TODO, Add Metadata workaround
// setID(DARK_OAK_WOOD_SLAB, 126, 5); //TODO, Add Metadata workaround
//        setID(COCOA, 127);
        setID(SANDSTONE_STAIRS, 128);
        setID(EMERALD_ORE, 129);
        setID(ENDER_CHEST, 130);
        setID(TRIPWIRE_HOOK, 131);
//        setID(TRIPWIRE, 132);
        setID(EMERALD_BLOCK, 133);
//        setID(SPRUCE_WOOD_STAIRS, 134);
//        setID(BIRCH_WOOD_STAIRS, 135);
//        setID(JUNGLE_WOOD_STAIRS, 136);
        setID(COMMAND_BLOCK, 137);
        setID(BEACON, 138);
        setID(COBBLESTONE_WALL, 139);
        setID(MOSSY_COBBLESTONE_WALL, 139, 1);
        setID(FLOWER_POT, 140);
//        setID(CARROTS, 141);
//        setID(POTATOES, 142);
//        setID(WOODEN_BUTTON, 143);
//        setID(MOB_HEAD, 144);
        setID(ANVIL, 145);
        setID(TRAPPED_CHEST, 146);
//        setID(WEIGHTED_PRESSURE_PLATE_LIGHT, 147);
//        setID(WEIGHTED_PRESSURE_PLATE_HEAVY, 148);
//        setID(REDSTONE_COMPARATOR_INACTIVE, 149);
//        setID(REDSTONE_COMPARATOR_ACTIVE, 150);
//        setID(DAYLIGHT_SENSOR, 151);
        setID(REDSTONE_BLOCK, 152);
        setID(NETHER_QUARTZ_ORE, 153);
        setID(HOPPER, 154);
        setID(QUARTZ_BLOCK, 155);
        setID(CHISELED_QUARTZ_BLOCK, 155, 1);
        setID(QUARTZ_PILLAR, 155, 2);
        setID(QUARTZ_STAIRS, 156);
        setID(ACTIVATOR_RAIL, 157);
        setID(DROPPER, 158);
//        setID(WHITE_HARDENED_CLAY, 159);
        setID(ORANGE_TERRACOTTA, 159, 1);
        setID(MAGENTA_TERRACOTTA, 159, 2);
        setID(LIGHT_BLUE_TERRACOTTA, 159, 3);
        setID(YELLOW_TERRACOTTA, 159, 4);
        setID(LIME_TERRACOTTA, 159, 5);
        setID(PINK_TERRACOTTA, 159, 6);
        setID(GRAY_TERRACOTTA, 159, 7);
        setID(LIGHT_GRAY_TERRACOTTA, 159, 8);
        setID(CYAN_TERRACOTTA, 159, 9);
        setID(PURPLE_TERRACOTTA, 159, 10);
        setID(BLUE_TERRACOTTA, 159, 11);
        setID(BROWN_TERRACOTTA, 159, 12);
        setID(GREEN_TERRACOTTA, 159, 13);
        setID(RED_TERRACOTTA, 159, 14);
        setID(BLACK_TERRACOTTA, 159, 15);
        setID(WHITE_STAINED_GLASS_PANE, 160);
        setID(ORANGE_STAINED_GLASS_PANE, 160, 1);
        setID(MAGENTA_STAINED_GLASS_PANE, 160, 2);
        setID(LIGHT_BLUE_STAINED_GLASS_PANE, 160, 3);
        setID(YELLOW_STAINED_GLASS_PANE, 160, 4);
        setID(LIME_STAINED_GLASS_PANE, 160, 5);
        setID(PINK_STAINED_GLASS_PANE, 160, 6);
        setID(GRAY_STAINED_GLASS_PANE, 160, 7);
        setID(LIGHT_GRAY_STAINED_GLASS_PANE, 160, 8);
        setID(CYAN_STAINED_GLASS_PANE, 160, 9);
        setID(PURPLE_STAINED_GLASS_PANE, 160, 10);
        setID(BLUE_STAINED_GLASS_PANE, 160, 11);
        setID(BROWN_STAINED_GLASS_PANE, 160, 12);
        setID(GREEN_STAINED_GLASS_PANE, 160, 13);
        setID(RED_STAINED_GLASS_PANE, 160, 14);
        setID(BLACK_STAINED_GLASS_PANE, 160, 15);
        setID(ACACIA_LEAVES, 161);
        setID(DARK_OAK_LEAVES, 161, 1);
        setID(ACACIA_WOOD, 162);
        setID(DARK_OAK_WOOD, 162, 1); //TODO, Add Metadata workaround
//        setID(ACACIA_WOOD_STAIRS, 163);
//        setID(DARK_OAK_WOOD_STAIRS, 164);
        setID(SLIME_BLOCK, 165);
        setID(BARRIER, 166);
        setID(IRON_TRAPDOOR, 167);
        setID(PRISMARINE, 168);
        setID(PRISMARINE_BRICKS, 168, 1);
        setID(DARK_PRISMARINE, 168, 2);
        setID(SEA_LANTERN, 169);
//        setID(HAY_BALE, 170);
        setID(WHITE_CARPET, 171);
        setID(ORANGE_CARPET, 171, 1);
        setID(MAGENTA_CARPET, 171, 2);
        setID(LIGHT_BLUE_CARPET, 171, 3);
        setID(YELLOW_CARPET, 171, 4);
        setID(LIME_CARPET, 171, 5);
        setID(PINK_CARPET, 171, 6);
        setID(GRAY_CARPET, 171, 7);
        setID(LIGHT_GRAY_CARPET, 171, 8);
        setID(CYAN_CARPET, 171, 9);
        setID(PURPLE_CARPET, 171, 10);
        setID(BLUE_CARPET, 171, 11);
        setID(BROWN_CARPET, 171, 12);
        setID(GREEN_CARPET, 171, 13);
        setID(RED_CARPET, 171, 14);
        setID(BLACK_CARPET, 171, 15);
//        setID(HARDENED_CLAY, 172);
//        setID(BLOCK_OF_COAL, 173);
        setID(PACKED_ICE, 174);
        setID(SUNFLOWER, 175);
        setID(LILAC, 175, 1);
//        setID(DOUBLE_TALLGRASS, 175, 2); //TODO: Add Metadata workaround
        setID(LARGE_FERN, 175, 3);
        setID(ROSE_BUSH, 175, 4);
        setID(PEONY, 175, 5);
//        setID(FREE_STANDING_BANNER, 176);
//        setID(WALL_MOUNTED_BANNER, 177);
//        setID(INVERTED_DAYLIGHT_SENSOR, 178);
        setID(RED_SANDSTONE, 179);
        setID(CHISELED_RED_SANDSTONE, 179, 1);
        setID(SMOOTH_RED_SANDSTONE, 179, 2);
        setID(RED_SANDSTONE_STAIRS, 180);
//        setID(DOUBLE_RED_SANDSTONE_SLAB, 181);
        setID(RED_SANDSTONE_SLAB, 182);
        setID(SPRUCE_FENCE_GATE, 183);
        setID(BIRCH_FENCE_GATE, 184);
        setID(JUNGLE_FENCE_GATE, 185);
        setID(DARK_OAK_FENCE_GATE, 186);
        setID(ACACIA_FENCE_GATE, 187);
        setID(SPRUCE_FENCE, 188);
        setID(BIRCH_FENCE, 189);
        setID(JUNGLE_FENCE, 190);
        setID(DARK_OAK_FENCE, 191);
        setID(ACACIA_FENCE, 192);
//        setID(SPRUCE_DOOR_BLOCK, 193);
//        setID(BIRCH_DOOR_BLOCK, 194);
//        setID(JUNGLE_DOOR_BLOCK, 195);
//        setID(ACACIA_DOOR_BLOCK, 196);
//        setID(DARK_OAK_DOOR_BLOCK, 197);
        setID(END_ROD, 198);
        setID(CHORUS_PLANT, 199);
        setID(CHORUS_FLOWER, 200);
        setID(PURPUR_BLOCK, 201);
        setID(PURPUR_PILLAR, 202);
        setID(PURPUR_STAIRS, 203);
//        setID(PURPUR_DOUBLE_SLAB, 204);
        setID(PURPUR_SLAB, 205);
        setID(END_STONE_BRICKS, 206);
//        setID(BEETROOT_BLOCK, 207);
        setID(GRASS_PATH, 208);
//        setID(END_GATEWAY, 209);
        setID(REPEATING_COMMAND_BLOCK, 210);
        setID(CHAIN_COMMAND_BLOCK, 211);
//        setID(FROSTED_ICE, 212);
        setID(MAGMA_BLOCK, 213);
        setID(NETHER_WART_BLOCK, 214);
//        setID(RED_NETHER_BRICK, 215);
        setID(BONE_BLOCK, 216);
        setID(STRUCTURE_VOID, 217);
        setID(OBSERVER, 218);
        setID(WHITE_SHULKER_BOX, 219);
        setID(ORANGE_SHULKER_BOX, 220);
        setID(MAGENTA_SHULKER_BOX, 221);
        setID(LIGHT_BLUE_SHULKER_BOX, 222);
        setID(YELLOW_SHULKER_BOX, 223);
        setID(LIME_SHULKER_BOX, 224);
        setID(PINK_SHULKER_BOX, 225);
        setID(GRAY_SHULKER_BOX, 226);
        setID(LIGHT_GRAY_SHULKER_BOX, 227);
        setID(CYAN_SHULKER_BOX, 228);
        setID(PURPLE_SHULKER_BOX, 229);
        setID(BLUE_SHULKER_BOX, 230);
        setID(BROWN_SHULKER_BOX, 231);
        setID(GREEN_SHULKER_BOX, 232);
        setID(RED_SHULKER_BOX, 233);
        setID(BLACK_SHULKER_BOX, 234);
        setID(WHITE_GLAZED_TERRACOTTA, 235);
        setID(ORANGE_GLAZED_TERRACOTTA, 236);
        setID(MAGENTA_GLAZED_TERRACOTTA, 237);
        setID(LIGHT_BLUE_GLAZED_TERRACOTTA, 238);
        setID(YELLOW_GLAZED_TERRACOTTA, 239);
        setID(LIME_GLAZED_TERRACOTTA, 240);
        setID(PINK_GLAZED_TERRACOTTA, 241);
        setID(GRAY_GLAZED_TERRACOTTA, 242);
        setID(LIGHT_GRAY_GLAZED_TERRACOTTA, 243);
        setID(CYAN_GLAZED_TERRACOTTA, 244);
        setID(PURPLE_GLAZED_TERRACOTTA, 245);
        setID(BLUE_GLAZED_TERRACOTTA, 246);
        setID(BROWN_GLAZED_TERRACOTTA, 247);
        setID(GREEN_GLAZED_TERRACOTTA, 248);
        setID(RED_GLAZED_TERRACOTTA, 249);
        setID(BLACK_GLAZED_TERRACOTTA, 250);
        setID(WHITE_CONCRETE, 251);
        setID(ORANGE_CONCRETE, 251, 1);
        setID(MAGENTA_CONCRETE, 251, 2);
        setID(LIGHT_BLUE_CONCRETE, 251, 3);
        setID(YELLOW_CONCRETE, 251, 4);
        setID(LIME_CONCRETE, 251, 5);
        setID(PINK_CONCRETE, 251, 6);
        setID(GRAY_CONCRETE, 251, 7);
        setID(LIGHT_GRAY_CONCRETE, 251, 8);
        setID(CYAN_CONCRETE, 251, 9);
        setID(PURPLE_CONCRETE, 251, 10);
        setID(BLUE_CONCRETE, 251, 11);
        setID(BROWN_CONCRETE, 251, 12);
        setID(GREEN_CONCRETE, 251, 13);
        setID(RED_CONCRETE, 251, 14);
        setID(BLACK_CONCRETE, 251, 15);
        setID(WHITE_CONCRETE_POWDER, 252);
        setID(ORANGE_CONCRETE_POWDER, 252, 1);
        setID(MAGENTA_CONCRETE_POWDER, 252, 2);
        setID(LIGHT_BLUE_CONCRETE_POWDER, 252, 3);
        setID(YELLOW_CONCRETE_POWDER, 252, 4);
        setID(LIME_CONCRETE_POWDER, 252, 5);
        setID(PINK_CONCRETE_POWDER, 252, 6);
        setID(GRAY_CONCRETE_POWDER, 252, 7);
        setID(LIGHT_GRAY_CONCRETE_POWDER, 252, 8);
        setID(CYAN_CONCRETE_POWDER, 252, 9);
        setID(PURPLE_CONCRETE_POWDER, 252, 10);
        setID(BLUE_CONCRETE_POWDER, 252, 11);
        setID(BROWN_CONCRETE_POWDER, 252, 12);
        setID(GREEN_CONCRETE_POWDER, 252, 13);
        setID(RED_CONCRETE_POWDER, 252, 14);
        setID(BLACK_CONCRETE_POWDER, 252, 15);
        setID(STRUCTURE_BLOCK, 255);
        setID(IRON_SHOVEL, 256);
        setID(IRON_PICKAXE, 257);
        setID(IRON_AXE, 258);
        setID(FLINT_AND_STEEL, 259);
        setID(APPLE, 260);
        setID(BOW, 261);
        setID(ARROW, 262);
        setID(COAL, 263);
        setID(CHARCOAL, 263, 1);
        setID(DIAMOND, 264);
        setID(IRON_INGOT, 265);
        setID(GOLD_INGOT, 266);
        setID(IRON_SWORD, 267);
        setID(WOODEN_SWORD, 268);
        setID(WOODEN_SHOVEL, 269);
        setID(WOODEN_PICKAXE, 270);
        setID(WOODEN_AXE, 271);
        setID(STONE_SWORD, 272);
        setID(STONE_SHOVEL, 273);
        setID(STONE_PICKAXE, 274);
        setID(STONE_AXE, 275);
        setID(DIAMOND_SWORD, 276);
        setID(DIAMOND_SHOVEL, 277);
        setID(DIAMOND_PICKAXE, 278);
        setID(DIAMOND_AXE, 279);
        setID(STICK, 280);
        setID(BOWL, 281);
        setID(MUSHROOM_STEW, 282);
        setID(GOLDEN_SWORD, 283);
        setID(GOLDEN_SHOVEL, 284);
        setID(GOLDEN_PICKAXE, 285);
        setID(GOLDEN_AXE, 286);
        setID(STRING, 287);
        setID(FEATHER, 288);
        setID(GUNPOWDER, 289);
        setID(WOODEN_HOE, 290);
        setID(STONE_HOE, 291);
        setID(IRON_HOE, 292);
        setID(DIAMOND_HOE, 293);
        setID(GOLDEN_HOE, 294);
        setID(WHEAT_SEEDS, 295);
        setID(WHEAT, 296);
        setID(BREAD, 297);
        setID(LEATHER_HELMET, 298);
        setID(LEATHER_CHESTPLATE, 299);
        setID(LEATHER_LEGGINGS, 300);
        setID(LEATHER_BOOTS, 301);
        setID(CHAINMAIL_HELMET, 302);
        setID(CHAINMAIL_CHESTPLATE, 303);
        setID(CHAINMAIL_LEGGINGS, 304);
        setID(CHAINMAIL_BOOTS, 305);
        setID(IRON_HELMET, 306);
        setID(IRON_CHESTPLATE, 307);
        setID(IRON_LEGGINGS, 308);
        setID(IRON_BOOTS, 309);
        setID(DIAMOND_HELMET, 310);
        setID(DIAMOND_CHESTPLATE, 311);
        setID(DIAMOND_LEGGINGS, 312);
        setID(DIAMOND_BOOTS, 313);
        setID(GOLDEN_HELMET, 314);
        setID(GOLDEN_CHESTPLATE, 315);
        setID(GOLDEN_LEGGINGS, 316);
        setID(GOLDEN_BOOTS, 317);
        setID(FLINT, 318);
        setID(PORKCHOP, 319);
        setID(COOKED_PORKCHOP, 320);
        setID(PAINTING, 321);
        setID(GOLDEN_APPLE, 322);
        setID(ENCHANTED_GOLDEN_APPLE, 322, 1);
        setID(OAK_SIGN, 323);
        setID(OAK_DOOR, 324);
        setID(BUCKET, 325);
        setID(WATER_BUCKET, 326);
        setID(LAVA_BUCKET, 327);
        setID(MINECART, 328);
        setID(SADDLE, 329);
        setID(IRON_DOOR, 330);
        setID(REDSTONE, 331);
        setID(SNOWBALL, 332);
        setID(OAK_BOAT, 333);
        setID(LEATHER, 334);
        setID(MILK_BUCKET, 335);
        setID(BRICK, 336);
        setID(CLAY, 337);
        setID(SUGAR_CANE, 338);
        setID(PAPER, 339);
        setID(BOOK, 340);
        setID(SLIME_BALL, 341);
        setID(CHEST_MINECART, 342);
        setID(FURNACE_MINECART, 343);
        setID(EGG, 344);
        setID(COMPASS, 345);
        setID(FISHING_ROD, 346);
        setID(CLOCK, 347);
        setID(GLOWSTONE_DUST, 348);
//        setID(RAW_FISH, 349);
        setID(SALMON, 349, 1);
        setID(TROPICAL_FISH, 349, 2);
        setID(PUFFERFISH, 349, 3);
//        setID(COOKED_FISH, 350);
        setID(COOKED_SALMON, 350, 1);
        setID(INK_SAC, 351);
        setID(RED_DYE, 351, 1);
        setID(GREEN_DYE, 351, 2);
        setID(COCOA_BEANS, 351, 3);
        setID(LAPIS_LAZULI, 351, 4);
        setID(PURPLE_DYE, 351, 5);
        setID(CYAN_DYE, 351, 6);
        setID(LIGHT_GRAY_DYE, 351, 7);
        setID(GRAY_DYE, 351, 8);
        setID(PINK_DYE, 351, 9);
        setID(LIME_DYE, 351, 10);
        setID(YELLOW_DYE, 351, 11);
        setID(LIGHT_BLUE_DYE, 351, 12);
        setID(MAGENTA_DYE, 351, 13);
        setID(ORANGE_DYE, 351, 14);
        setID(BONE_MEAL, 351, 15);
        setID(BONE, 352);
        setID(SUGAR, 353);
        setID(CAKE, 354);
//        setID(BED, 355);
        setID(REPEATER, 356);
        setID(COOKIE, 357);
        setID(MAP, 358);
        setID(SHEARS, 359);
        setID(MELON, 360);
        setID(PUMPKIN_SEEDS, 361);
        setID(MELON_SEEDS, 362);
//        setID(RAW_BEEF, 363);
//        setID(STEAK, 364);
//        setID(RAW_CHICKEN, 365);
        setID(COOKED_CHICKEN, 366);
        setID(ROTTEN_FLESH, 367);
        setID(ENDER_PEARL, 368);
        setID(BLAZE_ROD, 369);
        setID(GHAST_TEAR, 370);
        setID(GOLD_NUGGET, 371);
        setID(NETHER_WART, 372);
        setID(POTION, 373);
        setID(GLASS_BOTTLE, 374);
        setID(SPIDER_EYE, 375);
        setID(FERMENTED_SPIDER_EYE, 376);
        setID(BLAZE_POWDER, 377);
        setID(MAGMA_CREAM, 378);
        setID(BREWING_STAND, 379);
        setID(CAULDRON, 380);
        setID(ENDER_EYE, 381);
        setID(GLISTERING_MELON_SLICE, 382);
/*        setID(ELDER_GUARDIAN_SPAWN_EGG, 383, 4);
        setID(WITHER_SKELETON_SPAWN_EGG, 383, 5);
        setID(STRAY_SPAWN_EGG, 383, 6);
        setID(HUSK_SPAWN_EGG, 383, 23);
        setID(ZOMBIE_VILLAGER_SPAWN_EGG, 383, 27);
        setID(SKELETON_HORSE_SPAWN_EGG, 383, 28);
        setID(ZOMBIE_HORSE_SPAWN_EGG, 383, 29);
        setID(DONKEY_SPAWN_EGG, 383, 31);
        setID(MULE_SPAWN_EGG, 383, 32);
        setID(EVOKER_SPAWN_EGG, 383, 34);
        setID(VEX_SPAWN_EGG, 383, 35);
        setID(VINDICATOR_SPAWN_EGG, 383, 36);
        setID(CREEPER_SPAWN_EGG, 383, 50);
        setID(SKELETON_SPAWN_EGG, 383, 51);
        setID(SPIDER_SPAWN_EGG, 383, 52);
        setID(ZOMBIE_SPAWN_EGG, 383, 54);
        setID(SLIME_SPAWN_EGG, 383, 55);
        setID(GHAST_SPAWN_EGG, 383, 56);
        setID(ZOMBIE_PIGMAN_SPAWN_EGG, 383, 57);
        setID(ENDERMAN_SPAWN_EGG, 383, 58);
        setID(CAVE_SPIDER_SPAWN_EGG, 383, 59);
        setID(SILVERFISH_SPAWN_EGG, 383, 60);
        setID(BLAZE_SPAWN_EGG, 383, 61);
        setID(MAGMA_CUBE_SPAWN_EGG, 383, 62);
        setID(BAT_SPAWN_EGG, 383, 65);
        setID(WITCH_SPAWN_EGG, 383, 66);
        setID(ENDERMITE_SPAWN_EGG, 383, 67);
        setID(GUARDIAN_SPAWN_EGG, 383, 68);
        setID(SHULKER_SPAWN_EGG, 383, 69);
        setID(PIG_SPAWN_EGG, 383, 90);
        setID(SHEEP_SPAWN_EGG, 383, 91);
        setID(COW_SPAWN_EGG, 383, 92);
        setID(CHICKEN_SPAWN_EGG, 383, 93);
        setID(SQUID_SPAWN_EGG, 383, 94);
        setID(WOLF_SPAWN_EGG, 383, 95);
        setID(MOOSHROOM_SPAWN_EGG, 383, 96);
        setID(OCELOT_SPAWN_EGG, 383, 98);
        setID(HORSE_SPAWN_EGG, 383, 100);
        setID(RABBIT_SPAWN_EGG, 383, 101);
        setID(POLAR_BEAR_SPAWN_EGG, 383, 102);
        setID(LLAMA_SPAWN_EGG, 383, 103);
        setID(PARROT_SPAWN_EGG, 383, 105);
        setID(VILLAGER_SPAWN_EGG, 383, 120);*/
        setID(EXPERIENCE_BOTTLE, 384);
        setID(FIRE_CHARGE, 385);
        setID(WRITABLE_BOOK, 386);
        setID(WRITTEN_BOOK, 387);
        setID(EMERALD, 388);
        setID(ITEM_FRAME, 389);
        setID(FLOWER_POT, 390);
        setID(CARROT, 391);
        setID(POTATO, 392);
        setID(BAKED_POTATO, 393);
        setID(POISONOUS_POTATO, 394);
        setID(MAP, 395);
        setID(GOLDEN_CARROT, 396);
//        setID(MOB_HEAD_SKELETON, 397);
////      setID(MOB_HEAD_WITHER_SKELETON, 397:1); //TODO: Add Metadata workaround
////      setID(MOB_HEAD_[¬º-°]¬, 397:2); //TODO: Add Metadata workaround
////      setID(MOB_HEAD_HUMAN, 397:3); //TODO: Add Metadata workaround
////      setID(MOB_HEAD_CREEPER, 397:4); //TODO: Add Metadata workaround
////      setID(MOB_HEAD_DRAGON, 397:5); //TODO: Add Metadata workaround
        setID(CARROT_ON_A_STICK, 398);
        setID(NETHER_STAR, 399);
        setID(PUMPKIN_PIE, 400);
        setID(FIREWORK_ROCKET, 401);
        setID(FIREWORK_STAR, 402);
        setID(ENCHANTED_BOOK, 403);
        setID(COMPARATOR, 404);
        setID(NETHER_BRICK, 405);
        setID(NETHER_QUARTZ_ORE, 406);
        setID(TNT_MINECART, 407);
        setID(HOPPER_MINECART, 408);
        setID(PRISMARINE_SHARD, 409);
        setID(PRISMARINE_CRYSTALS, 410);
        setID(RABBIT, 411);
        setID(COOKED_RABBIT, 412);
        setID(RABBIT_STEW, 413);
        setID(RABBIT_FOOT, 414);
        setID(RABBIT_HIDE, 415);
        setID(ARMOR_STAND, 416);
        setID(IRON_HORSE_ARMOR, 417);
        setID(GOLDEN_HORSE_ARMOR, 418);
        setID(DIAMOND_HORSE_ARMOR, 419);
        setID(LEAD, 420);
        setID(NAME_TAG, 421);
        setID(COMMAND_BLOCK_MINECART, 422);
        setID(MUTTON, 423);
        setID(COOKED_MUTTON, 424);
//        setID(BANNER, 425);
        setID(END_CRYSTAL, 426);
        setID(SPRUCE_DOOR, 427);
        setID(BIRCH_DOOR, 428);
        setID(JUNGLE_DOOR, 429);
        setID(ACACIA_DOOR, 430);
        setID(DARK_OAK_DOOR, 431);
        setID(CHORUS_FRUIT, 432);
        setID(POPPED_CHORUS_FRUIT, 433);
        setID(BEETROOT, 434);
        setID(BEETROOT_SEEDS, 435);
        setID(BEETROOT_SOUP, 436);
        setID(DRAGON_BREATH, 437);
        setID(SPLASH_POTION, 438);
        setID(SPECTRAL_ARROW, 439);
        setID(TIPPED_ARROW, 440);
        setID(LINGERING_POTION, 441);
        setID(SHIELD, 442);
        setID(ELYTRA, 443);
        setID(SPRUCE_BOAT, 444);
        setID(BIRCH_BOAT, 445);
        setID(JUNGLE_BOAT, 446);
        setID(ACACIA_BOAT, 447);
        setID(DARK_OAK_BOAT, 448);
        setID(TOTEM_OF_UNDYING, 449);
        setID(SHULKER_SHELL, 450);
        setID(IRON_NUGGET, 452);
        setID(KNOWLEDGE_BOOK, 453);
        setID(MUSIC_DISC_13, 2256);
        setID(MUSIC_DISC_CAT, 2257);
        setID(MUSIC_DISC_BLOCKS, 2258);
        setID(MUSIC_DISC_CHIRP, 2259);
        setID(MUSIC_DISC_FAR, 2260);
        setID(MUSIC_DISC_MALL, 2261);
        setID(MUSIC_DISC_MELLOHI, 2262);
        setID(MUSIC_DISC_STAL, 2263);
        setID(MUSIC_DISC_STRAD, 2264);
        setID(MUSIC_DISC_WARD, 2265);
        setID(MUSIC_DISC_11, 2266);
        setID(MUSIC_DISC_WAIT, 2267);
    }
}
