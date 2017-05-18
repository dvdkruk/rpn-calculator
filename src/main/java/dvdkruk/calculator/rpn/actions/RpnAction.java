package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Interface for describing an action, which can be applied on a {@code RpnCalculator}.
 */
public interface RpnAction {

    /**
     * Return {@code true} when the action is applicable.
     *
     * @param arg input argument to check is the this applicable.
     * @return {@code true} when the action is applicable.
     */
    boolean isApplicable(String arg);

    /**
     * Applies this action to the given calculator.
     *
     * @param arg        input arg that is applicable.
     * @param calculator the calculator for applying this action.
     * @return an empty String when the action is applied successfully,
     * returns a warning message when is it not applied.
     */
    String apply(String arg, RpnCalculator calculator);
}
