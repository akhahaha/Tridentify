package com.akhahaha.tridentify.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Triangle test class
 * Created by Alan on 9/1/2016.
 */
public class TriangleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreation() {
        Triangle triangle = new Triangle(1.0, 2.2, 3.9);
        assert (triangle.getLength1() == 1.0);
        assert (triangle.getLength2() == 2.2);
        assert (triangle.getLength3() == 3.9);
    }

    @Test
    public void testCreationInvalidZero() {
        thrown.expect(IllegalArgumentException.class);
        new Triangle(1, 1, 0);
    }

    @Test
    public void testCreationInvalidNegative() {
        thrown.expect(IllegalArgumentException.class);
        new Triangle(1, -1, 1);
    }

    @Test
    public void testSetDimensions() {
        Triangle triangle1 = new Triangle(1, 2, 3);
        Triangle triangle2 = new Triangle(4, 5, 6);
        triangle2.setDimensions(1, 2, 3);
        assert (triangle1.equals(triangle2));
    }

    @Test
    public void testSetDimensionsInvalidZero() {
        thrown.expect(IllegalArgumentException.class);
        Triangle triangle = new Triangle(1, 1, 1);
        triangle.setDimensions(1, 1, 0);
    }

    @Test
    public void testSetDimensionsInvalidNegative() {
        thrown.expect(IllegalArgumentException.class);
        Triangle triangle = new Triangle(1, 1, 1);
        triangle.setDimensions(-1, 1, 1);
    }

    @Test
    public void testGetType() {
        assert (new Triangle(1, 1, 1).getType() == Triangle.Type.EQUILATERAL);
        assert (new Triangle(1, 1, 2).getType() == Triangle.Type.ISOCELES);
        assert (new Triangle(2, 1, 1).getType() == Triangle.Type.ISOCELES);
        assert (new Triangle(1, 2, 1).getType() == Triangle.Type.ISOCELES);
        assert (new Triangle(1, 2, 3).getType() == Triangle.Type.SCALENE);
    }
}
