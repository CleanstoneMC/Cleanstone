package rocks.cleanstone.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {

    private Vector vector0;
    private Vector vector1;
    private Vector vector2;

    @BeforeEach
    void createVectors() {
        vector0 = new Vector(0, 0, 0);
        vector1 = new Vector(1, 1, 1);
        vector2 = new Vector(2, 2, 2);
    }

    @Test
    void addVector() {
        vector0.addVector(vector1);

        assertEquals(1, vector0.x, 0.0);
        assertEquals(1, vector0.y, 0.0);
        assertEquals(1, vector0.z, 0.0);
    }

    @Test
    void subtractVector() {
        vector0.subtractVector(vector1);

        assertEquals(vector0.x, -1, 0.0);
        assertEquals(vector0.y, -1, 0.0);
        assertEquals(vector0.z, -1, 0.0);
    }

    @Test
    void multiplyVector() {
        vector1.multiplyVector(vector2);

        assertEquals(2, vector1.x, 0.0);
        assertEquals(2, vector1.y, 0.0);
        assertEquals(2, vector1.z, 0.0);

        vector1.multiplyVector(vector2);

        assertEquals(4, vector1.x, 0.0);
        assertEquals(4, vector1.y, 0.0);
        assertEquals(4, vector1.z, 0.0);
    }

    @Test
    void equals() {
        assertEquals(vector0, new Vector(vector0));

        vector0.addVector(vector1);

        assertEquals(vector0, vector1);
    }
}