package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {
    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

        if (a + b < c || a + c < b || b + c < a) {
            throw new IllegalArgumentException("Triangle invalid side value");
        }
    }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double halfP = perimeter() / 2;
        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 &&
                Double.compare(this.b, triangle.b) == 0 &&
                Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.a) == 0 &&
                Double.compare(this.c, triangle.b) == 0 &&
                Double.compare(this.b, triangle.c) == 0)

                || (Double.compare(this.b, triangle.a) == 0 &&
                Double.compare(this.a, triangle.b) == 0 &&
                Double.compare(this.c, triangle.c) == 0)

                || (Double.compare(this.b, triangle.a) == 0 &&
                Double.compare(this.c, triangle.b) == 0 &&
                Double.compare(this.a, triangle.c) == 0)

                || (Double.compare(this.c, triangle.a) == 0 &&
                Double.compare(this.a, triangle.b) == 0 &&
                Double.compare(this.b, triangle.c) == 0)

                || (Double.compare(this.c, triangle.a) == 0 &&
                Double.compare(this.b, triangle.b) == 0 &&
                Double.compare(this.a, triangle.c) == 0);

    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
