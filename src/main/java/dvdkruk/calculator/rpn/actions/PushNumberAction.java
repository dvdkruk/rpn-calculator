package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Push a number operator.
 */
public class PushNumberAction implements RpnAction {

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
    public String apply(String arg, RpnCalculator calculator) {
        calculator.push(Double.parseDouble(arg));

        //Action to undo
        calculator.pushHistory(calculator::pop);
        return "";
    }
}
