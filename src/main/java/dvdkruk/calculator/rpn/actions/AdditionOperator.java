package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Add operator.
 */
public class AdditionOperator extends DefaultRpnOperator {

    public AdditionOperator() {
        super("+", 2);
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        Double rightNumber = calculator.pop();
        Double leftNumber = calculator.pop();
        calculator.push(leftNumber + rightNumber);

        //undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(leftNumber);
            calculator.push(rightNumber);
        });
    }
}
