package mods.phlenum.cll.items;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Nov 2015
 */

public class ItemLemon extends ItemFood {

    public ItemLemon(String unloc, int healAmount, float saturation, boolean dogfood){
        super(healAmount, saturation, dogfood);
        GameRegistry.registerItem(this, unloc);
        setUnlocalizedName(unloc);
        setCreativeTab(CommonProxy.tabCLL);
    }

}