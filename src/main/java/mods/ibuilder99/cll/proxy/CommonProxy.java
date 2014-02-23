package mods.ibuilder99.cll.proxy;

import java.util.EnumMap;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.oredict.OreDictionary;
import mods.ibuilder99.cll.CombustibleLemonLauncher;
import mods.ibuilder99.cll.blocks.BlockLemonTreeLeaves;
import mods.ibuilder99.cll.blocks.BlockLemonTreeLeavesHarvested;
import mods.ibuilder99.cll.blocks.BlockLemonTreeSapling;
import mods.ibuilder99.cll.items.ItemCombustibleLemonLauncher;
import mods.ibuilder99.cll.items.ItemLemon;
import mods.ibuilder99.cll.items.ItemLemonExplosive;
import mods.ibuilder99.cll.lib.Reference;
import mods.ibuilder99.cll.network.CLLPacketHandler;
import mods.ibuilder99.cll.network.packets.CLLPacket;
import mods.ibuilder99.cll.world.DamageSourceExplosiveLemon;
import mods.ibuilder99.cll.world.EntityLemon;

/**
 * Combustible Lemon Launcher
 * @author Phil Julian (aka iBuilder99)
 */

public class CommonProxy {
	
	public static ItemLemon itemLemon;
	public static ItemLemonExplosive itemLemonExplosive;
	public static ItemCombustibleLemonLauncher itemCombustibleLemonLauncher;
	
	public static BlockLemonTreeLeaves blockLemonTreeLeaves;
	public static BlockLemonTreeLeavesHarvested blockLemonTreeLeavesHarvested;
	public static BlockLemonTreeSapling blockLemonTreeSapling;
	
	private static EnumMap<Side, FMLEmbeddedChannel> cllChannel;
	
	public static final DamageSourceExplosiveLemon DAMAGE_SOURCE_EXPLOSIVE_LEMON = new DamageSourceExplosiveLemon();
	
	public void initializeItems(){
		itemLemon = new ItemLemon(5, Reference.ITEM_LEMON, 0.2F, false);
		itemLemonExplosive = new ItemLemonExplosive(5, Reference.ITEM_LEMON_EXPLOSIVE, 0.2F, true);
		itemCombustibleLemonLauncher = new ItemCombustibleLemonLauncher(Reference.ITEM_COMBUSTIBLE_LEMON_LAUNCHER);
		
		OreDictionary.registerOre(Reference.OREDICT_LEMON, itemLemon);
	}
	
	public void initializeBlocks(){
		blockLemonTreeLeaves = new BlockLemonTreeLeaves(Reference.BLOCK_LEMON_TREE_LEAVES, 0.2F, 0.2F, Block.soundTypeGrass);
		blockLemonTreeLeavesHarvested = new BlockLemonTreeLeavesHarvested(Reference.BLOCK_LEMON_TREE_LEAVES_HARVESTED, 0.2F, 0.2F, Block.soundTypeGrass);
		blockLemonTreeSapling = new BlockLemonTreeSapling(Reference.BLOCK_LEMON_TREE_SAPLING, 0.0F, 0.0F, Block.soundTypeGrass);
	}
	
	public void initializeCrafting(){
		GameRegistry.addRecipe(new ItemStack(itemCombustibleLemonLauncher), new Object[]{
			"iio",
			"rp ",
			"iio", 
			Character.valueOf('i'), new ItemStack(Items.iron_ingot),
			Character.valueOf('o'), new ItemStack(Blocks.obsidian),
			Character.valueOf('r'), new ItemStack(Items.redstone),
			Character.valueOf('p'), new ItemStack(Blocks.piston)
		});
		GameRegistry.addRecipe(new ItemStack(itemLemonExplosive), new Object[]{
			" s ",
			"tlt",
			" s ",
			Character.valueOf('s'), new ItemStack(Items.string),
			Character.valueOf('t'), new ItemStack(Blocks.tnt),
			Character.valueOf('l'), new ItemStack(itemLemon)
		});
	}
	
	public void initializeWorld(){
		EntityRegistry.registerModEntity(EntityLemon.class, Reference.ENTITY_LEMON, 1, CombustibleLemonLauncher.instance, 80, 3, true);
	}
	
	public void initializeRenderers(){}
	

	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void initializePacketHandling(){
		cllChannel = NetworkRegistry.INSTANCE.newChannel(Reference.MOD_CHANNEL, new CLLPacketHandler());
		CLLPacketHandler.registerPackets();
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToAll(CLLPacket packet){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToPlayer(CLLPacket packet, EntityPlayerMP player){
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		cllChannel.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		cllChannel.get(Side.SERVER).writeAndFlush(packet);
	}
	
	/**
	 * Adapted from <a href='http://www.minecraftforge.net/wiki/Netty_Packet_Handling'>Minecraft Forge Wiki</a>
	 */
	
	public void packetCLL_sendToServer(CLLPacket packet){
		cllChannel.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		cllChannel.get(Side.CLIENT).writeAndFlush(packet);
	}
	
	public void initializeKeyBinding(){}
	
	public boolean doFancyRender(){
		return false;
	}
	
	
	public static class CommonHelper {
		
		public static void sendMessageToPlayer(EntityPlayer player, String message){
			player.addChatMessage(new ChatComponentText(message));
		}
		
		public static boolean createNBTTagIfNeeded(ItemStack itemstack){
			if(!itemstack.hasTagCompound()){
				itemstack.stackTagCompound = new NBTTagCompound();
				return true;
			}
			return false;
		}
		
	}
	
	
}
