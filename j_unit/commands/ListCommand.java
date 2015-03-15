package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class ListCommand extends Commands {

    public ListCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(String.join(", ", state.usingTable.list()));
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
