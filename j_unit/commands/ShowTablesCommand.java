package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;

import java.util.Map;

public class ShowTablesCommand extends Commands {


    public ShowTablesCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        for (Map.Entry<String, MyTable> entry: state.base.entrySet()) {
            String name = entry.getKey();
            int size = entry.getValue().size();
            System.out.println(name + " " + size);
        }
    }

    @Override
    public int numberOfArguments() {
        return 1;
    }
}

