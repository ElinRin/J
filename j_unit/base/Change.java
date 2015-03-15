package ru.fizteh.fivt.students.elina_denisova.j_unit.base;


import java.util.Map;

public abstract class Change {
    protected String key, oldValue;
    protected Map<String, Map<String, String>> databases;

    public Change(String key, String oldValue,  Map<String, Map<String, String>> databases) {
        this.key = key;
        this.oldValue = oldValue;
        this.databases = databases;
    }
    public abstract void execute();
}
