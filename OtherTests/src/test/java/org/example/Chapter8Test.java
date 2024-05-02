package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class Chapter8Test {
    private Chapter8 far;
    @BeforeEach
    void setUp() {
        far = new Chapter8();
    }
    void blowsUp() {
        throw new SimpleException(Chapter8.blowsUpMessage);
    }

    public void rethrows() {
        try{
            blowsUp();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void blowsUpTest() {
        try {
            far.blowsUp();
            fail("No exception caught.");
        } catch (SimpleException e) {
            assertEquals(Chapter8.blowsUpMessage, e.getMessage());
            out.println(STR."e cause: \{e.getCause()}");
        }
    }
    @Test
    void rethrowsTest() {
        try {
            far.rethrows();
            fail("No exception caught.");
        } catch (RuntimeException e) {
            out.println(STR."rethrows e cause: \{e.getCause()}");
            var cause = e.getCause();
            assertEquals(SimpleException.class, cause.getClass());
            assertEquals(Chapter8.blowsUpMessage, cause.getMessage());
        }

    }

    @Disabled
    @Test
    public void testExceptionOrder1() {
        try {
            blowsUp();
            rethrows();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }

    @Disabled
    @Test
    public void testExceptionOrder2() {
        try {
            rethrows();
            blowsUp();
            fail("no exception");
        }
        catch (SimpleException success) {
        }
        catch (RuntimeException failure) {
            fail("caught wrong exception");
        }
    }
    @Test
    public void testExceptionOrder3() {
        try {
            blowsUp();
            rethrows();
            fail("no exception");
        }
        catch (RuntimeException success) {}
        /*
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
         */
    }
    @Disabled
    @Test
    public void testExceptionOrder5() {
        try {
            blowsUp();
            rethrows();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }
    @Disabled
    @Test
    public void testExceptionOrder6() {
        try {
            rethrows();
            blowsUp();
            fail("no exception");
        }
        catch (SimpleException yours) {
            fail("caught wrong exception");
        }
        catch (RuntimeException success) {
        }
    }

    @Disabled
    @Test
    public void testExceptionOrder7() {
        try {
            rethrows();
            blowsUp();
            fail("no exception");
        }
        catch (SimpleException success) {
        }
        catch (RuntimeException fail) {
            fail("caught wrong exception");
        }
    }
    @Disabled
    @Test
    public void testErrorException1() {
        try {
            throw new RuntimeException("fail");
        }
        catch (Exception success) {
        }
    }
    class Dyer {
        Dyer() {
            throw new RuntimeException("oops.");
        }
    }
    @Disabled
    @Test
    public void testErrorException2() {
        try {
            new Dyer();
        }
        catch (Exception success) {
        }
    }
    @Disabled
    @Test
    public void testErrorException3() {
        try {
            new Dyer();
        }
        catch (Error success) {
        }
    }
    @Disabled
    @Test
    public void testErrorException4() {
        try {
            new Dyer();
        }
        catch (Throwable success) {
        }
    }
    @Disabled
    @Test
    public void testErrorException5() {
        try {
            new Dyer();
        }
        catch (Throwable fail) {
            fail("caught exception in wrong place");
        }
        /*
        catch (Error success) {
        }
         */
    }

    @Disabled
    @Test
    public void testErrorException6() {
        try {
            new Dyer();}
        catch (Error fail) {
            fail("caught exception in wrong place");
        }
        catch (Throwable success) {
        }
    }
    @Test
    public void testErrorException7() {
        try {
            new Dyer();
        }
        catch (Error fail) {
            fail("caught exception in wrong place");
        }
        catch (Throwable success) {
        }
        finally {
            return;
        }
    }
    public void testWithProblems() {
        try {
            doSomething();
            fail("no exception");
        }
        catch (Exception success) {}
    }
    void doSomething() throws Exception {
        throw new Exception("blah");
    }

    @Test
    void test_8_9() {
        try {
            far.callBlowsUp();
        } catch (RuntimeException e) {
            Chapter8.log(e);
        }
    }
}