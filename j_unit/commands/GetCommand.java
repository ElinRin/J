package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class GetCommand extends Commands {

    private String key;

    @Override
    public void execute() {
        if (base.getTable(Runner.usingTable) == null) {
            System.out.println("no table");
        } else {
            String result = base.getTable(Runner.usingTable).get(key);
            if (result == null) {
                System.out.println("not found");
            } else {
                System.out.println("found");
                System.out.println(result);
            }
        }
    }

    public final void putArguments(String[] args) {
        key = args[1];
    }

    @Override
    public int numberOfArguments() {
        return 1;
    }
}
