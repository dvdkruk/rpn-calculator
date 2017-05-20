package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Undo operator.
 */
public class UndoOperator extends DefaultRpnOperator {

    public UndoOperator() {
        super("undo", 0);
    }

    @Override
    protected String validateCalculator(RpnCalculator calculator) {
        String resultMsg = super.validateCalculator(calculator);
        if (resultMsg.isEmpty() && calculator.getHistorySize() < 1) {
            return String.format("operator %s (position: %d): history stack is empty", getOperator(), calculator.getPosCount());
        }
        return resultMsg;
    }

    @Override
    protected void applyOperator(RpnCalculator calculator) {
        calculator.popHistory().undo();
    }
}
