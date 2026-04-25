package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double halfP = perimeter() / 2;
        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }

}
