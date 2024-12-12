package org.example.controller.state;

import lombok.Setter;
import org.example.controller.action.AppAction;
import org.example.view.menu.CommandActionListener;

import java.util.LinkedList;
@Setter
public class UndoMachine {//
    private UndoRedoState undoRedoState;
    private CommandActionListener undo;
    private CommandActionListener redo;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList); //обе откл
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty(); //есть ли доступные действия для отмены
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty(); //...для повтора
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory(); //очистка списка действий
        undoRedoState.addAction(action);
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }
    public void updateButtons() {
        undo.setEnabled(isEnableUndo()); // обновление кнопок
        redo.setEnabled(isEnableRedo());
    }
}