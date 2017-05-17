package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

public class PushNumber implements RpnOperator {

    @Override
    public boolean isApplicable(String arg) {
        try {
            Double.parseDouble(arg);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            // null or not a number
        }
        return false;
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        calculator.push(Double.parseDouble(arg));

        //Action to undo
        calculator.pushHistory(() -> calculator.pop());
        return true;
    }
}
