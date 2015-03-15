package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

public abstract class Commands {
    public CommonCommandState state;

    public Commands(CommonCommandState state) {
        this.state = state;
    }
    public abstract void execute();
    public void putArguments(String[] args) {
    }
    public abstract int numberOfArguments();
}
