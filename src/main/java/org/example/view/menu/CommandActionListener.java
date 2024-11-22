package org.example.view.menu;

public class CommandActionListener {
    public static final String COMMAND = "Command";

    public CommandActionListener(String name, Icon icon, AppCommand command) {
        super(name, icon);
        putValue(COMMAND, command);
    }
    public CommandActionListener(AppCommand command) {
        super();
        putValue(COMMAND, command);
    }
}
