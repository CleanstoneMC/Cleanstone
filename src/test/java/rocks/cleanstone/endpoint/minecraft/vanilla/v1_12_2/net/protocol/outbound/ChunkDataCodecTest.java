package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.endpoint.minecraft.java.net.protocol.EncodedChunks;
import rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.ChunkDataEncoder_v1_12_2;
import rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.ProtocolBlockStateMapping_v1_12_2;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChunkDataCodecTest {

    @Test
    void encode() throws IOException {
        ChunkDataEncoder_v1_12_2 chunkDataEncoder_v1_12_2 = new ChunkDataEncoder_v1_12_2();
        ProtocolBlockStateMapping_v1_12_2 protocolBlockStateMapping_v1_12_2 = new ProtocolBlockStateMapping_v1_12_2();

        ChunkDataCodec chunkDataCodec = new ChunkDataCodec(chunkDataEncoder_v1_12_2, protocolBlockStateMapping_v1_12_2);

        ByteBuf buffer = Unpooled.buffer();
        chunkDataCodec.encode(buffer, EncodedChunks.getChunkDataPacket());

        byte[] data = new byte[buffer.readableBytes()];
        buffer.readBytes(data, 0, data.length);

        byte[] encodedChunk = EncodedChunks.V1_12_2_WithoutLight.getData();

        assertEquals(data.length, encodedChunk.length, "Length is wrong");

        for (int i = 0; i < encodedChunk.length; i++) {
            assertEquals(encodedChunk[i], data[i], "Byte " + i + " is wrong");
        }
    }
}