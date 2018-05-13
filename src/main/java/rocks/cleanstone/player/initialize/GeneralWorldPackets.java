package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;

import java.util.concurrent.ThreadLocalRandom;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.minecraft.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.PlayerAbilitiesPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.PlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class GeneralWorldPackets {

    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();
        player.sendPacket(new JoinGamePacket(0, 0, Dimension.OVERWORLD, Difficulty.EASY, LevelType.DEFAULT, false));
        player.sendPacket(new SpawnPositionPacket(new Position(0, 50, 0, null)));
        player.sendPacket(new PlayerAbilitiesPacket((byte) 0, 4, 4));
        player.sendPacket(new PlayerPositionAndLookPacket(0, 50, 0, 0, 0, 0,
                ThreadLocalRandom.current().nextInt(10000)));
        /*try {
            Thread.sleep(1000); // wait for incoming packets (probably not required)
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        player.sendPacket(ChunkDataPacketFactory.create(0, 0, new SimpleChunk(Collections.emptySet(),
                new ArrayChunkTable(), 0, 0), true));*/
    }
}
