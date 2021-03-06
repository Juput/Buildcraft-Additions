package buildcraftAdditions.api;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public class DusterRecepies {
    public static ArrayList<ItemStack> dusterInput = new ArrayList<ItemStack>(200);
    public static ArrayList<ItemStack> dusterOutput = new ArrayList<ItemStack>(200);

    public static void addDusterRecepie(ItemStack input, ItemStack output){
	    input.stackSize = 1;
        dusterInput.add(input);
        dusterOutput.add(output);
    }

    public static ItemStack getOutput(ItemStack input){
	    int teller = 0;
	    for (ItemStack stack: dusterInput){
		    if (ItemStack.areItemStacksEqual(stack, input)){
			    return dusterOutput.get(teller).copy();
		    }
		    teller++;
	    }
        return null;
    }
}
