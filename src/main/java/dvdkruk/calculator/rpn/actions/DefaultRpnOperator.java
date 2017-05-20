package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Default class to inherit from for reverse polish notation operator.
 */
public abstract class DefaultRpnOperator implements RpnOperator {

    private final String operator;

    private final int amountOfParameters;

    /**
     * Constructor for setting the operator string and amount of needed parameters for this operator.
     *
     * @param operator           the string of this operator
     * @param amountOfParameters amount parameters needed for this operator
     */
    DefaultRpnOperator(String operator, int amountOfParameters) {
        this.operator = operator;
        this.amountOfParameters = amountOfParameters;
    }

    @Override
    public boolean isApplicable(String arg) {
        return arg.equals(getOperator());
    }

    @Override
    public String apply(String arg, RpnCalculator calculator) {
        String resultMsg = validateCalculator(calculator);
        if (resultMsg.isEmpty()) {
            applyOperator(calculator);
        }
        return resultMsg;
    }

    @Override
    public String getOperator() {
        return this.operator;
    }

    /**
     * Checks the state of the {@code calculator} argument to see if the operation can be successfully applied.
     * Returns an empty string when the state of the calculator is valid,
     * else a warning message is returned.
     *
     * @param calculator the calculator to validate.
     * @return an empty string when the state of the calculator is valid,
     * else a warning message is returned.
     */
    String validateCalculator(RpnCalculator calculator) {
        if (calculator.getStackSize() < this.amountOfParameters) {
            return String.format("operator %s (position: %d): insufficient parameters", getOperator(), calculator.getPosCount() + calculator.getStackSize());
        }
        return "";
    }

    /**
     * Apply the operator on the given calculator.
     *
     * @param calculator the calculator for applying this operator.
     */
    protected abstract void applyOperator(RpnCalculator calculator);

}
