package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class CommitCommand extends Commands {

    public CommitCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            System.out.println(state.usingTable.commit());
        }
    }

    @Override
    public int numberOfArguments() {
        return 0;
    }
}
