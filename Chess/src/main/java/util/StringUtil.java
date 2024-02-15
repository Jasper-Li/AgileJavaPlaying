package util;

import static java.lang.System.out;

public class StringUtil {
    public static String NEW_LINE = java.lang.System.getProperty("line.separator");
    private  StringUtil() {}
    public static String appendNewLine(String s) {
        return  s + NEW_LINE;
    }
    public static boolean isEqualIgnoreEOL(String left, String right) {
        var regex = "\\r?\\n";
        var leftLines = left.split(regex);
        var rightLines = right.split(regex);
        if(leftLines.length != rightLines.length){
            out.println(STR."length mismatch: \{leftLines.length} != \{rightLines.length}");
            return false;
        }
        for(var i = 0; i<leftLines.length; ++i){
            if(!leftLines[i].equals(rightLines[i])) {
                return false;
            }
        }
        return true;
    }
}
