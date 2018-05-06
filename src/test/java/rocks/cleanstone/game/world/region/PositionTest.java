package rocks.cleanstone.game.world.region;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void createPosition() {
        Position position = new Position(0, 0, 0, null);

        assertEquals(0, position.getX(), 0.0);
        assertEquals(0, position.getY(), 0.0);
        assertEquals(0, position.getZ(), 0.0);

        Position position1 = new Position(position);
        assertEquals(position, position1);

        assertEquals(0, position1.getX(), 0.0);
        assertEquals(0, position1.getY(), 0.0);
        assertEquals(0, position1.getZ(), 0.0);

        position1.addX(1);
        position1.addY(2);
        position1.addZ(3);
        assertNotEquals(position, position1);


        assertEquals(1, position1.getX(), 0.0);
        assertEquals(2, position1.getY(), 0.0);
        assertEquals(3, position1.getZ(), 0.0);

        position.addVector(position1);

        assertEquals(1, position.getX(), 0.0);
        assertEquals(2, position.getY(), 0.0);
        assertEquals(3, position.getZ(), 0.0);

        assertEquals(position, position1);
    }
}