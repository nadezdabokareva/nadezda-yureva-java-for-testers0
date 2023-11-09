package ru.stqa.geometry.figures;

public class Triangle {

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static void printTrianglePerimeter(double a, double b, double c){
        System.out.println(String.format("Периметр треугольника со сторнами %f, %f, %f = %f",  a, b, c, perimeter(a, b, c)));
    }

    public static double square(double a, double b, double c) {
        double halfMeter = (perimeter(a, b, c))*0.5;
        double square = (halfMeter*(halfMeter-a)*(halfMeter-b)*(halfMeter-c));
        return Math.sqrt(square);
    }

    public static void printTriangleSquare(double a, double b, double c){
        System.out.println(String.format("Площадь треугольника со сторнами %f, %f, %f = %f",  a, b, c, square(a, b, c)));
    }


}
