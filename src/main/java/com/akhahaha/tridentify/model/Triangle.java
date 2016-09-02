package com.akhahaha.tridentify.model;

/**
 * Triangle model
 * Created by Alan on 9/1/2016.
 */
public class Triangle {
    public enum Type {
        EQUILATERAL, ISOCELES, SCALENE
    }

    private double length1;
    private double length2;
    private double length3;

    private static final String LENGTH_EXCEPTION_MSG = "Invalid triangle lengths";

    public Triangle(double length1, double length2, double length3) {
        setDimensions(length1, length2, length3);
    }

    /**
     * Sets and validates the com.akhahaha.triangle's dimensions.
     *
     * @param length1 Length of side 1
     * @param length2 Length of side 2
     * @param length3 Length of side 3
     */
    public void setDimensions(double length1, double length2, double length3) {
        // Validate lengths
        if (length1 <= 0 || length2 <= 0 || length3 <= 0) {
            throw new IllegalArgumentException(LENGTH_EXCEPTION_MSG);
        }

        this.length1 = length1;
        this.length2 = length2;
        this.length3 = length3;
    }

    public double getLength1() {
        return length1;
    }

    public void setLength1(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException(LENGTH_EXCEPTION_MSG);
        }

        this.length1 = length;
    }

    public double getLength2() {
        return length2;
    }

    public void setLength2(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException(LENGTH_EXCEPTION_MSG);
        }

        this.length2 = length;
    }

    public double getLength3() {
        return length3;
    }

    public void setLength3(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException(LENGTH_EXCEPTION_MSG);
        }

        this.length3 = length;
    }

    /**
     * Returns the triangle type.
     *
     * @return Triangle type
     */
    public Type getType() {
        int numEqualSides = 0;

        if (length1 == length2) {
            numEqualSides = 2;
            if (length1 == length3) {
                numEqualSides = 3;
            }
        } else if (length1 == length3 || length2 == length3) {
            numEqualSides = 2;
        }

        switch (numEqualSides) {
            case 3:
                return Type.EQUILATERAL;
            case 2:
                return Type.ISOCELES;
            case 0:
            default:
                return Type.SCALENE;
        }
    }

    /**
     * Determines if equivalent to another triangle.
     *
     * @param t Triangle to be compared
     * @return Equivalence
     */
    public boolean equals(Triangle t) {
        return length1 == t.getLength1() && length2 == t.getLength2() && length3 == t.getLength3() ||
                length1 == t.getLength1() && length2 == t.getLength3() && length3 == t.getLength2() ||
                length1 == t.getLength2() && length2 == t.getLength1() && length3 == t.getLength3() ||
                length1 == t.getLength2() && length2 == t.getLength3() && length3 == t.getLength1() ||
                length1 == t.getLength3() && length2 == t.getLength1() && length3 == t.getLength2() ||
                length1 == t.getLength3() && length2 == t.getLength2() && length3 == t.getLength1();
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "length1=" + length1 +
                ", length2=" + length2 +
                ", length3=" + length3 +
                '}';
    }
}
