package buildcraftAdditions.blocks;

import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftTransport;
import buildcraft.core.IItemPipe;
import buildcraftAdditions.tileEntities.Bases.TileBaseDuster;
import buildcraftAdditions.tileEntities.TileSemiAutomaticDuster;
import buildcraftAdditions.utils.Utils;
import buildcraftAdditions.variables.Variables;
import eureka.core.EurekaKnowledge;
import eureka.interfaces.IEurekaBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public class BlockSemiAutomaticDuster extends BlockBase implements IEurekaBlock {
    public IIcon front, sides, top, bottom;

    public BlockSemiAutomaticDuster() {
        super(Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
        if (player.isSneaking())
            return false;
        if (player.getCurrentEquippedItem() != null) {
            if (player.getCurrentEquippedItem().getItem() instanceof IItemPipe)
                return false;
        }
        TileBaseDuster duster = (TileBaseDuster) world.getTileEntity(x, y, z);
        if (duster != null && duster.getStackInSlot(0) == null && player.getCurrentEquippedItem() != null) {
            ItemStack stack = player.getCurrentEquippedItem().copy();
            stack.stackSize = 1;
            duster.setInventorySlotContents(0, stack);
            player.getCurrentEquippedItem().stackSize--;
            if (player.getCurrentEquippedItem().stackSize <= 0)
                player.setCurrentItemOrArmor(0, null);
        } else {
            if (duster.getStackInSlot(0) != null){
                if (!world.isRemote)
                    Utils.dropItemstack(world, x, y, z, duster.getStackInSlot(0));
                duster.setInventorySlotContents(0, null);
            }
        }
        world.markBlockForUpdate(x, y, z);
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileSemiAutomaticDuster();
    }

    @Override
    public boolean isAllowed(EntityPlayer player) {
        return EurekaKnowledge.isFinished(player, Variables.DustT1Key);
    }

    @Override
    public ItemStack[] getComponents() {
        return new ItemStack[]{new ItemStack(BuildCraftCore.ironGearItem, 2), new ItemStack(Items.gold_ingot), new ItemStack(BuildCraftTransport.pipeItemsGold, 2), new ItemStack(Blocks.stone, 3)};
    }

    @Override
    public String getMessage() {
        return Utils.localize("eureka.missingKnowledge");
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float hit){
        if (entity instanceof EntityPlayer) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof TileSemiAutomaticDuster) {
                EurekaKnowledge.eurekaBlockEvent(world, (IEurekaBlock) world.getBlock(x, y, z), x, y, z, (EntityPlayer) entity);
                ((TileSemiAutomaticDuster) tileEntity).makeProgress((EntityPlayer) entity);
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, i, j, k, entityliving, stack);

        ForgeDirection orientation = Utils.get2dOrientation(entityliving);
        world.setBlockMetadataWithNotify(i, j, k, orientation.getOpposite().ordinal(), 1);

    }

    @Override
    public IIcon getIcon(int side, int meta) {
        // If no metadata is set, then this is an icon.
        if (meta == 0 && side == 3)
            return front;

        if (side == meta && meta > 1)
            return front;

        switch (side) {
            case 0:
                return bottom;
            case 1:
                return top;
        }
        return sides;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        front = register.registerIcon("bcadditions:dusterSemiAutomaticFront");
        sides = register.registerIcon("bcadditions:dusterSemiAutomaticSides");
        top = register.registerIcon("bcadditions:dusterSemiAutomaticTop");
        bottom = register.registerIcon("bcadditions:dusterSemiAutomaticBottom");
    }
}
