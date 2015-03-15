package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class ExitCommand extends Commands {


    public ExitCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null || state.usingTable.unsavedChanges() == 0) {
            throw new IllegalMonitorStateException("Exit");
        } else {
            System.err.println(state.usingTable.unsavedChanges() + "  unsaved changes");
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
