import chess.PrinterTest;
import org.junit.platform.suite.api.*;

@Suite
@SelectPackages({"chess", "pieces", "util"})
@SelectClasses({PrinterTest.class})
//@SelectClasses({CharacterTest.class})
public class AllTests {
}
