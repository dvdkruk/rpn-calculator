package dvdkruk.calculator.rpn.actions;

/**
 * Interface for describing an operator, which can be applied on a {@code RpnCalculator}.
 */
public interface RpnOperator extends RpnAction {

    /**
     * Returns the operator string of this action.
     *
     * @return operator string on which this action is applicable.
     */
    String getOperatorAsString();
}
