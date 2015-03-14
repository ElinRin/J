package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class DropCommand extends Commands {
    private String tableName;

    @Override
    public void execute() {
        try {
            if (base.getTable(tableName) == base.getTable(Runner.usingTable)) {
                Runner.usingTable = null;
            }
            base.removeTable(tableName);
            System.out.println("dropped");
        } catch (IllegalStateException e) {
            System.out.println(tableName + " not exists");
        }
    }

    public final void putArguments(String[] args) {
        tableName = args[1];
    }

    public final int numberOfArguments() {
        return 1;
    }
}
