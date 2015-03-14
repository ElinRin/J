package ru.fizteh.fivt.students.elina_denisova.j_unit.base;

import ru.fizteh.fivt.students.elina_denisova.j_unit.Runner;

public class PutChange extends Change{

    String key, oldValue;

    public PutChange(String key, String oldValue) {
        this.key = key;
        this.oldValue = oldValue;
    }

    @Override
    public void execute() {
        if (oldValue == null)
            Runner.base.getTable(Runner.usingTable).remove(key);
        else
            Runner.base.getTable(Runner.usingTable).put(key, oldValue);

    }
}
