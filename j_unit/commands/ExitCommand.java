package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;

public class ExitCommand extends Commands {
    @Override
    public void execute() {
        if (Runner.usingTable == null || ( (MyTable) base.getTable(Runner.usingTable) ).unsavedChanges() == 0) {
            throw new IllegalMonitorStateException("Exit");
        } else {
            System.err.println(((MyTable) base.getTable(Runner.usingTable) ).unsavedChanges() + "  unsaved changes");
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
