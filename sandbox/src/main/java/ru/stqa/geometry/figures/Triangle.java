package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {
    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

    if (a + b < c || a + c < b || b + c < a) {
        throw new IllegalArgumentException("Triangle invalid side value");
    } }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double halfP = perimeter() / 2;
        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }

}
