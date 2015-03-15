package ru.fizteh.fivt.students.elina_denisova.j_unit.commands;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProvider;

public class CommonCommandState {
    public MyTableProvider base;
    public MyTable usingTable;

    public CommonCommandState(MyTableProvider base, MyTable usingTable) {
        this.base = base;
        this.usingTable = usingTable;
    }

}
