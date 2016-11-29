package mods.phlenum.cll.lib;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

public final class Reference {

	public static final String MOD_NAME = "Combustible Lemon Launcher";
	public static final String MOD_ID = "cll";
	public static final String MOD_VERSION = "@VERSION@";
	public static final String MOD_CHANNEL = "cll";
	public static final String TEXTURE_PREFIX = Reference.MOD_ID + ":";

	public static final String ITEM_LEMON = "Lemon";
	public static final String ITEM_LEMON_EXPLOSIVE = "LemonExplosive";
	public static final String ITEM_COMBUSTIBLE_LEMON_LAUNCHER = "CombustibleLemonLauncher";
	
	public static final String OREDICT_LEMON = "foodLemon";

	public static final String BLOCK_LEMON_LEAVES = "LemonLeaves";
	public static final String BLOCK_LEMON_LEAVES_HARVESTED = "LemonLeavesHarvested";
	public static final String BLOCK_LEMON_TREE_LOG = "LemonTreeLog";
	public static final String BLOCK_LEMON_TREE_PLANKS = "LemonTreePlanks";
	public static final String BLOCK_LEMON_TREE_SAPLING = "LemonTreeSapling";
	
	public static final String SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_FIRE = "item.CombustibleLemonLauncher.fire";
	public static final String SOUNDEVENT_COMBUSTIBLELEMONLAUNCHER_OUTOFAMMO = "item.CombustibleLemonLauncher.outofammo";

	public static final String MODEL_COMBUSTIBLE_LEMON_LAUNCHER = MOD_ID.toLowerCase() + ":models/CombustibleLemonLauncher.obj";
	public static final String TEXTURE_COMBUSTIBLE_LEMON_LAUNCHER = MOD_ID.toLowerCase() + ":textures/models/CombustibleLemonLauncher.png";

	public static final String ENTITY_LEMON = "EntityLemon";
	public static final String DAMAGE_SOURCE_EXPLOSIVE_LEMON = "explosiveLemon";
	
	public static final String CONFIG_GENERATE_TREES = "Generate Lemon Trees";

}
