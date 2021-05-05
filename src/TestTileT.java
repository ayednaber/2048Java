/**
 * Author: Ayed Naber
 * Revised: April 11th, 2021
 *
 * Description: Testing TileT class
 */

package src;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTileT {
    private TileT tile1;
    private TileT tile2;
    private TileT tile3;
    private TileT tile4;


    @Before
    public void setUp() {
        tile1 = new TileT(4);
        tile2 = new TileT(8);
        tile3 = new TileT(32);
        tile4 = new TileT(2);
    }

    @After
    public void tearDown() {
        tile1 = null;
        tile2 = null;
        tile3 = null;
        tile4 = null;
    }

    @Test
    public void getValueTest1() {
        assertEquals(4, tile1.getValue());
    }

    @Test
    public void getValueTest2() {
        assertEquals(8, tile2.getValue());
    }

    @Test
    public void getValueTest3() {
        assertEquals(32, tile3.getValue());
    }

    @Test
    public void getValueTest4() {
        assertEquals(2, tile4.getValue());
    }

    @Test
    public void setValueTest1() {
        tile1.setValue(64);
        assertEquals(64, tile1.getValue());
    }

    @Test
    public void setValueTest2() {
        tile2.setValue(128);
        assertEquals(128, tile2.getValue());
    }

    @Test
    public void setValueTest3() {
        tile3.setValue(512);
        assertEquals(512, tile3.getValue());
    }

    @Test
    public void setValueTest4() {
        tile4.setValue(512);
        assertEquals(512, tile4.getValue());
    }
}


