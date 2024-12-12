package org.example.controller.state;
import org.example.controller.action.AppAction;

import java.util.LinkedList;

public abstract class UndoRedoState {
    private static final int MAX_UNDO = 50;

    public LinkedList<AppAction> getUndoActivityList() {
        return undoActivityList;
    }
    public LinkedList<AppAction> getRedoActivityList() {
        return redoActivityList;
    }

    private final LinkedList<AppAction> undoActivityList;
    private final LinkedList<AppAction> redoActivityList;

    protected UndoRedoState(LinkedList<AppAction> undoActivityList, LinkedList<AppAction> redoActivity) {
        this.undoActivityList = undoActivityList;
        this.redoActivityList = redoActivity;
    }

    public abstract UndoRedoState undo();
    public abstract UndoRedoState redo();

    public void clearHistory(){
        redoActivityList.clear(); // после нового действия, список старых не доступен
    }
    public  void addAction(AppAction action){
        if(undoActivityList.size() != MAX_UNDO){
            undoActivityList.add(action);
        }
    }
}