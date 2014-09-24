package com.mario90900.fruitsofnature.block;

import java.util.ArrayList;
import java.util.Random;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityPumpkinPlant;
import com.mario90900.fruitsofnature.utility.LogHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPumpkin extends BlockVineGroundPlant implements ITileEntityProvider, IGrowable{
	public BlockPumpkin(){
		super();
		this.setBlockName(UnlocalizedNames.PUMPKIN_PLANT);
		this.setBlockTextureName(UnlocalizedNames.PUMPKIN_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityPumpkinPlant();
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.farmland;
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		super.updateTick(world, x, y, z, rand);
		
		if (world.getBlockLightValue(x, y, z) >= 6) {
			int meta = world.getBlockMetadata(x, y, z);
			LogHelper.info("Pumpkin tick, and lit! Meta is: " + meta);
			
			if (meta < 3 || (meta > 3 && meta < 7) || meta == 8){
				TileEntityPumpkinPlant tile = getPlantTile(world, x, y, z);
				tile.onBlockTick(world, x, y, z, rand);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float facingX, float facingY, float facingZ) {
		if (player.getCurrentEquippedItem() == null && world.getBlockMetadata(x, y, z) == 7){ //If the plant is Metadata 7, it is the fruit-half and it is ready to be harvested.
			TileEntityPumpkinPlant tilePumpkin = getPlantTile(world, x, y, z);
			int numDrops = tilePumpkin.calcYield(world.rand);
			LogHelper.info("Whoa, the pumpkin dropped this many: " + numDrops + " and the yieldInt is: " + tilePumpkin.getYieldInt());
			if (numDrops != 0){
				ItemStack itemCrop = new ItemStack(this.returnCrop(), numDrops, 0);
				this.dropBlockAsItem(world, x, y, z, itemCrop);
			}
			world.setBlockMetadataWithNotify(x, y, z, 8, 2);
			return true;
		}
		
		return false;
	}
	
	public int getRenderType() {
        return RenderIds.RenderPumpkinVine;
    }
	
	protected Item returnSeeds() {
		return ModItems.pumpkinSeeds;
	}
	
	protected Item returnCrop() {
		return Item.getItemFromBlock(Blocks.pumpkin);
	}
	
	public static TileEntityPumpkinPlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityPumpkinPlant))
			return null;

		return (TileEntityPumpkinPlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		TileEntityPumpkinPlant tilePumpkin = getPlantTile(world, x, y, z);
		
		if (!(tilePumpkin == null)){
			int potency = tilePumpkin.getPotencyInt();
			int yield = tilePumpkin.getYieldInt();
			int growth = tilePumpkin.getGrowthInt();

			if (metadata == 7) {
				for (int i = 0; i < 3 + fortune; ++i) {
					if (world.rand.nextInt(15) <= metadata) {
						ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
						PlantHelper.setStats(itemSeed, potency, yield, growth);
						ret.add(itemSeed);
					}
				}
			} else if (metadata >= 8) {
				for (int i = 0; i < 3 + fortune; ++i) {
					if (world.rand.nextInt(15) <= metadata) {
						ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
						PlantHelper.setStats(itemSeed, potency, yield, growth);
						ret.add(itemSeed);
					}
				}
			} else {
				ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
				PlantHelper.setStats(itemSeed, potency, yield, growth);
				ret.add(itemSeed);
			}
			
			removeTileEnt(world, x, y, z, metadata);
		}

        return ret;
    }
}
