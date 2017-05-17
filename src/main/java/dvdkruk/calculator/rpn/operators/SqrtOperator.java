package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

/**
 * Created by damia on 17/05/2017.
 */
public class SqrtOperator implements RpnOperator {
    @Override
    public boolean isApplicable(String arg) {
        return arg.equals("sqrt");
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        if (calculator.getStackSize() < 1) {
            calculator.addPosCountFailCorrection(1);
            return false;
        }

        Double number = calculator.pop();
        calculator.push(Math.sqrt(number));

        //Undo action
        calculator.pushHistory(() -> {
            calculator.pop();
            calculator.push(number);
        });
        return true;
    }
}
