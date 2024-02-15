package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayingTest {
    @Test
    void printClassName() {
        class A{
           String name()  {return "A";}
        }
        class B extends A{
            @Override
            String name()  {return "B";}
        }

        final A a = new A();
        assertTrue(a instanceof  A);
        assertEquals("A", a.name());
        final A b = new B();
        assertTrue(b instanceof  A);
        assertTrue(b instanceof  B);
        assertEquals("B", b.name());
    }
}
