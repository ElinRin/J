package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class PutCommand extends Commands {

    private String key;
    private String value;


    @Override
    public void execute(){
        if (Runner.usingTable == null) {
            System.out.println("no table");
        } else {
            String result = null;
            result = base.getTable(Runner.usingTable).put(key, value);
            if (result == null) {
                System.out.println("new");
            } else {
                System.out.println("overwrite");
                System.out.println(result);
            }
        }
    }

    @Override
    public int numberOfArguments() {
        return 2;
    }

    @Override
    public void putArguments(String[] args) {
        key = args[1];
        value = args[2];
    }
}

