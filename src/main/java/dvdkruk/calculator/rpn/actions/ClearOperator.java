package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

import java.util.List;

/**
 * Clear operator.
 */
public class ClearOperator extends DefaultRpnOperator {

    public ClearOperator() {
        super("clear", 0);
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        List<Double> oldStack = calculator.stackAsList();
        calculator.clear();

        //Undo action
        calculator.pushHistory(() -> calculator.addAll(oldStack));
    }
}
