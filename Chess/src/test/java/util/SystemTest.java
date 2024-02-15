package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTest {

    @Test
    void isWindows() {
        var osName = util.System.getOsName();
        if (osName.contains("windows")) {
            assertTrue(util.System.isWindows());
        }
    }
}