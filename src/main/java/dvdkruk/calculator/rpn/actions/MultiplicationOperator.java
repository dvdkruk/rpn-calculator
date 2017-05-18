package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Multiply operator.
 */
public class MultiplicationOperator extends DefaultRpnOperator {

    public MultiplicationOperator() {
        super("*", 2);
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        Double rightNumber = calculator.pop();
        Double leftNumber = calculator.pop();
        calculator.push(rightNumber * leftNumber);

        //undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(leftNumber);
            calculator.push(rightNumber);
        });
    }
}
