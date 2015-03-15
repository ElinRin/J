package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class SizeCommand extends Commands {


    public SizeCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(state.usingTable.size());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
