package dvdkruk.calculator.rpn;

import junit.framework.TestCase;

public class RpnCalculatorTest extends TestCase {

    private RpnCalculator calculator;

    public void setUp() throws Exception {
        super.setUp();
        calculator = new RpnCalculator();
    }

    public void testPushNumbers() {
        assertEquals("stack: 5 2", calculator.parse(new String[]{"5", "2"}));
    }

    public void testSqrt() {
        assertEquals("stack: 1.4142135623", calculator.parse(new String[]{"2", "sqrt"}));
    }

    public void testSqrtOnMultipleNumbers() {
        assertEquals("stack: 1.4142135623 3", calculator.parse(new String[]{"2", "sqrt", "9", "sqrt"}));
    }

    public void testClear() {
        assertEquals("stack: 3", calculator.parse(new String[]{"2", "sqrt", "clear", "9", "sqrt"}));
    }

    public void testSubtraction() {
        assertEquals("stack: 3", calculator.parse(new String[]{"5", "2", "-"}));
        assertEquals("stack: 0", calculator.parse(new String[]{"3", "-"}));
        assertEquals("stack:", calculator.parse(new String[]{"clear"}));
    }

    public void testUndoPushNumber() {
        assertEquals("stack:", calculator.parse(new String[]{"2", "3", "undo", "undo"}));
    }

    public void testUndoSqrt() {
        assertEquals("stack: 9", calculator.parse(new String[]{"9", "sqrt", "undo"}));
    }

    public void testUndoClear() {
        assertEquals("stack: 1 2 3", calculator.parse(new String[]{"1", "2", "3", "clear", "undo"}));
    }

    public void testUndoSubtraction() {
        assertEquals("stack: 2 1", calculator.parse(new String[]{"2", "1", "-", "undo"}));
    }

    public void testMultiplication() {
        assertEquals("stack: 3 9", calculator.parse(new String[]{"3", "3", "3", "*"}));
        assertEquals("stack: 27", calculator.parse(new String[]{"*"}));
    }

    public void testUndoMultiplication() {
        assertEquals("stack: 3 3 3", calculator.parse(new String[]{"3", "3", "3", "*", "undo"}));
    }

    public void testAddition() {
        assertEquals("stack: 1337", calculator.parse(new String[]{"1300", "37", "+"}));
    }

    public void testUndoAddition() {
        assertEquals("stack: 1300 37", calculator.parse(new String[]{"1300", "37", "+", "undo"}));
    }

    public void testDivision() {
        assertEquals("stack: 6", calculator.parse(new String[]{"12", "2", "/"}));
    }

    public void testUndoDivision() {
        assertEquals("stack: 12 2", calculator.parse(new String[]{"12", "2", "/", "undo"}));
    }

    public void testInvalidAmountOfNumbersOnStack() {
        assertEquals("operator * (position: 15): insufficient parameters\nstack: 11", calculator.parse(new String[]{"1", "2", "3", "*", "5", "+", "*", "*", "6", "5"}));
    }

    public void testInvalidOperator() {
        assertEquals("Unsupported operator: abc\nstack: 3", calculator.parse(new String[]{"3", "abc"}));
    }
}