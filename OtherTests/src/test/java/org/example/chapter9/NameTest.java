package org.example.chapter9;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void equality() {
        var name1 = new Name("Tom");
        var name1Prime = new Name("Tom");
        var name2 = new Name("Jerry");

        assertEquals(name1, name1Prime);
        assertNotEquals(name1, name2);

        assertEquals(name1, name1);

        var name1Prime2 = new Name("Tom");
        assertEquals(name1Prime, name1Prime2);
        assertEquals(name1, name1Prime2);

        assertEquals(name1Prime, name1);

        assertNotEquals(name1, null);
    }

    @Test
    void setEquality() {
        final var foo = new Name("Foo");
        Set<Name> names = Set.of(
            foo,
            new Name("Tom"),
            new Name("Jerry"),
            new Name("Park")
        );
        assertTrue(names.contains(foo));
        assertTrue(names.contains(new Name("Foo")));
    }
}