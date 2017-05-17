package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Created by damia on 17/05/2017.
 */
public class UndoOperator implements RpnOperator {
    @Override
    public boolean isApplicable(String arg) {
        return arg.equals("undo");
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        if (calculator.getHistorySize() < 1) {
            return false;
        }

        calculator.popHistory().undo();
        return true;
    }
}
