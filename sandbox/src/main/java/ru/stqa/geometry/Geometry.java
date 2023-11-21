package ru.stqa.geometry;

import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));

        Triangle.printTrianglePerimeter(new Triangle(1, 2 ,3));
        Triangle.printTriangleSquare(new Triangle(1, 2 ,3));
    }

}
