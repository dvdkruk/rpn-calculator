package dvdkruk.calculator.rpn.actions;

/**
 * Interface for describing an undo action.
 */
@FunctionalInterface
public interface UndoAction {

    /**
     * Performs an undo action, normally undoing a {@see RpnAction}.
     */
    void undo();
}
