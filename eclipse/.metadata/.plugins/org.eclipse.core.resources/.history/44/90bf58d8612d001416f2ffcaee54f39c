package com.mario90900.fruitsofnature.block;

import java.util.Random;

import com.mario90900.fruitsofnature.init.ModBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPlant extends BlockFON implements IPlantable, IGrowable {
	
	protected int minDrops;
	protected int maxDrops;
	
	@SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public BlockPlant(){
		super(Material.plants);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
        this.setCreativeTab((CreativeTabs)null);
	}
	
	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        this.checkAndDropBlock(world, x, y, z);
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		this.checkAndDropBlock(world, x, y, z);
	}
	
	protected void checkAndDropBlock(World world, int x, int y, int z) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }
	
	public boolean canBlockStay(World world, int x, int y, int z) { //May need to be overwritten to allow for Vine plants
        return  world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta > 7) {
            meta = 7;
        }
        return this.icons[meta];
    }
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg) {
        this.icons = new IIcon[8];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconReg.registerIcon(String.format("%s" + i, getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        }
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		return;
	}
	
	public void removeTileEnt(World world, int x, int y, int z, int metadata){
		if (hasTileEntity(metadata)) {
            world.removeTileEntity(x, y, z);
        }
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	public int getRenderType(){
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return null;
    }

	//IPlantable methods
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		if (this == ModBlocks.wheatPlant) return EnumPlantType.Plains;
		return EnumPlantType.Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	//End IPlantable methods
	
	//IGrowable methods
	@Override //Earlier check if Bonemeal effects the plant, true continues, false aborts the attempt. Vanilla wheat uses it to test if the wheat is fully grown or not.
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemoteWorld) {
		return world.getBlockMetadata(x, y, z) != 7;
	}

	@Override //This returns true if Bonemeal should effect this plant, false aborts
	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return true;
	}

	@Override //This is the method called when Bonemeal is used on the block
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		this.boneMealGrow(world, x, y, z);
	}
	
	public void boneMealGrow(World world, int x, int y, int z) { //The method that actually "grows" the plant after a piece of bonemeal is used.
        int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (l > 7) {
            l = 7;
        }

        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
	//End IGrowable methods
}
