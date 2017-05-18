package dvdkruk.calculator.rpn;


import dvdkruk.calculator.rpn.actions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reverse polish notation calculator.
 */
public class RpnCalculator {

    private final Stack<Double> stack = new Stack<>();

    private final Stack<UndoAction> history = new Stack<>();

    private final Set<RpnAction> actions = Stream.of(
            new PushNumberAction(),
            new SqrtOperator(),
            new ClearOperator(),
            new SubtractOperator(),
            new UndoOperator(),
            new MultiplicationOperator(),
            new AdditionOperator(),
            new DivisionOperator())
            .collect(Collectors.toSet());

    private final DecimalFormat formatter = new DecimalFormat();

    private int posCount = 1;

    /**
     * Init the reverse polish notation calculator.
     */
    public RpnCalculator() {
        // init number formatter
        this.formatter.setMinimumFractionDigits(0);
        this.formatter.setMaximumFractionDigits(10);
        this.formatter.setRoundingMode(RoundingMode.FLOOR);
        this.formatter.setGroupingUsed(false);
    }

    /**
     * Parses the given arguments and performs the actions if applicable.
     * Returns an empty String when all arguments are successfully applied,
     * returns a warning when one of the arguments couldn't be applied.
     *
     * @param args arguments to parse and apply.
     * @return an empty String when all arguments are successfully applied,
     * returns a warning when one of the arguments couldn't be applied.
     */
    public String parse(String[] args) {
        this.posCount = 1;
        for (String arg : args) {
            String warningMsg = parse(arg);
            if (!warningMsg.isEmpty()) {
                return warningMsg;
            }
        }
        return "";
    }

    private String parse(String arg) {
        for (RpnAction operator : this.actions) {
            if (operator.isApplicable(arg)) {
                return operator.apply(arg, this);
            }
        }
        //no valid operator found.
        return String.format("operator %s (position: %d): unknown operator", arg, this.posCount);
    }

    /**
     * Pops the latest pushed number of the stack of the calculator.
     *
     * @return latest pushed number
     */
    public Double pop() {
        this.posCount++;
        return this.stack.pop();
    }

    /**
     * Pushes the {@code number} argument onto the stack of the calculator.
     *
     * @param number number to push onto the stack
     * @return the {@code number} argument.
     */
    public Double push(Double number) {
        this.posCount++;
        return this.stack.push(number);
    }

    /**
     * Pops the latest undo actions in the history stack of the calculator.
     *
     * @return latest pushed undo actions
     */
    public UndoAction popHistory() {
        return this.history.pop();
    }

    /**
     * Pushes the given undo actions on to the history stack of the calculator.
     *
     * @param undoAction undo actions to push.
     * @return the {@code undoAction} argument.
     */
    public UndoAction pushHistory(UndoAction undoAction) {
        return this.history.push(undoAction);
    }

    /**
     * Returns numbers on the stack as a space separated string.
     *
     * @return numbers on the stack as a space separated string.
     */
    public String getStackAsString() {
        Enumeration<Double> numbers = this.stack.elements();
        if (numbers.hasMoreElements()) {
            StringBuilder builder = new StringBuilder();
            builder.append(this.formatter.format(numbers.nextElement()));
            while (numbers.hasMoreElements()) {
                builder.append(" ");
                builder.append(this.formatter.format(numbers.nextElement()));
            }
            return builder.toString();
        }
        return "";
    }

    /**
     * Returns a snapshot of the current stack as list.
     *
     * @return current stack as list.
     */
    public List<Double> stackAsList() {
        return Collections.list(this.stack.elements());
    }

    /**
     * Clears the stack of the calculator.
     */
    public void clear() {
        this.stack.clear();
    }

    /**
     * Add a list of numbers to the stack of the calculator.
     *
     * @param list list of number to add
     */
    public void addAll(List<Double> list) {
        this.stack.addAll(list);
    }

    /**
     * Get the current stack size of the calculator.
     *
     * @return size of the stack
     */
    public int getStackSize() {
        return this.stack.size();
    }

    /**
     * Get the size of the history stack of the calculator.
     *
     * @return size of the history stack
     */
    public int getHistorySize() {
        return this.history.size();
    }

    /**
     * Get the current stack modification position (mod count) of the calculator.
     * This count is reset to one on every {@see parse()} invocation.
     *
     * @return current position count
     */
    public int getPosCount() {
        return this.posCount;
    }

    /**
     * Looks at the number at the top of this stack without removing it
     * from the stack.
     *
     * @return number at the top of this stack;
     */
    public Double peek() {
        return this.stack.peek();
    }

    /**
     * Returns all supported operators as comma separated string.
     *
     * @return all supported operators as comma separated string.
     */
    public String getSupportedOperators() {
        return actions.stream().filter(a -> a instanceof DefaultRpnOperator)
                .map(o -> ((DefaultRpnOperator) o).getOperatorAsString())
                .collect(Collectors.joining(", "));
    }
}
