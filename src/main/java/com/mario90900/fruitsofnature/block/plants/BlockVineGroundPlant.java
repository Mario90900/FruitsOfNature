package com.mario90900.fruitsofnature.block.plants;

import java.util.Random;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.tileentity.IStatTilePlant;
import com.mario90900.fruitsofnature.tileentity.TileVineGroundPlant;
import com.mario90900.fruitsofnature.utility.LogHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockVineGroundPlant extends BlockPlant{ //Metadata 0 - 3 are for the first plant, 4 - 7 signify it is the second plant
	public BlockVineGroundPlant(){
		super();
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		if (!world.isRemote){
			super.updateTick(world, x, y, z, rand);
			
			if (world.getBlockLightValue(x, y, z) >= 9) {
				int meta = world.getBlockMetadata(x, y, z);
				
				if (meta < 3 || (meta > 3 && meta < 7)){
					IStatTilePlant tile = getPlantTile(world, x, y, z);
					if (tile != null){
						tile.onBlockTick(world, x, y, z, rand);
					}
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float facingX, float facingY, float facingZ) {
		if (!world.isRemote && player.getCurrentEquippedItem() == null && world.getBlockMetadata(x, y, z) == 7){ //If the plant is Metadata 7, it is the fruit-half and it is ready to be harvested.
			IStatTilePlant tile = getPlantTile(world, x, y, z);
			int numDrops = tile.calcYield(world.rand);
			if (numDrops != 0){
				ItemStack itemCrop = new ItemStack(this.returnCrop(), numDrops, 0);
				this.dropBlockAsItem(world, x, y, z, itemCrop);
			}
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			return true;
		}
		return false;
	}
	
	@Override //Earlier check if Bonemeal effects the plant, true continues, false aborts the attempt. Vanilla wheat uses it to test if the wheat is fully grown or not.
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemoteWorld) {
		return (world.getBlockMetadata(x, y, z) < 3 || (world.getBlockMetadata(x, y, z) > 3 && world.getBlockMetadata(x, y, z) < 7) || world.getBlockMetadata(x, y, z) > 7);
	} //If the meta is anything but 3 or 7, return true
	
	@Override
	public void boneMealGrow(World world, int x, int y, int z) { //The method that actually "grows" the plant after a piece of bonemeal is used.
		int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (world.getBlockMetadata(x, y, z) < 3 && l >= 3) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
            PlantHelper.vineGroundPlantExpand(world, world.getTileEntity(x, y, z), ((TileVineGroundPlant)world.getTileEntity(x, y, z)).getPlantBlock(), x, y, z);
        } else if (l >= 7){
        	world.setBlockMetadataWithNotify(x, y, z, 7, 2);
		} else {
        	world.setBlockMetadataWithNotify(x, y, z, l, 2);
        }
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){ //Golly, this is really long and probably way more then is needed...	
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta < 3) {
			return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		} else if (meta == 3) { //If meta is 3, it is the first half of the stem, look for the second half near it
			for (int i = 0; i < 4; i++){
				switch (i){
				case 0:
					if (this.isEqualTo(world.getBlock(x, y, z + 1), this) && world.getBlockMetadata(x, y, z + 1) > 3){
						return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
					}
					break;
				case 1:
					if (this.isEqualTo(world.getBlock(x + 1, y, z), this) && world.getBlockMetadata(x + 1, y, z) > 3){
						return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
					}
					break;
				case 2:
					if (this.isEqualTo(world.getBlock(x, y, z - 1), this) && world.getBlockMetadata(x, y, z - 1) > 3){
						return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
					}
					break;
				case 3:
					if (this.isEqualTo(world.getBlock(x - 1, y, z), this) && world.getBlockMetadata(x - 1, y, z) > 3){
						return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
					}
					break;
				}
			}
			return false;
		} else { //If meta is greater then 3, it is the second half of the stem, look for the first half near it. Second half can grow on any block.
			for (int i = 0; i < 4; i++){
				switch (i){
				case 0:
					if (this.isEqualTo(world.getBlock(x, y, z + 1), this) && world.getBlockMetadata(x, y, z + 1) == 3){
						return true;
					}
					break;
				case 1:
					if (this.isEqualTo(world.getBlock(x + 1, y, z), this) && world.getBlockMetadata(x + 1, y, z) == 3){
						return true;
					}
					break;
				case 2:
					if (this.isEqualTo(world.getBlock(x, y, z - 1), this) && world.getBlockMetadata(x, y, z - 1) == 3){
						return true;
					}
					break;
				case 3:
					if (this.isEqualTo(world.getBlock(x - 1, y, z), this) && world.getBlockMetadata(x - 1, y, z) == 3){
						return true;
					}
					break;
				}
			}
			return false;
		}
	}
}
