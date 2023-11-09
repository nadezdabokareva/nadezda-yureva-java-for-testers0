package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double a) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f",  a, squareArea(a)));
    }

    public static double squareArea(double a) {
        return a * a;
    }

}
