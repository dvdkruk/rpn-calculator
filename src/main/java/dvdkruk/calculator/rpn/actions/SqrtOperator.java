package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Sqrt operator.
 */
public class SqrtOperator extends DefaultRpnOperator {

    public SqrtOperator() {
        super("sqrt", 1);
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        Double number = calculator.pop();
        calculator.push(Math.sqrt(number));

        //Undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(number);
        });
    }
}
