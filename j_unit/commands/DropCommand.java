package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class DropCommand extends Commands {
    private String tableName;

    public DropCommand(CommonCommandState state) {
        super(state);
    }


    @Override
    public void execute() {
        try {
            if (state.base.getTable(tableName) == state.usingTable) {
               state.usingTable = null;
            }
            state.base.removeTable(tableName);
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
