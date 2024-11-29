package org.example.view.menu;

import lombok.AllArgsConstructor;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;

@AllArgsConstructor
public class SwitchAction implements AppCommand
{
    private AppAction appAction;
    private MenuState menuState;
    @Override
    public void execute() {
        menuState.setAction(appAction);
    }
}
