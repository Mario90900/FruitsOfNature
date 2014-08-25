package com.mario90900.fruitsofnature.creativetab;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabFON {
	public static final CreativeTabs FON_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()){
		@Override
		public Item getTabIconItem(){
			return ModItems.wheatSeeds;
		}
	};
}
