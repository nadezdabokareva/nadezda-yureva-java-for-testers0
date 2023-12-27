package ru.stqa.geometry.figures;

public record Square (double side) {

    public Square {
        if (side < 0) {
            throw new IllegalArgumentException("Square side should be non-negative");
        }
    }

    public static void printSquareArea(Square s) {
        System.out.println(String.format("Площадь квадрата со стороной %f = %f",  s.side, s.squareArea()));
    }
    public static void printSquarePerimeter(Square s) {
        System.out.println(String.format("Периметр квадрата со стороной %f = %f",  s.side, s.side*4, s.squareArea()));
    }

    public double squareArea() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
