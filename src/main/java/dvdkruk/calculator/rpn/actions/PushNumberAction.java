package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Push a number operator.
 */
public class PushNumberAction implements RpnAction {

    private static final Logger LOG = Logger.getLogger(PushNumberAction.class.getName());

    @Override
    public boolean isApplicable(String arg) {
        try {
            Double number = Double.parseDouble(arg);
            return !(number.isNaN() || number.isInfinite());
        } catch (NullPointerException | NumberFormatException e) {
            LOG.log(Level.FINEST, arg + " cannot be parsed to a " + Double.class, e);
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
