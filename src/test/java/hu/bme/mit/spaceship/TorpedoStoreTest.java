package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void testConstructor() {
        TorpedoStore store = new TorpedoStore(5);

        assertEquals(5, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void testEmptyStore() {
        TorpedoStore store = new TorpedoStore(0);

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void testFireNegativeNumberOfTorpedoes() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void testFireZeroTorpedoes() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void testFireSingleTorpedo() {
        TorpedoStore store = new TorpedoStore(5);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void testFireMultipleTorpedoes() {
        TorpedoStore store = new TorpedoStore(5);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void testFireAllTorpedoes() {
        TorpedoStore store = new TorpedoStore(5);

        boolean result = store.fire(5);

        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void testFireMoreTorpedoesThanAvailable() {
        TorpedoStore store = new TorpedoStore(5);

        assertThrows(IllegalArgumentException.class, () -> store.fire(10));
    }

    @Test
    void testFailureRateOne() {
        TorpedoStore store = new TorpedoStore(5, 1.0);

        boolean result = store.fire(3);

        assertFalse(result);
        assertEquals(5, store.getTorpedoCount());
    }

}
