package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double a) {
        String format = String.format("Площадь квадрата со стороной %f = %f", a, squareArea(a));
        System.out.println(format);
    }

    private static double squareArea(double a) {
        return a * a;
    }

}
