package ru.stqa.geometry.figures;

import java.util.*;

public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {

        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if ((a+b) < c || (a+c) < b || (c+b) < a) {
            throw new IllegalArgumentException("Sum two sides of triangle cannot be less than third side");
        }

        this.a = a;
        this.b = b;
        this.c = c;

    }

    public static void printTrianglePerimeter(Triangle triangle) {
        System.out.println(
                String.format("Периметр треугольника со сторнами %f, %f, %f = %f",
                        triangle.a,
                        triangle.b,
                        triangle.c,
                        triangle.perimeter()));
    }

    public double square() {
        double halfMeter = (perimeter()) * 0.5;
        double square = (halfMeter * (halfMeter - this.a) * (halfMeter - this.b) * (halfMeter - this.c));
        return Math.sqrt(square);
    }

    public static void printTriangleSquare(Triangle triangle){
        System.out.println(String.format("Площадь треугольника со сторнами %f, %f, %f = %f",
                triangle.a,
                triangle.b,
                triangle.c,
                triangle.square()));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        List<Double> thisTriangleSides = new ArrayList<>(3) {{
            add(a);
            add(b);
            add(c);
        }};
        List<Double> innerTriangleSides = new ArrayList<>(3) {{
            add(triangle.a);
            add(triangle.b);
            add(triangle.c);
        }};
        Collections.sort(thisTriangleSides);
        Collections.sort(innerTriangleSides);

        return thisTriangleSides.equals(innerTriangleSides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

}
