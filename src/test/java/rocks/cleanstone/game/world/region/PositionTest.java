package rocks.cleanstone.game.world.region;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void createPosition() {
        Position position = new Position(0, 0, 0, null);

        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
        assertEquals(0, position.getZ());

        Position position1 = new Position(position);
        assertEquals(position, position1);

        assertEquals(0, position1.getX());
        assertEquals(0, position1.getY());
        assertEquals(0, position1.getZ());

        position1.addX(1);
        position1.addY(2);
        position1.addZ(3);
        assertNotEquals(position, position1);


        assertEquals(1, position1.getX());
        assertEquals(2, position1.getY());
        assertEquals(3, position1.getZ());

        position.addVector(position1);

        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(3, position.getZ());

        assertEquals(position, position1);
    }
}