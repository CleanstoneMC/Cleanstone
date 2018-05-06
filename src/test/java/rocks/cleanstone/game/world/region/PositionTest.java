package rocks.cleanstone.game.world.region;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void createPosition() {
        Position position = new Position(0, 0, 0, null);

        assertTrue(position.getX() == 0);
        assertTrue(position.getY() == 0);
        assertTrue(position.getZ() == 0);

        Position position1 = position.clone();
        assertEquals(position, position1);

        assertTrue(position1.getX() == 0);
        assertTrue(position1.getY() == 0);
        assertTrue(position1.getZ() == 0);

        position1.addX(1);
        position1.addY(2);
        position1.addZ(3);
        assertNotEquals(position, position1);


        assertTrue(position1.getX() == 1);
        assertTrue(position1.getY() == 2);
        assertTrue(position1.getZ() == 3);

        position.addVector(position1);

        assertTrue(position.getX() == 1);
        assertTrue(position.getY() == 2);
        assertTrue(position.getZ() == 3);

        assertEquals(position, position1);
    }
}