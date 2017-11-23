package mods.phlenum.cll;

import static mods.phlenum.cll.lib.Reference.*;
import mods.phlenum.cll.lib.CLLLogger;
import mods.phlenum.cll.proxy.CommonProxy;
import mods.phlenum.cll.proxy.CommonProxy.CLLConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 29 Nov 2014
 */

@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION, guiFactory="mods.phlenum.cll.client.gui.CLLGuiFactory", certificateFingerprint=MOD_FINGERPRINT, canBeDeactivated=false)
public final class CombustibleLemonLauncher {

	@Mod.Instance(MOD_ID)
	public static CombustibleLemonLauncher instance;

	@SidedProxy(serverSide = "mods.phlenum.cll.proxy.CommonProxy", clientSide = "mods.phlenum.cll.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void fingerprintViolation(FMLFingerprintViolationEvent fingerprintViolationEvent){
		CLLLogger.logWarning("This jar file of Combustible Lemon Launcher failed the fingerprint validation!");
	}
	

	@Mod.EventHandler
	public void preInitializeMod(FMLPreInitializationEvent preInitEvent){
		CLLLogger.logInfo("Initializing Combustible Lemon Launcher " + MOD_VERSION);

		proxy.initializeBlocks();

		proxy.initializeItems();
		
		proxy.initializeModels();
		
		proxy.initializeEntityRender();
		
		proxy.initializeSoundEvents();
		
		CLLConfig.initializeConfig(preInitEvent.getSuggestedConfigurationFile());
	}

	@Mod.EventHandler
	public void initializeMod(FMLInitializationEvent initEvent){
		proxy.initializeWorld();

		proxy.initializeRenderers();
		
		proxy.initializePacketHandler();
	}

}

