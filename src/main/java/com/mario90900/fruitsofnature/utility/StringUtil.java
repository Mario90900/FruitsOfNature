package com.mario90900.fruitsofnature.utility;

import net.minecraft.util.StatCollector;

public class StringUtil {
	public static String localize(String key) {
		return StatCollector.translateToLocal(key);
	}
}
