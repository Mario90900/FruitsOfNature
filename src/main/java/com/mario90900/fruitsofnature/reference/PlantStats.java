package com.mario90900.fruitsofnature.reference;

import net.minecraft.block.Block;

public final class PlantStats {
	public static final float VERYLOW_MOD = 0.25f;
	public static final float LOW_MOD = 0.5f;
	public static final float AVERAGE_MOD = 1f;
	public static final float HIGH_MOD = 1.5f;
	public static final float VERYHIGH_MOD = 2f;
	
	//Values for use in every plant, when it is at Average level. These are used in the various calculations, modifying them here will change the output.
	//Yield comes in two flavors, flat or range.
	//	Flat will always return a flat number of crops, unless modifying the value with the strength results in a decimal. If there is a decimal, it will calculate the proper percent for additional drops. EX. 1.5 will return 1 and has a 50% chance for a second.
	//	Range has both the max and the min modified by strength, and then each is handled separately and goes through the flat calculations. Then these integer values are used as the max and min for a random range between, unless max is equal or less then min, where min will be used instead.
	//Growth is a simple calculation, and is used as the percentage each time the block ticks that it will grow that tick.
	
	//Wheat
	public static final int WHEAT_YIELD = 2;
	public static final int WHEAT_GROWTH = 14;
	
	//Potatoes
	public static final int POTATO_YIELD_MIN = 1;
	public static final int POTATO_YIELD_MAX = 4;
	public static final int POTATO_GROWTH = 14;
	
	//Carrots
	public static final int CARROT_YIELD_MIN = 1;
	public static final int CARROT_YIELD_MAX = 4;
	public static final int CARROT_GROWTH = 14;
	
	//Lilypads
	public static final int LILYPAD_YIELD = 1;
	public static final int LILYPAD_GROWTH = 10;
	
	//Vines
	public static final int VINE_YIELD = 2;
	public static final int VINE_GROWTH = 18;
	
	//Pumpkins
	public static final int PUMPKIN_YIELD = 1;
	public static final int PUMPKIN_GROWTH = 14;
	
	//Melons
	public static final int MELON_YIELD = 1;
	public static final int MELON_GROWTH = 14;
	
	//Red Mushrooms
	public static final int RED_MUSH_YIELD_MIN = 1;
	public static final int RED_MUSH_YIELD_MAX = 4;
	public static final int RED_MUSH_GROWTH = 12;
	
	//Brown Mushrooms
	public static final int BROWN_MUSH_YIELD_MIN = 1;
	public static final int BROWN_MUSH_YIELD_MAX = 4;
	public static final int BROWN_MUSH_GROWTH = 12;
	
	//Cocoa
	public static final int COCOA_YIELD_MIN = 2;
	public static final int COCOA_YIELD_MAX = 4;
	public static final int COCOA_GROWTH = 18;
}
