package dvdkruk.calculator.rpn;

import junit.framework.TestCase;

public class RpnCalculatorTest extends TestCase {

    private RpnCalculator calculator;

    public void setUp() throws Exception {
        super.setUp();
        calculator = new RpnCalculator();
    }

    public void testPushNumbers() {
        assertEquals("", calculator.parse(new String[]{"5", "2"}));
        assertEquals("5 2", calculator.getStackAsString());
    }

    public void testSqrt() {
        assertEquals("", calculator.parse(new String[]{"2", "sqrt"}));
        assertEquals("1.4142135623", calculator.getStackAsString());
    }

    public void testSqrtOnMultipleNumbers() {
        assertEquals("", calculator.parse(new String[]{"2", "sqrt", "9", "sqrt"}));
        assertEquals("1.4142135623 3", calculator.getStackAsString());
    }

    public void testClear() {
        assertEquals("", calculator.parse(new String[]{"2", "sqrt", "clear", "9", "sqrt"}));
        assertEquals("3", calculator.getStackAsString());
    }

    public void testSubtraction() {
        assertEquals("", calculator.parse(new String[]{"5", "2", "-"}));
        assertEquals("3", calculator.getStackAsString());
        assertEquals("", calculator.parse(new String[]{"3", "-"}));
        assertEquals("0", calculator.getStackAsString());
        assertEquals("", calculator.parse(new String[]{"clear"}));
        assertEquals("", calculator.getStackAsString());
    }

    public void testUndoPushNumber() {
        assertEquals("", calculator.parse(new String[]{"2", "3", "undo", "undo"}));
        assertEquals("", calculator.getStackAsString());
    }

    public void testUndoSqrt() {
        assertEquals("", calculator.parse(new String[]{"9", "sqrt", "undo"}));
        assertEquals("9", calculator.getStackAsString());
    }

    public void testUndoClear() {
        assertEquals("", calculator.parse(new String[]{"1", "2", "3", "clear", "undo"}));
        assertEquals("1 2 3", calculator.getStackAsString());
    }

    public void testUndoSubtraction() {
        assertEquals("", calculator.parse(new String[]{"2", "1", "-", "undo"}));
        assertEquals("2 1", calculator.getStackAsString());
    }

    public void testMultiplication() {
        assertEquals("", calculator.parse(new String[]{"3", "3", "3", "*"}));
        assertEquals("3 9", calculator.getStackAsString());
        assertEquals("", calculator.parse(new String[]{"*"}));
        assertEquals("27", calculator.getStackAsString());

    }

    public void testUndoMultiplication() {
        assertEquals("", calculator.parse(new String[]{"3", "3", "3", "*", "undo"}));
        assertEquals("3 3 3", calculator.getStackAsString());
    }

    public void testAddition() {
        assertEquals("", calculator.parse(new String[]{"1300", "37", "+"}));
        assertEquals("1337", calculator.getStackAsString());
    }

    public void testUndoAddition() {
        assertEquals("", calculator.parse(new String[]{"1300", "37", "+", "undo"}));
        assertEquals("1300 37", calculator.getStackAsString());
    }

    public void testDivision() {
        assertEquals("", calculator.parse(new String[]{"12", "2", "/"}));
        assertEquals("6", calculator.getStackAsString());
    }

    public void testUndoDivision() {
        assertEquals("", calculator.parse(new String[]{"12", "2", "/", "undo"}));
        assertEquals("12 2", calculator.getStackAsString());
    }

    public void testDivideByZero() {
        assertEquals("operator / (position: 3): cannot divide by zero", calculator.parse(new String[]{"99", "0", "/"}));
        assertEquals("99 0", calculator.getStackAsString());
    }

    public void testInvalidAmountOfNumbersOnStack() {
        assertEquals("operator * (position: 15): insufficient parameters", calculator.parse(new String[]{"1", "2", "3", "*", "5", "+", "*", "*", "6", "5"}));
        assertEquals("11", calculator.getStackAsString());
    }

    public void testInvalidOperator() {
        assertEquals("operator abc (position: 2): unknown operator", calculator.parse(new String[]{"3", "abc"}));
        assertEquals("3", calculator.getStackAsString());
    }

    public void testUndoWithNoMoreHistory() {
        assertEquals("operator undo (position: 3): history stack is empty", calculator.parse(new String[]{"1", "undo", "undo"}));
        assertEquals("", calculator.getStackAsString());
    }

    public void testUndoWithNoHistory() {
        assertEquals("operator undo (position: 1): history stack is empty", calculator.parse(new String[]{"undo"}));
        assertEquals("", calculator.getStackAsString());
    }

    public void testNoParamtersForTwoParamterRequiredOperator() {
        assertEquals("operator * (position: 1): insufficient parameters", calculator.parse(new String[]{"*"}));
        assertEquals("", calculator.getStackAsString());
    }
}