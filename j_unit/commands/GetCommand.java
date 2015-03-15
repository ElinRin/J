package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class GetCommand extends Commands {

    private String key;

    public GetCommand(CommonCommandState state) {
        super(state);
    }

    @Override
    public void execute() {
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            String result = state.usingTable.get(key);
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
