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
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.vineSeeds, 1), new ItemStack(Blocks.vine));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pumpkinSeeds, 1), new ItemStack(Items.pumpkin_seeds));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.melonSeeds, 1), new ItemStack(Items.melon_seeds));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redMushSeeds, 1), new ItemStack(Blocks.red_mushroom));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.brownMushSeeds, 1), new ItemStack(Blocks.brown_mushroom));
	}
}