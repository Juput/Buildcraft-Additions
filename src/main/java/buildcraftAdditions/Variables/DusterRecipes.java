package buildcraftAdditions.Variables;

import buildcraftAdditions.core.Logger;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class DusterRecipes {

    private static HashMap<ItemStack, HashMap<ItemStack, Double>> recipes = new HashMap<ItemStack, HashMap<ItemStack, Double>>();

    public static void addDusterRecipe(ItemStack input, ItemStack[] outputs, double[] chances) {

        input = input.copy().splitStack(1);

        if (outputs.length != chances.length || recipes.keySet().contains(input)) {
            Logger.error("Unable to add duster recipe (" + input.toString() + " -> " + Arrays.asList(outputs).toString() + ")");
            return;
        }

        HashMap<ItemStack, Double> outputPairs = new HashMap<ItemStack, Double>();

        for (int i = 0; i < outputs.length; i++) {
            ItemStack output = outputs[i].copy();
            if (!outputPairs.containsKey(output))
                outputPairs.put(output.copy(), chances[i]);
            else {
                Logger.error("Unable to add duster recipe (" + input.toString() + " -> " + Arrays.asList(outputs).toString() + ")");
                return;
            }
        }

        recipes.put(input.copy(), outputPairs);

    }

    public static ArrayList<ItemStack> getOutputs(ItemStack input) {

        if (!hasOutput(input))
            return null;

        ArrayList<ItemStack> outputs = new ArrayList<ItemStack>(1);

        for (Map.Entry<ItemStack, HashMap<ItemStack, Double>> entry : recipes.entrySet()) {
            if (ItemStack.areItemStacksEqual(input, entry.getKey())) {
                for (ItemStack output : entry.getValue().keySet()) {
                    if (entry.getValue().get(output) <= Math.random())
                        output.stackSize = (int) (output.stackSize * Math.random());
                    if (output.stackSize > 0)
                        outputs.add(output);
                }
            }
        }
        return outputs;
    }

    public static boolean hasOutput(ItemStack input) {

        for (ItemStack in : recipes.keySet()) {
            if (ItemStack.areItemStacksEqual(in, input.copy().splitStack(1)))
                return true;
        }

        return recipes.containsKey(input.copy().splitStack(1));
    }
}
