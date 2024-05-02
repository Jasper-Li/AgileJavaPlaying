package sis.studentinfo;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static java.lang.System.out;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    @Test
     void basic() {
        final var course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }

    @Test
    void equality() {
        final var courseA = new Course("CMSC", "120");
        final var courseAPrime = new Course("CMSC", "120");
        assertEquals(courseA, courseAPrime);

        final var courseB = new Course("CMSC_2", "120");
        assertNotEquals(courseA, courseB);

        assertEquals(courseA, courseA);
        assertEquals(courseAPrime, courseA);

        final var courseAPrime2 = new Course("CMSC", "120");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        assertFalse(courseA.equals(null));
        assertFalse(courseA.equals("Number"));
    }

    @Test
    void testHashCode() {
        final var courseA = new Course("CMSC", "120");
        final var courseAPrime = new Course("CMSC", "120");
        assertEquals(courseA.hashCode(), courseAPrime.hashCode());

        final var courseB = new Course("CMSC_2", "120");
        assertNotEquals(courseA.hashCode(), courseB.hashCode());
//        out.println(STR."A hashCode: \{courseA.hashCode()}");
//        out.println(STR."APrime hashCode: \{courseAPrime.hashCode()}");
//        out.println(STR."B hashCode: \{courseB.hashCode()}");
    }

    @Disabled
    @Test
    void hashCodePerformance() {
        final int count = 1_0000;
        var start = System.currentTimeMillis();
        var map = new HashMap<Course, String>();
        for (int i=0; i<count; ++i) {
            map.put(new Course(STR."C_\{i}" ,  Integer.toString(i)), "");
        }
        var end = System.currentTimeMillis();
        var elapsed = end - start;
        out.println(STR."elapsed: \{elapsed}ms");
        final var threshold =200L;

        assertThat(elapsed, lessThan(threshold));
    }
}
