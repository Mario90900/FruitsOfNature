package com.mario90900.fruitsofnature.item.crops;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.item.ItemStatedFood;
import com.mario90900.fruitsofnature.reference.FoodStats;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

public class ItemBrownMushCrop extends ItemStatedFood{
	public ItemBrownMushCrop() {
		super(FoodStats.BROWN_MUSH_HEAL, FoodStats.BROWN_MUSH_SATURATION);
		this.setUnlocalizedName(UnlocalizedNames.BROWN_MUSH_CROP);
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		return super.onEaten(stack, world, player);
	}
}
