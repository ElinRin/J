package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class RemoveCommand extends Commands {

    private String key;

    public RemoveCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            String result = state.usingTable.remove(key);
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

