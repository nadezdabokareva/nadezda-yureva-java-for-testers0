package ru.stqa.geometry.figures;

public class Triangle {

    private double a;
    private double b;
    private double c;


    public Triangle(double a, double b, double c) {
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
}
