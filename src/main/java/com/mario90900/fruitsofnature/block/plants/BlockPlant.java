package com.mario90900.fruitsofnature.block.plants;

import java.util.ArrayList;
import java.util.Random;

import com.mario90900.fruitsofnature.block.BlockFON;
import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.tileentity.IStatTilePlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityWheatPlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockPlant extends BlockFON implements IPlantable, IGrowable, ITileEntityProvider{
	
	@SideOnly(Side.CLIENT)
    protected IIcon[] icons;
	
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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
        super.onNeighborBlockChange(world, x, y, z, block);
        this.checkAndDropBlock(world, x, y, z);
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		super.updateTick(world, x, y, z, rand);
		this.checkAndDropBlock(world, x, y, z); //This will see if the block is able to stay, and if not, removes it
	}
	
	protected void checkAndDropBlock(World world, int x, int y, int z) {
        if (!this.canBlockStay(world, x, y, z)) { //The check to see if the block can stay
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }
	
	public boolean canBlockStay(World world, int x, int y, int z) { //May need to be overwritten to allow for special (Vine and others) plants
        return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
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
	
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		ArrayList<ItemStack> drops = getDrops(world, x, y, z, meta, 0);
		
		for (int i = 0; i < drops.size(); i++){
			float fx = world.rand.nextFloat() * 0.8F + 0.1F;
            float fy = world.rand.nextFloat() * 0.8F + 0.1F;
            float fz = world.rand.nextFloat() * 0.8F + 0.1F;
            
            EntityItem entityitem = new EntityItem(world, (double)((float)x + fx), (double)((float)y + fy), (double)((float)z + fz), drops.get(i));
            
            float f3 = 0.05F;
            entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
            entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
            entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);
            entityitem.delayBeforeCanPickup = 20;
            world.spawnEntityInWorld(entityitem);
		}
		
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	public IStatTilePlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof IStatTilePlant)){
			return null;
		}
			
		return (IStatTilePlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) { //Handle the dropping and transferring of stats to dropped items.
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		IStatTilePlant tilePlant = getPlantTile(world, x, y, z);
		
		if (tilePlant != null){
			int numDrops = tilePlant.calcYield(world.rand);;
			int potency = tilePlant.getPotencyInt();
			int yield = tilePlant.getYieldInt();
			int growth = tilePlant.getGrowthInt();

			if (metadata >= 7) {
				for (int i = 0; i < 3 + fortune; ++i) {
					if (world.rand.nextInt(15) <= metadata) {
						ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
						PlantHelper.setStats(itemSeed, potency, yield, growth);
						ret.add(itemSeed);
					}
				}
				ItemStack itemCrop = new ItemStack(this.returnCrop(), numDrops, 0);
				PlantHelper.setCropStats(itemCrop, potency);
				ret.add(itemCrop);
			} else {
				ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
				PlantHelper.setStats(itemSeed, potency, yield, growth);
				ret.add(itemSeed);
			}
		}

        return ret;
    }
	
	//Be sure to override these methods with the plant returns!
	protected Item returnSeeds(){
		return null;
	}
	
	protected Item returnCrop(){
		return null;
	}
	//End!
	
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
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {//Only called by canSustainPlant!
		if (this == ModBlocks.wheatPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.potatoPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.carrotPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.lilypadPlant) return EnumPlantType.Water;
		if (this == ModBlocks.pumpkinPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.melonPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.redMushPlant) return EnumPlantType.Crop;
		if (this == ModBlocks.brownMushPlant) return EnumPlantType.Crop;
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
