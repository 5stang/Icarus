package tombenpotter.icarus.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import tombenpotter.icarus.util.EventHandler;

public class PacketClientConfig implements IMessage, IMessageHandler<PacketClientConfig, IMessage> {

    public PacketClientConfig() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(PacketClientConfig message, MessageContext ctx) {
        EventHandler.holdSneakToHoverForPlayer.add(ctx.getServerHandler().playerEntity.getUniqueID());
        return null;
    }
}
