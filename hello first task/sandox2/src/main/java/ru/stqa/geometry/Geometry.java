package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.0);
        Rectangle.printRectangleArea(7.0, 3.0);
        Triangle.printTrianglePerimeter(7, 8, 5);
        Triangle.printTriangleSquare(10, 10, 10);
    }

}
