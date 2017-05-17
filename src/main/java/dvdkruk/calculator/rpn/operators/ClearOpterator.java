package dvdkruk.calculator.rpn.operators;

import dvdkruk.calculator.rpn.RpnCalculator;

import java.util.List;

/**
 * Created by damia on 17/05/2017.
 */
public class ClearOpterator implements RpnOperator {
    @Override
    public boolean isApplicable(String arg) {
        return arg.equals("clear");
    }

    @Override
    public boolean apply(String arg, RpnCalculator calculator) {
        List<Double> oldStack = calculator.stackAsList();
        calculator.clear();

        //Undo action
        calculator.pushHistory(() -> calculator.addAll(oldStack));
        return true;
    }
}
