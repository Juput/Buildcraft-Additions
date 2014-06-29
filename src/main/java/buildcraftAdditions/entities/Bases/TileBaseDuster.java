package buildcraftAdditions.entities.Bases;

import buildcraftAdditions.Variables.DusterRecipes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftadditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftadditions.wordpress.com/wiki/licensing-stuff/
 */
public abstract class TileBaseDuster extends TileBase {
    public int progress;

    public void makeProgress(){
        if (getStackInSlot(0) != null && DusterRecipes.hasOutput(getStackInSlot(0))) {
            progress++;
            if (progress == 5) {
                dust();
                progress = 0;
            }
        } else {
            progress = 0;
        }
    }

    public void dust(){
        if (!worldObj.isRemote)
            return;
            
        for (ItemStack output : DusterRecipes.getOutputs(getStackInSlot(0))) {
            for (int i = 0; i < output.stackSize; i++) {
                float f1 = 0.7F;
                double d = (worldObj.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
                double d1 = (worldObj.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
                double d2 = (worldObj.rand.nextFloat() * f1) + (1.0F - f1) * 0.5D;
                EntityItem itemToDrop = new EntityItem(worldObj, xCoord + d, yCoord + d1, zCoord + d2, output.copy().splitStack(1));
                setInventorySlotContents(0, null);
                itemToDrop.delayBeforeCanPickup = 10;
                worldObj.spawnEntityInWorld(itemToDrop);
            }
        }
        
        setInventorySlotContents(0, null);
        markDirty();
    }

}
