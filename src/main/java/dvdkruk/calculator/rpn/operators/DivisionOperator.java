package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Created by damia on 17/05/2017.
 */
public class DivisionOperator implements RpnOperator {
    @Override
    public boolean isApplicable(String arg) {
        return arg.equals("/");
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        if (calculator.getStackSize() < 2) {
            calculator.addPosCountFailCorrection(2);
            return false;
        }

        Double right = calculator.pop();
        Double left = calculator.pop();

        calculator.push(left / right);

        //Undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(left);
            calculator.push(right);
        });
        return true;
    }
}
