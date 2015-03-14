package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class CommitCommand extends Commands {
    @Override
    public void execute() {
        if (base.getTable(Runner.usingTable) == null) {
            System.out.println("no table");
        } else {
            System.out.println(base.getTable(Runner.usingTable).commit());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
