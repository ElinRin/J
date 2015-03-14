package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class ListCommand extends Commands {
    @Override
    public void execute() {
        if (base.getTable(Runner.usingTable) == null) {
            System.out.println("no table");
        } else {
            System.out.println(String.join(", ", base.getTable(Runner.usingTable).list()));
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
