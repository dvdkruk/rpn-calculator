package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Subtract operator.
 */
public class SubtractOperator extends DefaultRpnOperator {

    public SubtractOperator() {
        super("-", 2);
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        Double rightNumber = calculator.pop();
        Double leftNumber = calculator.pop();
        calculator.push(leftNumber - rightNumber);

        //Undo Action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(leftNumber);
            calculator.push(rightNumber);
        });
    }
}
