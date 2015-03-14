package ru.fizteh.fivt.students.elina_denisova.j_unit.base;

import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.students.elina_denisova.j_unit.exception.HandlerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MyTableProvider implements TableProvider {

    private HashMap<String, MyTable> tables;
    private File parentDirectory;


    public MyTableProvider(String path) {
        parentDirectory = new File(path);
        tables = new HashMap<>();

        try {
            if (path == null) {
                throw new NullPointerException(" Wrong path");
            }
            if (!Files.exists(parentDirectory.toPath()) && !parentDirectory.mkdir()) {
                throw new IllegalArgumentException(" Cannot create working directory");
            }
            if (!parentDirectory.isDirectory()) {
                throw new IllegalArgumentException("" + parentDirectory.toString()
                        + " is not a directory");
            }
            for (String childName : parentDirectory.list()) {
                File childDirectory = new File(parentDirectory, childName);
                if (childDirectory.isDirectory()) {
                    tables.put(childName, new MyTable(childDirectory));
                } else {
                    throw new IllegalArgumentException(childName
                            + " from databases directory is not a directory");
                }
            }
        } catch (IllegalArgumentException e) {
            HandlerException.handler(e);
        }
    }

    @Override
    public Table getTable(String name) {
        return tables.get(name);
    }

    @Override
    public Table createTable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("createTable: "
                    + "Invalid name. ");
        }
        if (tables.containsKey(name)) {
            return null;
        } else {
            File newTable = new File(parentDirectory, name);
            if (!newTable.mkdir()) {
                throw new UnsupportedOperationException("createTable: "
                        + "Unable to create working directory for new table. ");
            }
            tables.put(name, new MyTable(newTable));
            return tables.get(name);
        }
    }

    public static void deleteDirectory(File dir) {
        if (dir.isDirectory())
        {
            for (String children : dir.list())
            {
                File subDir = new File(dir, children);
                deleteDirectory(subDir);
            }
            try {
                Files.delete(dir.toPath());
            } catch (IOException e) {
                throw new IllegalArgumentException("removeTable: "
                        + "cannon delete directory", e);
            }
        }
        else
        {
            try {
                Files.delete(dir.toPath());
            } catch (IOException e) {
                throw new IllegalArgumentException("removeTable: "
                        + "cannon delete database file", e);
            }
        }
    }

    @Override
    public void removeTable(String name) {
        if (name == null) {
            throw new IllegalArgumentException("removeTable: "
                    + "Invalid name. ");
        }
        if (tables.containsKey(name)) {
            File dir = new File(parentDirectory, name);
            deleteDirectory(dir);
            tables.remove(name);
        } else {
            throw new IllegalStateException("removeTable: " + name + "doesn't exist");
        }
    }


    public Set<Map.Entry<String, MyTable>> entrySet() {
        return tables.entrySet();
    }



}
