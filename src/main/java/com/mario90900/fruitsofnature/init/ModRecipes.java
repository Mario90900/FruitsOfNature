package com.mario90900.fruitsofnature.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init(){
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.wheatSeeds, 1), new ItemStack(Items.wheat_seeds));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.potatoSeeds, 1), new ItemStack(Items.potato));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.carrotSeeds, 1), new ItemStack(Items.carrot));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lilypadSeeds, 1), new ItemStack(Blocks.waterlily));
	}
}
