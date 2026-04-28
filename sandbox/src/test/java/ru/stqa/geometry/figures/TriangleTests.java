package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(4.0, 5.0, 6.0);
        double result = t.perimeter();
        Assertions.assertEquals(15.0, result);
    }

    @Test
    void canCalculateArea() {
        var t = new Triangle(4.0, 5.0, 6.0);
        double result = t.area();
        Assertions.assertEquals(9.921, result, 0.001);
    }
    @Test
    void cannotCreateTriangleWithNegativeSide(){
        try {
            new Triangle(-4.0, 5.0, 6.0);
            Assertions.fail();
        }catch (IllegalArgumentException exception) {
            //OK
        }
    }
     @Test
    void cannotCreateTriangleWithThisSide(){
         try {
             new Triangle(1.0, 1.0, 6.0);
             Assertions.fail();
         }catch (IllegalArgumentException exception) {
             //OK
         }
     }
}
