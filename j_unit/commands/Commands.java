package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProvider;

public abstract class Commands {
    protected MyTableProvider base;
    public void stateBase(MyTableProvider base) {
        this.base = base;
    }
    public abstract void execute();
    public void putArguments(String[] args) {
    }
    public abstract int numberOfArguments();
}
