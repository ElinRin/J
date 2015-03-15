package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class RollbackCommand extends Commands {


    public RollbackCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(state.usingTable.rollback());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}

