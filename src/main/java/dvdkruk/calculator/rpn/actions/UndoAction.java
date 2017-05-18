package dvdkruk.calculator.rpn.actions;

/**
 * Interface for describing an undo action.
 */
public interface UndoAction {

    /**
     * Performs an undo action, normally undoing a {@see RpnAction}.
     */
    void undo();
}
