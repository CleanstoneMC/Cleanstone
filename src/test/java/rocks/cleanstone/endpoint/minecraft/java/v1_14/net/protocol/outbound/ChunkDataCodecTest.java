package rocks.cleanstone.endpoint.minecraft.java.v1_14.protocol.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.endpoint.minecraft.java.net.protocol.EncodedChunks;
import rocks.cleanstone.endpoint.minecraft.java.v1_14.protocol.ChunkDataEncoder_v1_14;
import rocks.cleanstone.endpoint.minecraft.java.v1_14.protocol.ProtocolBlockStateMapping_v1_14;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChunkDataCodecTest {

    @Test
    void encode() throws IOException {
        ChunkDataEncoder_v1_14 chunkDataEncoder_v1_14 = new ChunkDataEncoder_v1_14();
        ProtocolBlockStateMapping_v1_14 protocolBlockStateMapping_v1_14 = new ProtocolBlockStateMapping_v1_14();

        ChunkDataCodec chunkDataCodec = new ChunkDataCodec(chunkDataEncoder_v1_14, protocolBlockStateMapping_v1_14);

        ByteBuf buffer = Unpooled.buffer();
        chunkDataCodec.encode(buffer, EncodedChunks.getChunkDataPacket());

        byte[] data = new byte[buffer.readableBytes()];
        for (int i = 0; i < data.length; i++) {
            data[i] = buffer.readByte();
        }

        byte[] encodedChunk = EncodedChunks.V1_14_WithoutLight.getData();

        assertEquals(data.length, encodedChunk.length);

        for (int i = 0; i < encodedChunk.length; i++) {
            if (encodedChunk[i] != data[i]) {
                System.out.println("Byte " + i + " is wrong");
            }
            assertEquals(encodedChunk[i], data[i]);
        }
    }
}