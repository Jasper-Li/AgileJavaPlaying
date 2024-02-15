package util;

public class System {
    private System() {}

    public static String getOsName() {
        return java.lang.System.getProperty("os.name").toLowerCase();
    }
    public static boolean isWindows() {
        return getOsName().contains("windows");
    }
}
