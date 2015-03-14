package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class RollbackCommand extends Commands {

    @Override
    public void execute() {
        if (Runner.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(base.getTable(Runner.usingTable).rollback());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}

