package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.node.CommandNode;
import rocks.cleanstone.game.command.completion.node.NodeType;
import rocks.cleanstone.net.minecraft.packet.outbound.DeclareCommandsPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class DeclareCommandsCodec implements OutboundPacketCodec<DeclareCommandsPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DeclareCommandsPacket packet) throws IOException {

        ByteBufUtils.writeVarInt(byteBuf, packet.getCommandNodes().size());

        for (CommandNode commandNode : packet.getCommandNodes()) {
            byteBuf.writeBytes(encodeCommandNode(byteBuf, commandNode));
        }

        ByteBufUtils.writeVarInt(byteBuf, 0); //TODO: Get Rootnode

        return byteBuf;
    }

    private ByteBuf encodeCommandNode(ByteBuf byteBuf, CommandNode commandNode) throws IOException {

        byteBuf.writeByte(commandNode.getCommandNodeFlags().getBitMask());
        ByteBufUtils.writeVarInt(byteBuf, commandNode.getChildren().size());
        //TODO: Write Childs?
        if (commandNode.getCommandNodeFlags().isHasRedirect()) {
            ByteBufUtils.writeVarInt(byteBuf, commandNode.getRedirectNodeIndex());
        }

        if (commandNode.getCommandNodeFlags().getNodeType() == NodeType.ARGUMENT
                || commandNode.getCommandNodeFlags().getNodeType() == NodeType.LITERAL) {
            ByteBufUtils.writeUTF8(byteBuf, commandNode.getName());
        }

        if (commandNode.getCommandNodeFlags().getNodeType() == NodeType.ARGUMENT) {
            ByteBufUtils.writeUTF8(byteBuf, commandNode.getParser().getIdentifier());

            //TODO: Properties
        }

        if (commandNode.getCommandNodeFlags().isHasSuggestionType()) {
            ByteBufUtils.writeUTF8(byteBuf, commandNode.getSuggestionsType().getIdentifier());
        }

        return byteBuf;
    }

}
