package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class CreateCommand extends Commands {
    private String tableName;

    public CreateCommand(CommonCommandState state) {
        super(state);
    }


    @Override
    public void execute() {
        if (state.base.createTable(tableName) == null) {
            System.out.println(tableName + " exists");
        } else {
            System.out.println("created");
        }
    }

    public final void putArguments(String[] args) {
        tableName = args[1];
    }

    public final int numberOfArguments() {
        return 1;
    }
}
