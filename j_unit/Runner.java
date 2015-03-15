package ru.fizteh.fivt.students.elina_denisova.j_unit;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTable;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProvider;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProviderFactory;
import ru.fizteh.fivt.students.elina_denisova.j_unit.commands.*;
import ru.fizteh.fivt.students.elina_denisova.j_unit.exception.HandlerException;
import ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter.InteractiveParse;
import ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter.PackageParse;

import java.util.HashMap;

public class Runner {


    public static void main(String[] args) {

        try {
            String path = System.getProperty("fizteh.db.dir");
            if (path == null) {
                throw new IllegalArgumentException("Directory doesn't exist");
            }
            MyTableProviderFactory factory = new MyTableProviderFactory();
            MyTableProvider base = factory.create(path);
            MyTable table = null;
            CommonCommandState state = new CommonCommandState(base, table);
            HashMap<String, Commands> listCommands;
            listCommands = new HashMap<>();
            listCommands.put("create", new CreateCommand(state));
            listCommands.put("drop", new DropCommand(state));
            listCommands.put("use", new UseCommand(state));
            listCommands.put("show_tables", new ShowTablesCommand(state));
            listCommands.put("put", new PutCommand(state));
            listCommands.put("get", new GetCommand(state));
            listCommands.put("remove", new RemoveCommand(state));
            listCommands.put("list", new ListCommand(state));
            listCommands.put("exit", new ExitCommand(state));
            listCommands.put("commit", new CommitCommand(state));
            listCommands.put("rollback", new RollbackCommand(state));
            listCommands.put("size", new SizeCommand(state));

            if (args.length == 0) {
                InteractiveParse.parse(listCommands);
            } else {
                PackageParse.parse(listCommands, args);
            }
        } catch (IllegalArgumentException e) {
            HandlerException.handler(e);
        }
    }

}

