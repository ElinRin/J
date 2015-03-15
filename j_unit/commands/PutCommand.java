package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public class PutCommand extends Commands {

    private String key;
    private String value;

    public PutCommand(CommonCommandState state) {
        super(state);
    }


    @Override
    public void execute(){
        if (state.usingTable == null) {
            System.out.println("no table");
        } else {
            String result = null;
            result = state.usingTable.put(key, value);
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

