package buildcraftAdditions.triggers;

import buildcraft.api.gates.ITileTrigger;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.core.triggers.BCTrigger;
import buildcraftAdditions.tileEntities.TileChargingStation;
import buildcraftAdditions.utils.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public class TriggerReadyToCharge extends BCTrigger implements ITileTrigger {
    public IIcon icon;

    public TriggerReadyToCharge(){
        super("bcadditions:TriggerReadyToCharge");
    }

    @Override
    public String getDescription(){
        return Utils.localize("trigger.readyToCharge");
    }

    @Override
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof TileChargingStation){
            TileChargingStation chargingStation = (TileChargingStation) tile;
            return chargingStation.getStackInSlot(0) == null;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon() {
        return icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconregister) {
        icon = iconregister.registerIcon("bcadditions:TriggerReadyToCharge");
    }

    @Override
    public ITrigger rotateLeft() {
        return this;
    }
}
