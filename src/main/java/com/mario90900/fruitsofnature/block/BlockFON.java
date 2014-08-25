package com.mario90900.fruitsofnature.block;

import com.mario90900.fruitsofnature.creativetab.CreativeTabFON;
import com.mario90900.fruitsofnature.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockFON extends Block{
	public BlockFON(Material material){
		super(material);
		this.setCreativeTab(CreativeTabFON.FON_TAB);
	}
	
	public BlockFON(){
		this(Material.rock);
	}
	
	@Override
    public String getUnlocalizedName(){
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName){
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
