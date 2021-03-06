package buildcraftAdditions.tileEntities.Bases;

import buildcraftAdditions.api.DusterRecepies;
import eureka.core.EurekaKnowledge;
import eureka.interfaces.IEurekaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public abstract class TileBaseDuster extends TileBase implements IEurekaTileEntity {
    public int progress;
    private String key;

    public TileBaseDuster(String key){
        this.key = key;
    }

    public void makeProgress(EntityPlayer player){
        if (getStackInSlot(0) != null && getDust(getStackInSlot(0)) != null) {
            progress++;
            if (progress == 8) {
                dust();
                progress = 0;
                makeProgress(player, key);
            }
        }
    }

    public abstract void dust();

    public ItemStack getDust(ItemStack stack){
        return DusterRecepies.getOutput(stack);
    }

    @Override
    public void makeProgress(EntityPlayer player, String key) {
        EurekaKnowledge.makeProgress(player, key);
    }
}
