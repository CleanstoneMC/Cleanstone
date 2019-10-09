package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.net.minecraft.protocol.EncodedChunks;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.ChunkDataEncoder_v1_12_2;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.ProtocolBlockStateMapping_v1_12_2;

import java.io.IOException;
import java.util.Arrays;

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
        for (int i = 0; i < data.length; i++) {
            data[i] = buffer.readByte();
        }

        byte[] encodedChunk = EncodedChunks.V1_12_2_WithoutLight.getData();

        assertEquals(data.length, encodedChunk.length);

        for (int i = 0; i < encodedChunk.length; i++) {
            if (encodedChunk[i] != data[i]) {
                System.out.println("Byte " + i + " is wrong");
            }
            assertEquals(encodedChunk[i], data[i]);
        }
    }
}