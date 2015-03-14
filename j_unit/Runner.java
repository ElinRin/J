package ru.fizteh.fivt.students.elina_denisova.j_unit;

import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProvider;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProviderFactory;
import ru.fizteh.fivt.students.elina_denisova.j_unit.commands.*;
import ru.fizteh.fivt.students.elina_denisova.j_unit.exception.HandlerException;
import ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter.InteractiveParse;
import ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter.PackageParse;

import java.util.HashMap;

public class Runner {

    public static String usingTable;
    public static MyTableProvider base;
    public static void main(String[] args) {

        try {
            String path = System.getProperty("fizteh.db.dir");
            if (path == null) {
                throw new IllegalArgumentException("Directory doesn't exist");
            }
            MyTableProviderFactory factory = new MyTableProviderFactory();
            base = factory.create(path);
            HashMap<String, Commands> listCommands;
            listCommands = new HashMap<>();
            listCommands.put("create", new CreateCommand());
            listCommands.put("drop", new DropCommand());
            listCommands.put("use", new UseCommand());
            listCommands.put("show_tables", new ShowTablesCommand());
            listCommands.put("put", new PutCommand());
            listCommands.put("get", new GetCommand());
            listCommands.put("remove", new RemoveCommand());
            listCommands.put("list", new ListCommand());
            listCommands.put("exit", new ExitCommand());
            listCommands.put("commit", new CommitCommand());
            listCommands.put("rollback", new RollbackCommand());
            listCommands.put("size", new SizeCommand());
            for (Commands com : listCommands.values()) {
                com.stateBase(base);
            }

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

