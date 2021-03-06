package buildcraftAdditions.networking;

import buildcraftAdditions.tileEntities.TileKineticDuster;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * Copyright (c) 2014, AEnterprise
 * http://buildcraftAdditions.wordpress.com/
 * Buildcraft Additions is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://buildcraftAdditions.wordpress.com/wiki/licensing-stuff/
 */
public class MessageDusterKinetic implements IMessage, IMessageHandler<MessageDusterKinetic, IMessage> {
	public int x, y, z, id, meta, progressStage;

	public MessageDusterKinetic(){

	}

	public MessageDusterKinetic(int x, int y, int z, int progressStage, ItemStack stack){
		this.x = x;
		this.y = y;
		this.z = z;
		this.progressStage = progressStage;
		if (stack == null){
			this.id = 0;
		} else {
			this.id = Item.getIdFromItem(stack.getItem());
			this.meta = stack.getItemDamage();
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.progressStage = buf.readInt();
		this.id = buf.readInt();
		this.meta = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(progressStage);
		buf.writeInt(id);
		buf.writeInt(meta);
	}

	@Override
	public IMessage onMessage(MessageDusterKinetic message, MessageContext ctx) {
		TileEntity entity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if (entity != null && entity instanceof TileKineticDuster){
			TileKineticDuster duster = (TileKineticDuster) entity;
			duster.progressStage = message.progressStage;
			FMLClientHandler.instance().getClient().theWorld.markBlockForUpdate(message.x, message.y, message.z);
			if (message.id == 0){
				duster.setInventorySlotContents(0, null);
			} else {
				duster.setInventorySlotContents(0, new ItemStack(Item.getItemById(message.id), 1, message.meta));
			}
		}
		return null;
	}
}
