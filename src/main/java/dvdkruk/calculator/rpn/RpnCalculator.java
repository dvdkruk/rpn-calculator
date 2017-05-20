package dvdkruk.calculator.rpn;


import dvdkruk.calculator.rpn.actions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Reverse polish notation calculator.
 */
public class RpnCalculator {

    private final Deque<Double> stack = new ArrayDeque<>();

    private final Deque<UndoAction> history = new ArrayDeque<>();

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
    String parse(String[] args) {
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
     */
    public void push(Double number) {
        this.posCount++;
        this.stack.push(number);
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
     */
    public void pushHistory(UndoAction undoAction) {
        this.history.push(undoAction);
    }

    /**
     * Returns numbers on the stack as a space separated string.
     *
     * @return numbers on the stack as a space separated string.
     */
    String getStackAsString() {
        Iterator<Double> reversedStream = this.stack.descendingIterator();
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(reversedStream,
                Spliterator.ORDERED), false)
                .map(formatter::format)
                .collect(Collectors.joining(" "));
    }

    /**
     * Returns a snapshot of the current stack as list.
     *
     * @return current stack as list.
     */
    public List<Double> stackAsList() {
        return new ArrayList<>(stack);
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
    String getSupportedOperators() {
        return actions.stream().filter(a -> a instanceof RpnOperator)
                .map(o -> ((RpnOperator) o).getOperator())
                .collect(Collectors.joining(", "));
    }
}
