package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.state.UndoMachine;

public class SwitchUndo implements AppCommand {
    public SwitchUndo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }

    private final UndoMachine undoMachine;
    @Override
    public void execute() {
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }
}
