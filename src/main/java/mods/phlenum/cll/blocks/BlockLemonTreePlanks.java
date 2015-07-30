package mods.phlenum.cll.blocks;

import mods.phlenum.cll.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * The Combustible Lemon Launcher mod
 * https://github.com/Phlenum/Combustible-Lemon-Launcher
 * http://minecraft.curseforge.com/mc-mods/62429-combustible-lemon-launcher
 * @author Phil Julian
 * @date 12 Feb 2015
 */

public class BlockLemonTreePlanks extends Block {


    public BlockLemonTreePlanks(String unloc, float hardness, float resistance, SoundType sound){
        super(Material.wood);
        GameRegistry.registerBlock(this, unloc);
        setHardness(hardness);
        setResistance(resistance);
        setUnlocalizedName(unloc);
        setStepSound(sound);
        setCreativeTab(CommonProxy.tabCLL);
    }
    
    @Override
    public boolean isFlammable(IBlockAccess arg0, BlockPos arg1, EnumFacing arg2){
    	return true;
    }
    
    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
    	return 20;
    }
  
}