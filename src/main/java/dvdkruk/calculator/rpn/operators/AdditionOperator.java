package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Created by damia on 17/05/2017.
 */
public class AdditionOperator implements RpnOperator {
    @Override
    public boolean isApplicable(String arg) {
        return arg.equals("+");
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        if (calculator.getStackSize() < 2) {
            calculator.addPosCountFailCorrection(2);
            return false;
        }

        Double rightNumber = calculator.pop();
        Double leftNumber = calculator.pop();
        calculator.push(leftNumber + rightNumber);

        //undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(leftNumber);
            calculator.push(rightNumber);
        });
        return true;
    }
}
