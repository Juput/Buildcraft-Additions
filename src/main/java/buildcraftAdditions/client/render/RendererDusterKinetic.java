package buildcraftAdditions.client.render;

import buildcraftAdditions.tileEntities.Bases.TileBaseDuster;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public class RendererDusterKinetic extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float fl) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        TileBaseDuster duster = (TileBaseDuster) entity;
        ItemStack stack = duster.getStackInSlot(0);
        EntityItem item;
        if (stack != null) {
            item = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, stack);
            item.hoverStart = 0;
            RenderManager.instance.renderEntityWithPosYaw(item, 0.5, 0.5, 0.5, 0, 0);
        }
        GL11.glPopMatrix();
    }
}
