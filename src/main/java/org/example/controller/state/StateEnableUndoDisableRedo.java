package org.example.controller.state;

import org.example.controller.action.AppAction;

import java.util.LinkedList;

public class StateEnableUndoDisableRedo extends UndoRedoState {//поведения

    protected StateEnableUndoDisableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = undoActivityList.pollLast(); // отмена включена
        if (action != null) {
            redoActivityList.add(action);
            action.unexecute();
        }
        if (!redoActivityList.isEmpty()) // список повторов не пуст
        {
            return new StateEnableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
        } else return new StateDisableUndoEnableRedo(getUndoActivityList(), getRedoActivityList());
    }

    @Override
    public UndoRedoState redo() {
        return this;
    }
}