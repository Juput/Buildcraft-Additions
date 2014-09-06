package buildcraftAdditions.eureka;

import buildcraftAdditions.BuildcraftAdditions;
import buildcraftAdditions.variables.Variables;
import eureka.api.client.gui.EurekaChapter;
import eureka.api.EurekaInformation;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public class DusterMechanicalInformation extends EurekaInformation {
	@Override
	public String getKey() {
		return Variables.DustT2Key1;
	}

	@Override
	public int getIncrement() {
		return 1;
	}

	@Override
	public int getMaxValue() {
		return 40;
	}

	@Override
	public ItemStack getDisplayStack() {
		return new ItemStack(BuildcraftAdditions.mechanicalDusterBlock);
	}

	@Override
	public String getCategory() {
		return "BCA";
	}

	@Override
	public EurekaChapter getGui() {
		return new DusterMechanicalGui();
	}

	@Override
	public ArrayList<String> getRequiredResearch() {
		ArrayList<String> research = new ArrayList<String>();
		research.add(Variables.DustT1Key);
		return research;
	}
}
