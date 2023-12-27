package ru.stqa.geometry;

import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.0), new Square(3.0), new Square(3.0));
//        for (Square square : squares){
//            Square.printSquareArea(square);
//        }

        Consumer <Square> print = Square::printSquareArea;
        squares.forEach(print);


//        Triangle.printTrianglePerimeter(new Triangle(1, 2 ,3));
//        Triangle.printTriangleSquare(new Triangle(1, 2 ,3));
    }

}
