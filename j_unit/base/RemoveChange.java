package ru.fizteh.fivt.students.elina_denisova.j_unit.base;

import java.util.Map;

public class RemoveChange extends Change {
    public RemoveChange(String key, String oldValue,  Map<String, Map<String, String>> databases) {
        super(key,oldValue, databases);
    }

    @Override
    public void execute() {
        String adds = MyTable.pathname(key);
        if (oldValue != null)
            databases.get(adds).put(key, oldValue);
    }
}
