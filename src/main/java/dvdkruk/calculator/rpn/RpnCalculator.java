package dvdkruk.calculator.rpn;


import com.sun.javaws.exceptions.InvalidArgumentException;
import dvdkruk.calculator.rpn.operators.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RpnCalculator {

    private final Stack<Double> stack = new Stack<>();

    private final Stack<UndoAction> historyStack = new Stack<>();

    private final Set<RpnOperator> operators = Stream.of(
            new PushNumber(),
            new SqrtOperator(),
            new ClearOpterator(),
            new SubtractOperator(),
            new UndoOperator(),
            new MultiplicationOperator(),
            new AdditionOperator(),
            new DivisionOperator()).collect(Collectors.toSet());

    private final DecimalFormat formatter = new DecimalFormat();

    private int posCount;

    public RpnCalculator() {
        this.formatter.setMinimumFractionDigits(0);
        this.formatter.setMaximumFractionDigits(10);
        this.formatter.setRoundingMode(RoundingMode.FLOOR);
        this.formatter.setGroupingUsed(false);
    }

    public String parse(String[] args) {
        this.posCount = 0;
        for (String arg : args) {
            try {
                if (!parse(arg)) {
                    return String.format("operator %s (position: %d): insufficient parameters\n%s", arg, posCount, getStackString());
                }
            } catch (InvalidArgumentException e) {
                return String.format("Unsupported operator: %s\n%s", arg, getStackString());
            }
        }
        return getStackString();
    }

    private boolean parse(String arg) throws InvalidArgumentException {
        for (RpnOperator operator : this.operators) {
            if (operator.isApplicable(arg)) {
                return operator.apply(arg, this);
            }
        }
        //no valid operator found.
        throw new InvalidArgumentException(new String[]{"unknown operator: " + arg});
    }

    public Double pop() {
        this.posCount++;
        return this.stack.pop();
    }

    public Double push(Double item) {
        this.posCount++;
        return this.stack.push(item);
    }

    public void addPosCountFailCorrection(int i) {
        this.posCount = this.posCount + i;
    }

    public UndoAction popHistory() {
        return this.historyStack.pop();
    }

    public UndoAction pushHistory(UndoAction undoAction) {
        return this.historyStack.push(undoAction);
    }


    private String getStackString() {
        Enumeration<Double> numbers = this.stack.elements();
        StringBuilder builder = new StringBuilder();
        builder.append("stack:");
        while (numbers.hasMoreElements()) {
            builder.append(" ");
            builder.append(this.formatter.format(numbers.nextElement()));
        }
        return builder.toString();
    }

    public List<Double> stackAsList() {
        return Collections.list(this.stack.elements());
    }

    public void clear() {
        this.stack.clear();
    }

    public void addAll(List<Double> list) {
        this.stack.addAll(list);
    }

    public int getStackSize() {
        return this.stack.size();
    }

    public int getHistorySize() {
        return this.historyStack.size();
    }
}
