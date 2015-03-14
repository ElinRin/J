package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class SizeCommand extends Commands {

    @Override
    public void execute() {
        if (Runner.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(base.getTable(Runner.usingTable).size());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
