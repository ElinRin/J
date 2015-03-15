package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;

public class UseCommand extends Commands {

    String tableName;

    public UseCommand(CommonCommandState state) {
        super(state);
    }


    @Override
    public void execute() {


        if (state.usingTable != null && state.usingTable.unsavedChanges() != 0) {
            System.out.println(state.usingTable.unsavedChanges() + " unsaved changes");
        } else {
            if (state.base.getTable(tableName) == null) {
                System.out.println(tableName + " not exists");
            } else {
                state.usingTable = (MyTable)state.base.getTable(tableName);
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
