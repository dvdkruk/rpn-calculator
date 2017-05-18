package dvdkruk.calculator.rpn.actions;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Division operator.
 */
public class DivisionOperator extends DefaultRpnOperator {

    public DivisionOperator() {
        super("/", 2);
    }

    @Override
    protected String validateCalculator(RpnCalculator calculator) {
        String resultMsg = super.validateCalculator(calculator);
        if (resultMsg.isEmpty()) {
            if (calculator.peek() == 0.0) {
                return String.format("operator %s (position: %d): cannot divide by zero", this.getOperatorAsString(), calculator.getPosCount());
            }
        }
        return resultMsg;
    }

    @Override
    protected void applyOperator(String arg, RpnCalculator calculator) {
        Double right = calculator.pop();
        Double left = calculator.pop();

        calculator.push(left / right);

        //Undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(left);
            calculator.push(right);
        });
    }
}
