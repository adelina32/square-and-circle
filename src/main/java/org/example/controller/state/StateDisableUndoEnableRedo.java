package org.example.controller.state;

import org.example.controller.action.AppAction;

import java.util.LinkedList;

public class StateDisableUndoEnableRedo extends UndoRedoState{//

    protected StateDisableUndoEnableRedo(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        super(undoActivityList, redoActivity);
    }

    @Override
    public UndoRedoState undo() {
        return this;
    }

    @Override
    public UndoRedoState redo() {
        LinkedList<AppAction> undoActivityList = getUndoActivityList();
        LinkedList<AppAction> redoActivityList = getRedoActivityList();
        AppAction action = redoActivityList.pollLast(); // повтор включен
        if (action != null) {
            undoActivityList.add(action); //Извлекает последнее действие из списка повторов, добавляет его в список отмен
            action.execute();
        }
        return new StateEnableUndoDisableRedo(getUndoActivityList(),getRedoActivityList());
    }
}