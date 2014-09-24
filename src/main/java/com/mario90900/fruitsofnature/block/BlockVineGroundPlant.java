package com.mario90900.fruitsofnature.block;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.tileentity.TileVineGroundPlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockVineGroundPlant extends BlockPlant{ //Metadata 0 - 3 are for the first plant, 4 - 7 signify it is the second plant
	public BlockVineGroundPlant(){
		super();
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
	
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta > 8) {
            meta = 8;
        }
        return this.icons[meta];
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iconReg) {
        this.icons = new IIcon[9];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconReg.registerIcon(String.format("%s" + i, getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        }
    }
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){ //Golly, this is really long and probably way more then is needed...
		if (world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this)) {
			if (world.getBlockMetadata(x, y, z) == 3) {
				for (int i = 0; i < 4; i++){
					switch (i){
					case 0:
						if (this.isEqualTo(world.getBlock(x, y, z + 1), this) && world.getBlockMetadata(x, y, z + 1) > 3){
							return true;
						}
						break;
					case 1:
						if (this.isEqualTo(world.getBlock(x + 1, y, z), this) && world.getBlockMetadata(x + 1, y, z) > 3){
							return true;
						}
						break;
					case 2:
						if (this.isEqualTo(world.getBlock(x, y, z - 1), this) && world.getBlockMetadata(x, y, z - 1) > 3){
							return true;
						}
						break;
					case 3:
						if (this.isEqualTo(world.getBlock(x - 1, y, z), this) && world.getBlockMetadata(x - 1, y, z) > 3){
							return true;
						}
						break;
					}
				}
			} else if (world.getBlockMetadata(x, y, z) > 3) {
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
			} else {
				return true;
			}
		}
		return false;
	}
}
