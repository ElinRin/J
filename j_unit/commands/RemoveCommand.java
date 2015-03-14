package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class RemoveCommand extends Commands {

    private String key;


    @Override
    public void execute() {
        if (Runner.usingTable == null) {
            System.out.println("no table");
        } else {
            String result = base.getTable(Runner.usingTable).remove(key);
            if (result != null) {
                System.out.println("removed");
            } else {
                System.out.println("not found");
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

