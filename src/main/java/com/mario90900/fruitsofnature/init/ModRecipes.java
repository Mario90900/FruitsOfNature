package com.mario90900.fruitsofnature.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init(){
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.wheatSeeds, 1), new ItemStack(Items.wheat_seeds));
	}
}