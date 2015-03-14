package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;

public class UseCommand extends Commands {

    String tableName;

    @Override
    public void execute() {


        if (Runner.usingTable != null && ( (MyTable) base.getTable(Runner.usingTable) ).unsavedChanges() != 0) {
            System.out.println(( (MyTable) base.getTable(Runner.usingTable) ).unsavedChanges() + " unsaved changes");
        } else {
            if (base.getTable(tableName) == null) {
                System.out.println(tableName + " not exists");
            } else {
                Runner.usingTable = tableName;
                System.out.println("using " + tableName);
            }
        }
    }

    @Override
    public int numberOfArguments() {
        return 1;
    }

    @Override
    public void putArguments(String[] args) {
        tableName = args[1];
    }
}
