package com.akhahaha.tridentify;

import com.akhahaha.tridentify.model.Triangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Main test class
 * Created by Alan on 9/2/2016.
 */
public class MainTest {
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));
    }

    @After
    public void cleanupStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testTriangles() {
        Main.main(new String[]{"1", "1", "1"});
        assertEquals(Triangle.Type.EQUILATERAL.name(), outStream.toString().trim());

        outStream.reset();
        Main.main(new String[]{"1", "1", "2"});
        assertEquals(Triangle.Type.ISOCELES.name(), outStream.toString().trim());

        outStream.reset();
        Main.main(new String[]{"1", "2", "3"});
        assertEquals(Triangle.Type.SCALENE.name(), outStream.toString().trim());
    }
}
