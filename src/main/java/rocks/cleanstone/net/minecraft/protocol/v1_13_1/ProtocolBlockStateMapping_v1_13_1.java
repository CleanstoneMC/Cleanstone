package rocks.cleanstone.net.minecraft.protocol.v1_13_1;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.ModernBlockStateMapping;

import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockType.*;

@Component("protocolBlockStateMapping_v1_13_1")
public class ProtocolBlockStateMapping_v1_13_1 extends ModernBlockStateMapping {

    public ProtocolBlockStateMapping_v1_13_1() {
        super(BlockState.of(STONE));
        setBaseID(AIR, 0);
        setBaseID(STONE, 1);
        setBaseID(GRANITE, 2);
        setBaseID(POLISHED_GRANITE, 3);
        setBaseID(DIORITE, 4);
        setBaseID(POLISHED_DIORITE, 5);
        setBaseID(ANDESITE, 6);
        setBaseID(POLISHED_ANDESITE, 7);
        setBaseID(GRASS_BLOCK, 8);
        setBaseID(DIRT, 10);
        setBaseID(COARSE_DIRT, 11);
        setBaseID(PODZOL, 12);
        setBaseID(COBBLESTONE, 14);
        setBaseID(OAK_PLANKS, 15);
        setBaseID(SPRUCE_PLANKS, 16);
        setBaseID(BIRCH_PLANKS, 17);
        setBaseID(JUNGLE_PLANKS, 18);
        setBaseID(ACACIA_PLANKS, 19);
        setBaseID(DARK_OAK_PLANKS, 20);
        setBaseID(OAK_SAPLING, 21);
        setBaseID(SPRUCE_SAPLING, 23);
        setBaseID(BIRCH_SAPLING, 25);
        setBaseID(JUNGLE_SAPLING, 27);
        setBaseID(ACACIA_SAPLING, 29);
        setBaseID(DARK_OAK_SAPLING, 31);
        setBaseID(BEDROCK, 33);
        setBaseID(WATER, 34);
        setBaseID(LAVA, 50);
        setBaseID(SAND, 66);
        setBaseID(RED_SAND, 67);
        setBaseID(GRAVEL, 68);
        setBaseID(GOLD_ORE, 69);
        setBaseID(IRON_ORE, 70);
        setBaseID(COAL_ORE, 71);
        setBaseID(OAK_LOG, 72);
        setBaseID(SPRUCE_LOG, 75);
        setBaseID(BIRCH_LOG, 78);
        setBaseID(JUNGLE_LOG, 81);
        setBaseID(ACACIA_LOG, 84);
        setBaseID(DARK_OAK_LOG, 87);
        setBaseID(STRIPPED_SPRUCE_LOG, 90);
        setBaseID(STRIPPED_BIRCH_LOG, 93);
        setBaseID(STRIPPED_JUNGLE_LOG, 96);
        setBaseID(STRIPPED_ACACIA_LOG, 99);
        setBaseID(STRIPPED_DARK_OAK_LOG, 102);
        setBaseID(STRIPPED_OAK_LOG, 105);
        setBaseID(OAK_WOOD, 108);
        setBaseID(SPRUCE_WOOD, 111);
        setBaseID(BIRCH_WOOD, 114);
        setBaseID(JUNGLE_WOOD, 117);
        setBaseID(ACACIA_WOOD, 120);
        setBaseID(DARK_OAK_WOOD, 123);
        setBaseID(STRIPPED_OAK_WOOD, 126);
        setBaseID(STRIPPED_SPRUCE_WOOD, 129);
        setBaseID(STRIPPED_BIRCH_WOOD, 132);
        setBaseID(STRIPPED_JUNGLE_WOOD, 135);
        setBaseID(STRIPPED_ACACIA_WOOD, 138);
        setBaseID(STRIPPED_DARK_OAK_WOOD, 141);
        setBaseID(OAK_LEAVES, 144);
        setBaseID(SPRUCE_LEAVES, 158);
        setBaseID(BIRCH_LEAVES, 172);
        setBaseID(JUNGLE_LEAVES, 186);
        setBaseID(ACACIA_LEAVES, 200);
        setBaseID(DARK_OAK_LEAVES, 214);
        setBaseID(SPONGE, 228);
        setBaseID(WET_SPONGE, 229);
        setBaseID(GLASS, 230);
        setBaseID(LAPIS_ORE, 231);
        setBaseID(LAPIS_BLOCK, 232);
        setBaseID(DISPENSER, 233);
        setBaseID(SANDSTONE, 245);
        setBaseID(CHISELED_SANDSTONE, 246);
        setBaseID(CUT_SANDSTONE, 247);
        setBaseID(NOTE_BLOCK, 248);
        setBaseID(WHITE_BED, 748);
        setBaseID(ORANGE_BED, 764);
        setBaseID(MAGENTA_BED, 780);
        setBaseID(LIGHT_BLUE_BED, 796);
        setBaseID(YELLOW_BED, 812);
        setBaseID(LIME_BED, 828);
        setBaseID(PINK_BED, 844);
        setBaseID(GRAY_BED, 860);
        setBaseID(LIGHT_GRAY_BED, 876);
        setBaseID(CYAN_BED, 892);
        setBaseID(PURPLE_BED, 908);
        setBaseID(BLUE_BED, 924);
        setBaseID(BROWN_BED, 940);
        setBaseID(GREEN_BED, 956);
        setBaseID(RED_BED, 972);
        setBaseID(BLACK_BED, 988);
        setBaseID(POWERED_RAIL, 1004);
        setBaseID(DETECTOR_RAIL, 1016);
        setBaseID(STICKY_PISTON, 1028);
        setBaseID(COBWEB, 1040);
        setBaseID(GRASS, 1041);
        setBaseID(FERN, 1042);
        setBaseID(DEAD_BUSH, 1043);
        setBaseID(SEAGRASS, 1044);
        setBaseID(TALL_SEAGRASS, 1045);
        setBaseID(PISTON, 1047);
        setBaseID(PISTON_HEAD, 1059);
        setBaseID(WHITE_WOOL, 1083);
        setBaseID(ORANGE_WOOL, 1084);
        setBaseID(MAGENTA_WOOL, 1085);
        setBaseID(LIGHT_BLUE_WOOL, 1086);
        setBaseID(YELLOW_WOOL, 1087);
        setBaseID(LIME_WOOL, 1088);
        setBaseID(PINK_WOOL, 1089);
        setBaseID(GRAY_WOOL, 1090);
        setBaseID(LIGHT_GRAY_WOOL, 1091);
        setBaseID(CYAN_WOOL, 1092);
        setBaseID(PURPLE_WOOL, 1093);
        setBaseID(BLUE_WOOL, 1094);
        setBaseID(BROWN_WOOL, 1095);
        setBaseID(GREEN_WOOL, 1096);
        setBaseID(RED_WOOL, 1097);
        setBaseID(BLACK_WOOL, 1098);
        setBaseID(MOVING_PISTON, 1099);
        setBaseID(DANDELION, 1111);
        setBaseID(POPPY, 1112);
        setBaseID(BLUE_ORCHID, 1113);
        setBaseID(ALLIUM, 1114);
        setBaseID(AZURE_BLUET, 1115);
        setBaseID(RED_TULIP, 1116);
        setBaseID(ORANGE_TULIP, 1117);
        setBaseID(WHITE_TULIP, 1118);
        setBaseID(PINK_TULIP, 1119);
        setBaseID(OXEYE_DAISY, 1120);
        setBaseID(BROWN_MUSHROOM, 1121);
        setBaseID(RED_MUSHROOM, 1122);
        setBaseID(GOLD_BLOCK, 1123);
        setBaseID(IRON_BLOCK, 1124);
        setBaseID(BRICKS, 1125);
        setBaseID(TNT, 1126);
        setBaseID(BOOKSHELF, 1127);
        setBaseID(MOSSY_COBBLESTONE, 1128);
        setBaseID(OBSIDIAN, 1129);
        setBaseID(TORCH, 1130);
        setBaseID(WALL_TORCH, 1131);
        setBaseID(FIRE, 1135);
        setBaseID(SPAWNER, 1647);
        setBaseID(OAK_STAIRS, 1648);
        setBaseID(CHEST, 1728);
        setBaseID(REDSTONE_WIRE, 1752);
        setBaseID(DIAMOND_ORE, 3048);
        setBaseID(DIAMOND_BLOCK, 3049);
        setBaseID(CRAFTING_TABLE, 3050);
        setBaseID(WHEAT, 3051);
        setBaseID(FARMLAND, 3059);
        setBaseID(FURNACE, 3067);
        setBaseID(SIGN, 3075);
        setBaseID(OAK_DOOR, 3107);
        setBaseID(LADDER, 3171);
        setBaseID(RAIL, 3179);
        setBaseID(COBBLESTONE_STAIRS, 3189);
        setBaseID(WALL_SIGN, 3269);
        setBaseID(LEVER, 3277);
        setBaseID(STONE_PRESSURE_PLATE, 3301);
        setBaseID(IRON_DOOR, 3303);
        setBaseID(OAK_PRESSURE_PLATE, 3367);
        setBaseID(SPRUCE_PRESSURE_PLATE, 3369);
        setBaseID(BIRCH_PRESSURE_PLATE, 3371);
        setBaseID(JUNGLE_PRESSURE_PLATE, 3373);
        setBaseID(ACACIA_PRESSURE_PLATE, 3375);
        setBaseID(DARK_OAK_PRESSURE_PLATE, 3377);
        setBaseID(REDSTONE_ORE, 3379);
        setBaseID(REDSTONE_TORCH, 3381);
        setBaseID(REDSTONE_WALL_TORCH, 3383);
        setBaseID(STONE_BUTTON, 3391);
        setBaseID(SNOW, 3415);
        setBaseID(ICE, 3423);
        setBaseID(SNOW_BLOCK, 3424);
        setBaseID(CACTUS, 3425);
        setBaseID(CLAY, 3441);
        setBaseID(SUGAR_CANE, 3442);
        setBaseID(JUKEBOX, 3458);
        setBaseID(OAK_FENCE, 3460);
        setBaseID(PUMPKIN, 3492);
        setBaseID(NETHERRACK, 3493);
        setBaseID(SOUL_SAND, 3494);
        setBaseID(GLOWSTONE, 3495);
        setBaseID(NETHER_PORTAL, 3496);
        setBaseID(CARVED_PUMPKIN, 3498);
        setBaseID(JACK_O_LANTERN, 3502);
        setBaseID(CAKE, 3506);
        setBaseID(REPEATER, 3513);
        setBaseID(WHITE_STAINED_GLASS, 3577);
        setBaseID(ORANGE_STAINED_GLASS, 3578);
        setBaseID(MAGENTA_STAINED_GLASS, 3579);
        setBaseID(LIGHT_BLUE_STAINED_GLASS, 3580);
        setBaseID(YELLOW_STAINED_GLASS, 3581);
        setBaseID(LIME_STAINED_GLASS, 3582);
        setBaseID(PINK_STAINED_GLASS, 3583);
        setBaseID(GRAY_STAINED_GLASS, 3584);
        setBaseID(LIGHT_GRAY_STAINED_GLASS, 3585);
        setBaseID(CYAN_STAINED_GLASS, 3586);
        setBaseID(PURPLE_STAINED_GLASS, 3587);
        setBaseID(BLUE_STAINED_GLASS, 3588);
        setBaseID(BROWN_STAINED_GLASS, 3589);
        setBaseID(GREEN_STAINED_GLASS, 3590);
        setBaseID(RED_STAINED_GLASS, 3591);
        setBaseID(BLACK_STAINED_GLASS, 3592);
        setBaseID(OAK_TRAPDOOR, 3593);
        setBaseID(SPRUCE_TRAPDOOR, 3657);
        setBaseID(BIRCH_TRAPDOOR, 3721);
        setBaseID(JUNGLE_TRAPDOOR, 3785);
        setBaseID(ACACIA_TRAPDOOR, 3849);
        setBaseID(DARK_OAK_TRAPDOOR, 3913);
        setBaseID(INFESTED_STONE, 3977);
        setBaseID(INFESTED_COBBLESTONE, 3978);
        setBaseID(INFESTED_STONE_BRICKS, 3979);
        setBaseID(INFESTED_MOSSY_STONE_BRICKS, 3980);
        setBaseID(INFESTED_CRACKED_STONE_BRICKS, 3981);
        setBaseID(INFESTED_CHISELED_STONE_BRICKS, 3982);
        setBaseID(STONE_BRICKS, 3983);
        setBaseID(MOSSY_STONE_BRICKS, 3984);
        setBaseID(CRACKED_STONE_BRICKS, 3985);
        setBaseID(CHISELED_STONE_BRICKS, 3986);
        setBaseID(BROWN_MUSHROOM_BLOCK, 3987);
        setBaseID(RED_MUSHROOM_BLOCK, 4051);
        setBaseID(MUSHROOM_STEM, 4115);
        setBaseID(IRON_BARS, 4179);
        setBaseID(GLASS_PANE, 4211);
        setBaseID(MELON, 4243);
        setBaseID(ATTACHED_PUMPKIN_STEM, 4244);
        setBaseID(ATTACHED_MELON_STEM, 4248);
        setBaseID(PUMPKIN_STEM, 4252);
        setBaseID(MELON_STEM, 4260);
        setBaseID(VINE, 4268);
        setBaseID(OAK_FENCE_GATE, 4300);
        setBaseID(BRICK_STAIRS, 4332);
        setBaseID(STONE_BRICK_STAIRS, 4412);
        setBaseID(MYCELIUM, 4492);
        setBaseID(LILY_PAD, 4494);
        setBaseID(NETHER_BRICKS, 4495);
        setBaseID(NETHER_BRICK_FENCE, 4496);
        setBaseID(NETHER_BRICK_STAIRS, 4528);
        setBaseID(NETHER_WART, 4608);
        setBaseID(ENCHANTING_TABLE, 4612);
        setBaseID(BREWING_STAND, 4613);
        setBaseID(CAULDRON, 4621);
        setBaseID(END_PORTAL, 4625);
        setBaseID(END_PORTAL_FRAME, 4626);
        setBaseID(END_STONE, 4634);
        setBaseID(DRAGON_EGG, 4635);
        setBaseID(REDSTONE_LAMP, 4636);
        setBaseID(COCOA, 4638);
        setBaseID(SANDSTONE_STAIRS, 4650);
        setBaseID(EMERALD_ORE, 4730);
        setBaseID(ENDER_CHEST, 4731);
        setBaseID(TRIPWIRE_HOOK, 4739);
        setBaseID(TRIPWIRE, 4755);
        setBaseID(EMERALD_BLOCK, 4883);
        setBaseID(SPRUCE_STAIRS, 4884);
        setBaseID(BIRCH_STAIRS, 4964);
        setBaseID(JUNGLE_STAIRS, 5044);
        setBaseID(COMMAND_BLOCK, 5124);
        setBaseID(BEACON, 5136);
        setBaseID(COBBLESTONE_WALL, 5137);
        setBaseID(MOSSY_COBBLESTONE_WALL, 5201);
        setBaseID(FLOWER_POT, 5265);
        setBaseID(POTTED_OAK_SAPLING, 5266);
        setBaseID(POTTED_SPRUCE_SAPLING, 5267);
        setBaseID(POTTED_BIRCH_SAPLING, 5268);
        setBaseID(POTTED_JUNGLE_SAPLING, 5269);
        setBaseID(POTTED_ACACIA_SAPLING, 5270);
        setBaseID(POTTED_DARK_OAK_SAPLING, 5271);
        setBaseID(POTTED_FERN, 5272);
        setBaseID(POTTED_DANDELION, 5273);
        setBaseID(POTTED_POPPY, 5274);
        setBaseID(POTTED_BLUE_ORCHID, 5275);
        setBaseID(POTTED_ALLIUM, 5276);
        setBaseID(POTTED_AZURE_BLUET, 5277);
        setBaseID(POTTED_RED_TULIP, 5278);
        setBaseID(POTTED_ORANGE_TULIP, 5279);
        setBaseID(POTTED_WHITE_TULIP, 5280);
        setBaseID(POTTED_PINK_TULIP, 5281);
        setBaseID(POTTED_OXEYE_DAISY, 5282);
        setBaseID(POTTED_RED_MUSHROOM, 5283);
        setBaseID(POTTED_BROWN_MUSHROOM, 5284);
        setBaseID(POTTED_DEAD_BUSH, 5285);
        setBaseID(POTTED_CACTUS, 5286);
        setBaseID(CARROTS, 5287);
        setBaseID(POTATOES, 5295);
        setBaseID(OAK_BUTTON, 5303);
        setBaseID(SPRUCE_BUTTON, 5327);
        setBaseID(BIRCH_BUTTON, 5351);
        setBaseID(JUNGLE_BUTTON, 5375);
        setBaseID(ACACIA_BUTTON, 5399);
        setBaseID(DARK_OAK_BUTTON, 5423);
        setBaseID(SKELETON_WALL_SKULL, 5447);
        setBaseID(SKELETON_SKULL, 5451);
        setBaseID(WITHER_SKELETON_WALL_SKULL, 5467);
        setBaseID(WITHER_SKELETON_SKULL, 5471);
        setBaseID(ZOMBIE_WALL_HEAD, 5487);
        setBaseID(ZOMBIE_HEAD, 5491);
        setBaseID(PLAYER_WALL_HEAD, 5507);
        setBaseID(PLAYER_HEAD, 5511);
        setBaseID(CREEPER_WALL_HEAD, 5527);
        setBaseID(CREEPER_HEAD, 5531);
        setBaseID(DRAGON_WALL_HEAD, 5547);
        setBaseID(DRAGON_HEAD, 5551);
        setBaseID(ANVIL, 5567);
        setBaseID(CHIPPED_ANVIL, 5571);
        setBaseID(DAMAGED_ANVIL, 5575);
        setBaseID(TRAPPED_CHEST, 5579);
        setBaseID(LIGHT_WEIGHTED_PRESSURE_PLATE, 5603);
        setBaseID(HEAVY_WEIGHTED_PRESSURE_PLATE, 5619);
        setBaseID(COMPARATOR, 5635);
        setBaseID(DAYLIGHT_DETECTOR, 5651);
        setBaseID(REDSTONE_BLOCK, 5683);
        setBaseID(NETHER_QUARTZ_ORE, 5684);
        setBaseID(HOPPER, 5685);
        setBaseID(QUARTZ_BLOCK, 5695);
        setBaseID(CHISELED_QUARTZ_BLOCK, 5696);
        setBaseID(QUARTZ_PILLAR, 5697);
        setBaseID(QUARTZ_STAIRS, 5700);
        setBaseID(ACTIVATOR_RAIL, 5780);
        setBaseID(DROPPER, 5792);
        setBaseID(WHITE_TERRACOTTA, 5804);
        setBaseID(ORANGE_TERRACOTTA, 5805);
        setBaseID(MAGENTA_TERRACOTTA, 5806);
        setBaseID(LIGHT_BLUE_TERRACOTTA, 5807);
        setBaseID(YELLOW_TERRACOTTA, 5808);
        setBaseID(LIME_TERRACOTTA, 5809);
        setBaseID(PINK_TERRACOTTA, 5810);
        setBaseID(GRAY_TERRACOTTA, 5811);
        setBaseID(LIGHT_GRAY_TERRACOTTA, 5812);
        setBaseID(CYAN_TERRACOTTA, 5813);
        setBaseID(PURPLE_TERRACOTTA, 5814);
        setBaseID(BLUE_TERRACOTTA, 5815);
        setBaseID(BROWN_TERRACOTTA, 5816);
        setBaseID(GREEN_TERRACOTTA, 5817);
        setBaseID(RED_TERRACOTTA, 5818);
        setBaseID(BLACK_TERRACOTTA, 5819);
        setBaseID(WHITE_STAINED_GLASS_PANE, 5820);
        setBaseID(ORANGE_STAINED_GLASS_PANE, 5852);
        setBaseID(MAGENTA_STAINED_GLASS_PANE, 5884);
        setBaseID(LIGHT_BLUE_STAINED_GLASS_PANE, 5916);
        setBaseID(YELLOW_STAINED_GLASS_PANE, 5948);
        setBaseID(LIME_STAINED_GLASS_PANE, 5980);
        setBaseID(PINK_STAINED_GLASS_PANE, 6012);
        setBaseID(GRAY_STAINED_GLASS_PANE, 6044);
        setBaseID(LIGHT_GRAY_STAINED_GLASS_PANE, 6076);
        setBaseID(CYAN_STAINED_GLASS_PANE, 6108);
        setBaseID(PURPLE_STAINED_GLASS_PANE, 6140);
        setBaseID(BLUE_STAINED_GLASS_PANE, 6172);
        setBaseID(BROWN_STAINED_GLASS_PANE, 6204);
        setBaseID(GREEN_STAINED_GLASS_PANE, 6236);
        setBaseID(RED_STAINED_GLASS_PANE, 6268);
        setBaseID(BLACK_STAINED_GLASS_PANE, 6300);
        setBaseID(ACACIA_STAIRS, 6332);
        setBaseID(DARK_OAK_STAIRS, 6412);
        setBaseID(SLIME_BLOCK, 6492);
        setBaseID(BARRIER, 6493);
        setBaseID(IRON_TRAPDOOR, 6494);
        setBaseID(PRISMARINE, 6558);
        setBaseID(PRISMARINE_BRICKS, 6559);
        setBaseID(DARK_PRISMARINE, 6560);
        setBaseID(PRISMARINE_STAIRS, 6561);
        setBaseID(PRISMARINE_BRICK_STAIRS, 6641);
        setBaseID(DARK_PRISMARINE_STAIRS, 6721);
        setBaseID(PRISMARINE_SLAB, 6801);
        setBaseID(PRISMARINE_BRICK_SLAB, 6807);
        setBaseID(DARK_PRISMARINE_SLAB, 6813);
        setBaseID(SEA_LANTERN, 6819);
        setBaseID(HAY_BLOCK, 6820);
        setBaseID(WHITE_CARPET, 6823);
        setBaseID(ORANGE_CARPET, 6824);
        setBaseID(MAGENTA_CARPET, 6825);
        setBaseID(LIGHT_BLUE_CARPET, 6826);
        setBaseID(YELLOW_CARPET, 6827);
        setBaseID(LIME_CARPET, 6828);
        setBaseID(PINK_CARPET, 6829);
        setBaseID(GRAY_CARPET, 6830);
        setBaseID(LIGHT_GRAY_CARPET, 6831);
        setBaseID(CYAN_CARPET, 6832);
        setBaseID(PURPLE_CARPET, 6833);
        setBaseID(BLUE_CARPET, 6834);
        setBaseID(BROWN_CARPET, 6835);
        setBaseID(GREEN_CARPET, 6836);
        setBaseID(RED_CARPET, 6837);
        setBaseID(BLACK_CARPET, 6838);
        setBaseID(TERRACOTTA, 6839);
        setBaseID(COAL_BLOCK, 6840);
        setBaseID(PACKED_ICE, 6841);
        setBaseID(SUNFLOWER, 6842);
        setBaseID(LILAC, 6844);
        setBaseID(ROSE_BUSH, 6846);
        setBaseID(PEONY, 6848);
        setBaseID(TALL_GRASS, 6850);
        setBaseID(LARGE_FERN, 6852);
        setBaseID(WHITE_BANNER, 6854);
        setBaseID(ORANGE_BANNER, 6870);
        setBaseID(MAGENTA_BANNER, 6886);
        setBaseID(LIGHT_BLUE_BANNER, 6902);
        setBaseID(YELLOW_BANNER, 6918);
        setBaseID(LIME_BANNER, 6934);
        setBaseID(PINK_BANNER, 6950);
        setBaseID(GRAY_BANNER, 6966);
        setBaseID(LIGHT_GRAY_BANNER, 6982);
        setBaseID(CYAN_BANNER, 6998);
        setBaseID(PURPLE_BANNER, 7014);
        setBaseID(BLUE_BANNER, 7030);
        setBaseID(BROWN_BANNER, 7046);
        setBaseID(GREEN_BANNER, 7062);
        setBaseID(RED_BANNER, 7078);
        setBaseID(BLACK_BANNER, 7094);
        setBaseID(WHITE_WALL_BANNER, 7110);
        setBaseID(ORANGE_WALL_BANNER, 7114);
        setBaseID(MAGENTA_WALL_BANNER, 7118);
        setBaseID(LIGHT_BLUE_WALL_BANNER, 7122);
        setBaseID(YELLOW_WALL_BANNER, 7126);
        setBaseID(LIME_WALL_BANNER, 7130);
        setBaseID(PINK_WALL_BANNER, 7134);
        setBaseID(GRAY_WALL_BANNER, 7138);
        setBaseID(LIGHT_GRAY_WALL_BANNER, 7142);
        setBaseID(CYAN_WALL_BANNER, 7146);
        setBaseID(PURPLE_WALL_BANNER, 7150);
        setBaseID(BLUE_WALL_BANNER, 7154);
        setBaseID(BROWN_WALL_BANNER, 7158);
        setBaseID(GREEN_WALL_BANNER, 7162);
        setBaseID(RED_WALL_BANNER, 7166);
        setBaseID(BLACK_WALL_BANNER, 7170);
        setBaseID(RED_SANDSTONE, 7174);
        setBaseID(CHISELED_RED_SANDSTONE, 7175);
        setBaseID(CUT_RED_SANDSTONE, 7176);
        setBaseID(RED_SANDSTONE_STAIRS, 7177);
        setBaseID(OAK_SLAB, 7257);
        setBaseID(SPRUCE_SLAB, 7263);
        setBaseID(BIRCH_SLAB, 7269);
        setBaseID(JUNGLE_SLAB, 7275);
        setBaseID(ACACIA_SLAB, 7281);
        setBaseID(DARK_OAK_SLAB, 7287);
        setBaseID(STONE_SLAB, 7293);
        setBaseID(SANDSTONE_SLAB, 7299);
        setBaseID(PETRIFIED_OAK_SLAB, 7305);
        setBaseID(COBBLESTONE_SLAB, 7311);
        setBaseID(BRICK_SLAB, 7317);
        setBaseID(STONE_BRICK_SLAB, 7323);
        setBaseID(NETHER_BRICK_SLAB, 7329);
        setBaseID(QUARTZ_SLAB, 7335);
        setBaseID(RED_SANDSTONE_SLAB, 7341);
        setBaseID(PURPUR_SLAB, 7347);
        setBaseID(SMOOTH_STONE, 7353);
        setBaseID(SMOOTH_SANDSTONE, 7354);
        setBaseID(SMOOTH_QUARTZ, 7355);
        setBaseID(SMOOTH_RED_SANDSTONE, 7356);
        setBaseID(SPRUCE_FENCE_GATE, 7357);
        setBaseID(BIRCH_FENCE_GATE, 7389);
        setBaseID(JUNGLE_FENCE_GATE, 7421);
        setBaseID(ACACIA_FENCE_GATE, 7453);
        setBaseID(DARK_OAK_FENCE_GATE, 7485);
        setBaseID(SPRUCE_FENCE, 7517);
        setBaseID(BIRCH_FENCE, 7549);
        setBaseID(JUNGLE_FENCE, 7581);
        setBaseID(ACACIA_FENCE, 7613);
        setBaseID(DARK_OAK_FENCE, 7645);
        setBaseID(SPRUCE_DOOR, 7677);
        setBaseID(BIRCH_DOOR, 7741);
        setBaseID(JUNGLE_DOOR, 7805);
        setBaseID(ACACIA_DOOR, 7869);
        setBaseID(DARK_OAK_DOOR, 7933);
        setBaseID(END_ROD, 7997);
        setBaseID(CHORUS_PLANT, 8003);
        setBaseID(CHORUS_FLOWER, 8067);
        setBaseID(PURPUR_BLOCK, 8073);
        setBaseID(PURPUR_PILLAR, 8074);
        setBaseID(PURPUR_STAIRS, 8077);
        setBaseID(END_STONE_BRICKS, 8157);
        setBaseID(BEETROOTS, 8158);
        setBaseID(GRASS_PATH, 8162);
        setBaseID(END_GATEWAY, 8163);
        setBaseID(REPEATING_COMMAND_BLOCK, 8164);
        setBaseID(CHAIN_COMMAND_BLOCK, 8176);
        setBaseID(FROSTED_ICE, 8188);
        setBaseID(MAGMA_BLOCK, 8192);
        setBaseID(NETHER_WART_BLOCK, 8193);
        setBaseID(RED_NETHER_BRICKS, 8194);
        setBaseID(BONE_BLOCK, 8195);
        setBaseID(STRUCTURE_VOID, 8198);
        setBaseID(OBSERVER, 8199);
        setBaseID(SHULKER_BOX, 8211);
        setBaseID(WHITE_SHULKER_BOX, 8217);
        setBaseID(ORANGE_SHULKER_BOX, 8223);
        setBaseID(MAGENTA_SHULKER_BOX, 8229);
        setBaseID(LIGHT_BLUE_SHULKER_BOX, 8235);
        setBaseID(YELLOW_SHULKER_BOX, 8241);
        setBaseID(LIME_SHULKER_BOX, 8247);
        setBaseID(PINK_SHULKER_BOX, 8253);
        setBaseID(GRAY_SHULKER_BOX, 8259);
        setBaseID(LIGHT_GRAY_SHULKER_BOX, 8265);
        setBaseID(CYAN_SHULKER_BOX, 8271);
        setBaseID(PURPLE_SHULKER_BOX, 8277);
        setBaseID(BLUE_SHULKER_BOX, 8283);
        setBaseID(BROWN_SHULKER_BOX, 8289);
        setBaseID(GREEN_SHULKER_BOX, 8295);
        setBaseID(RED_SHULKER_BOX, 8301);
        setBaseID(BLACK_SHULKER_BOX, 8307);
        setBaseID(WHITE_GLAZED_TERRACOTTA, 8313);
        setBaseID(ORANGE_GLAZED_TERRACOTTA, 8317);
        setBaseID(MAGENTA_GLAZED_TERRACOTTA, 8321);
        setBaseID(LIGHT_BLUE_GLAZED_TERRACOTTA, 8325);
        setBaseID(YELLOW_GLAZED_TERRACOTTA, 8329);
        setBaseID(LIME_GLAZED_TERRACOTTA, 8333);
        setBaseID(PINK_GLAZED_TERRACOTTA, 8337);
        setBaseID(GRAY_GLAZED_TERRACOTTA, 8341);
        setBaseID(LIGHT_GRAY_GLAZED_TERRACOTTA, 8345);
        setBaseID(CYAN_GLAZED_TERRACOTTA, 8349);
        setBaseID(PURPLE_GLAZED_TERRACOTTA, 8353);
        setBaseID(BLUE_GLAZED_TERRACOTTA, 8357);
        setBaseID(BROWN_GLAZED_TERRACOTTA, 8361);
        setBaseID(GREEN_GLAZED_TERRACOTTA, 8365);
        setBaseID(RED_GLAZED_TERRACOTTA, 8369);
        setBaseID(BLACK_GLAZED_TERRACOTTA, 8373);
        setBaseID(WHITE_CONCRETE, 8377);
        setBaseID(ORANGE_CONCRETE, 8378);
        setBaseID(MAGENTA_CONCRETE, 8379);
        setBaseID(LIGHT_BLUE_CONCRETE, 8380);
        setBaseID(YELLOW_CONCRETE, 8381);
        setBaseID(LIME_CONCRETE, 8382);
        setBaseID(PINK_CONCRETE, 8383);
        setBaseID(GRAY_CONCRETE, 8384);
        setBaseID(LIGHT_GRAY_CONCRETE, 8385);
        setBaseID(CYAN_CONCRETE, 8386);
        setBaseID(PURPLE_CONCRETE, 8387);
        setBaseID(BLUE_CONCRETE, 8388);
        setBaseID(BROWN_CONCRETE, 8389);
        setBaseID(GREEN_CONCRETE, 8390);
        setBaseID(RED_CONCRETE, 8391);
        setBaseID(BLACK_CONCRETE, 8392);
        setBaseID(WHITE_CONCRETE_POWDER, 8393);
        setBaseID(ORANGE_CONCRETE_POWDER, 8394);
        setBaseID(MAGENTA_CONCRETE_POWDER, 8395);
        setBaseID(LIGHT_BLUE_CONCRETE_POWDER, 8396);
        setBaseID(YELLOW_CONCRETE_POWDER, 8397);
        setBaseID(LIME_CONCRETE_POWDER, 8398);
        setBaseID(PINK_CONCRETE_POWDER, 8399);
        setBaseID(GRAY_CONCRETE_POWDER, 8400);
        setBaseID(LIGHT_GRAY_CONCRETE_POWDER, 8401);
        setBaseID(CYAN_CONCRETE_POWDER, 8402);
        setBaseID(PURPLE_CONCRETE_POWDER, 8403);
        setBaseID(BLUE_CONCRETE_POWDER, 8404);
        setBaseID(BROWN_CONCRETE_POWDER, 8405);
        setBaseID(GREEN_CONCRETE_POWDER, 8406);
        setBaseID(RED_CONCRETE_POWDER, 8407);
        setBaseID(BLACK_CONCRETE_POWDER, 8408);
        setBaseID(KELP, 8409);
        setBaseID(KELP_PLANT, 8435);
        setBaseID(DRIED_KELP_BLOCK, 8436);
        setBaseID(TURTLE_EGG, 8437);
        setBaseID(DEAD_TUBE_CORAL_BLOCK, 8449);
        setBaseID(DEAD_BRAIN_CORAL_BLOCK, 8450);
        setBaseID(DEAD_BUBBLE_CORAL_BLOCK, 8451);
        setBaseID(DEAD_FIRE_CORAL_BLOCK, 8452);
        setBaseID(DEAD_HORN_CORAL_BLOCK, 8453);
        setBaseID(TUBE_CORAL_BLOCK, 8454);
        setBaseID(BRAIN_CORAL_BLOCK, 8455);
        setBaseID(BUBBLE_CORAL_BLOCK, 8456);
        setBaseID(FIRE_CORAL_BLOCK, 8457);
        setBaseID(HORN_CORAL_BLOCK, 8458);
        setBaseID(TUBE_CORAL, 8459);
        setBaseID(BRAIN_CORAL, 8460);
        setBaseID(BUBBLE_CORAL, 8461);
        setBaseID(FIRE_CORAL, 8462);
        setBaseID(HORN_CORAL, 8463);
        setBaseID(DEAD_TUBE_CORAL_WALL_FAN, 8464);
        setBaseID(DEAD_BRAIN_CORAL_WALL_FAN, 8472);
        setBaseID(DEAD_BUBBLE_CORAL_WALL_FAN, 8480);
        setBaseID(DEAD_FIRE_CORAL_WALL_FAN, 8488);
        setBaseID(DEAD_HORN_CORAL_WALL_FAN, 8496);
        setBaseID(TUBE_CORAL_WALL_FAN, 8504);
        setBaseID(BRAIN_CORAL_WALL_FAN, 8512);
        setBaseID(BUBBLE_CORAL_WALL_FAN, 8520);
        setBaseID(FIRE_CORAL_WALL_FAN, 8528);
        setBaseID(HORN_CORAL_WALL_FAN, 8536);
        setBaseID(DEAD_TUBE_CORAL_FAN, 8544);
        setBaseID(DEAD_BRAIN_CORAL_FAN, 8546);
        setBaseID(DEAD_BUBBLE_CORAL_FAN, 8548);
        setBaseID(DEAD_FIRE_CORAL_FAN, 8550);
        setBaseID(DEAD_HORN_CORAL_FAN, 8552);
        setBaseID(TUBE_CORAL_FAN, 8554);
        setBaseID(BRAIN_CORAL_FAN, 8556);
        setBaseID(BUBBLE_CORAL_FAN, 8558);
        setBaseID(FIRE_CORAL_FAN, 8560);
        setBaseID(HORN_CORAL_FAN, 8562);
        setBaseID(SEA_PICKLE, 8564);
        setBaseID(BLUE_ICE, 8572);
        setBaseID(CONDUIT, 8573);
        setBaseID(VOID_AIR, 8574);
        setBaseID(CAVE_AIR, 8575);
        setBaseID(BUBBLE_COLUMN, 8576);
        setBaseID(STRUCTURE_BLOCK, 8578);
    }
}
