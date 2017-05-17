package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Created by damia on 17/05/2017.
 */
public interface RpnOperator {
    boolean isApplicable(String arg);

    boolean apply(String arg, RpnCalculator calculator);
}
