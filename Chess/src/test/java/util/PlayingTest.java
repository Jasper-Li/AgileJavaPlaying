package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertInstanceOf(A.class, a);
        assertEquals("A", a.name());
        final A b = new B();
        assertInstanceOf(A.class, b);
        assertInstanceOf(B.class, b);
        assertEquals("B", b.name());
    }
}
