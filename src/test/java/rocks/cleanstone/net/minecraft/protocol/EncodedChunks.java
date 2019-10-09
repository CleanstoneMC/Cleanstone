package rocks.cleanstone.net.minecraft.protocol;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.storage.chunk.BlockDataStorageProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public enum EncodedChunks {
    V1_12_2_WithoutLight("V1_12_2_WithoutLight.hex");

    byte[] data;

    EncodedChunks(String path) {
        URL url = EncodedChunks.class.getResource(path);

        assert(url != null);

        try {
            String s = Resources.toString(url, Charsets.UTF_8);
            this.data = hexStringToByteArray(s);
        } catch (IOException e) {
            return;
        }

    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public byte[] getData() {
        return data;
    }

    public static ChunkDataPacket getChunkDataPacket() {
        FlatWorldGenerator flatWorldGenerator = new FlatWorldGenerator(new BlockDataStorageProvider());
        Chunk chunk = flatWorldGenerator.generateChunk(1234567890, ChunkCoords.of(0, 0));
        return new ChunkDataPacket(0, 0, true, chunk.getBlockDataStorage(), null);
    }
}
