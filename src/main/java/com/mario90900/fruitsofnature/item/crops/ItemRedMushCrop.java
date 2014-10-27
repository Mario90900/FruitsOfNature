package com.mario90900.fruitsofnature.item.crops;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.item.ItemStatedFood;
import com.mario90900.fruitsofnature.reference.FoodStats;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemRedMushCrop extends ItemStatedFood{
	public ItemRedMushCrop() {
		super(FoodStats.RED_MUSH_HEAL, FoodStats.RED_MUSH_SATURATION);
		this.setUnlocalizedName(UnlocalizedNames.RED_MUSH_CROP);
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote) {
			if (PlantHelper.getPotency(stack) >= 3) {
				player.addPotionEffect(new PotionEffect(Potion.poison.id, 300, 0));
			}
		}
		return super.onEaten(stack, world, player);
	}
}