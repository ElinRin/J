package ru.fizteh.fivt.students.elina_denisova.j_unit.base;

import ru.fizteh.fivt.storage.strings.Table;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MyTable implements Table {

    private Path mainDir;
    private Map<String, Map<String, String>> databases;
    private static ArrayList<Change> changes;

    private static final int COUNT_OBJECT = 16;
    private static final int COMMON_CONSTANT_INDEX = 100;
    private final String ENCODING = "UTF-8";
    private static final String SUF_DIR = ".dir";
    private static final String SUF_FILE = ".dat";

    public MyTable(File tableDir) {

        databases = new HashMap<>();
        mainDir = tableDir.toPath();
        changes = new ArrayList<>();

        for (int i = 0; i < COUNT_OBJECT; i++) {
            File subDir = new File(tableDir, i + SUF_DIR);
            for (int j = 0; j < COUNT_OBJECT; j++) {
                File dbFile = new File(subDir, j + SUF_FILE);
                if (dbFile.exists()) {
                    String adds = index(i, j);
                    databases.put(adds, new HashMap<String, String>());
                    readFromFile(dbFile, databases.get(adds));
                }
            }
        }
    }


    @Override
    public String getName() {
        return mainDir.toString();
    }

    protected static String pathname(String key) {
        int hashCode = Math.abs(key.hashCode());
        int dir = hashCode % COUNT_OBJECT;
        int file = hashCode / COUNT_OBJECT % COUNT_OBJECT;
        return index(dir, file);
    }

    private static String index(int i, int j) {
        return  Integer.toString(i * COMMON_CONSTANT_INDEX + j);
    }

    @Override
    public String get(String key) throws IllegalArgumentException {
        if (key != null) {
            String adds = pathname(key);
            return databases.get(adds).get(key);
        } else {
            throw new IllegalArgumentException("get: Haven't key. ");
        }
    }

    @Override
    public String put(String key, String value) {
        if ((key != null) && (value != null)) {
            String adds = pathname(key);
            if (!databases.containsKey(adds)) {
                databases.put(adds, new HashMap<String, String>());
            }

            String oldValue = databases.get(adds).get(key);
            databases.get(adds).put(key, value);
            changes.add(new PutChange(key, oldValue, databases));
            return oldValue;
        } else {
            throw new IllegalArgumentException("put: Haven't key or value. ");
        }
    }

    @Override
    public String remove(String key) {
        if (key != null) {
            String adds = pathname(key);
            if (!databases.containsKey(adds)) {
                return null;
            } else {
                String oldValue = databases.get(adds).get(key);
                databases.get(adds).remove(key);
                changes.add(new RemoveChange(key, oldValue, databases));
                return oldValue;
            }
        } else {
            throw new IllegalArgumentException("get: Haven't key. ");
        }
    }

    @Override
    public int size() {
        int answer = 0;
        for (int i = 0; i < COUNT_OBJECT; i++) {
            for (int j = 0; j < COUNT_OBJECT; j++) {
                String adds = index(i, j);
                if (databases.containsKey(adds)) {
                    answer += databases.get(adds).size();
                }
            }
        }
        return answer;
    }

    @Override
    public int commit() {

        int count = changes.size();
        for (int i = 0; i < COUNT_OBJECT; i++) {
            for (int j = 0; j < COUNT_OBJECT; j++) {
                String adds = index(i, j);
                if (databases.containsKey(adds)) {
                    File directory = new File(mainDir.toString(), i + SUF_DIR);
                    if (!directory.exists()) {
                        if (!directory.mkdir()) {
                            throw new UnsupportedOperationException("ParserCommands.commandsExecution.put:"
                                    + " Unable to create directories in working catalog");
                        }
                    }
                    File dataBaseOld = new File(directory, j + SUF_FILE);
                    if (dataBaseOld.exists()) {
                        try {
                            Files.delete(dataBaseOld.toPath());
                        } catch (IOException e) {
                            throw new RuntimeException("writeInFile: Can't overwrite file");
                        }
                    }

                    if (!dataBaseOld.exists()) {
                        try {
                            if (!dataBaseOld.createNewFile()) {
                                throw new IOException();
                            }
                        } catch (IOException | NullPointerException e) {
                            throw new UnsupportedOperationException("commit: "
                                    + " Unable to create database files in working catalog");
                        }
                    }
                    try (RandomAccessFile dbFile = new RandomAccessFile(dataBaseOld, "rw")) {
                        for (Map.Entry<String, String> current : databases.get(adds).entrySet()) {
                            writeNext(dbFile, current.getKey());
                            writeNext(dbFile, current.getValue());
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException("writeInFile: File not found");
                    } catch (IOException e) {
                        throw new RuntimeException("writeInFile: Can't write to file.", e);
                    }

                    if (databases.get(adds).size() == 0) {
                        File subDir = new File(mainDir.toString(), i + SUF_DIR);
                        File dbFile = new File(subDir, j + SUF_FILE);
                        try {
                            if (dbFile.exists()) {
                                Files.delete(dbFile.toPath());
                            }
                        } catch (IOException e) {
                            throw new IllegalStateException("remove: "
                                    + "Cannot delete database file. ", e);
                        }
                        databases.remove(adds);

                        int k = 0;
                        for (int file = 0; file < COUNT_OBJECT; file++) {
                            String variableAdds = index(i, file);
                            if (databases.containsKey(variableAdds)) {
                                k++;
                            }
                        }
                        if (k == 0) {
                            try {
                                Files.delete(subDir.toPath());
                            } catch (DirectoryNotEmptyException e) {
                                throw new IllegalStateException("remove: Cannot remove table subdirectory. "
                                        + "Redundant files", e);
                            } catch (IOException e) {
                                throw new IllegalStateException("remove: "
                                        + "Cannot delete database subdirectory", e);
                            }
                        }
                    }
                }
            }
        }

        changes.clear();
        return count;

    }

    private void writeNext(RandomAccessFile dbFile, String word) throws IOException {
        try {
            dbFile.writeInt(word.getBytes(ENCODING).length);
            dbFile.write(word.getBytes(ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("writeNext: Don't supported encoding " + ENCODING + ". ");
        } catch (IOException e) {
            throw new RuntimeException("writeNext: Can't write to database. ", e);
        }
    }
    public void readFromFile(File dbFileName,  Map<String, String> data) throws IllegalArgumentException {
        try (RandomAccessFile file = new RandomAccessFile(dbFileName.toString(), "r")) {

            if (file.length() > 0) {
                while (file.getFilePointer() < file.length()) {
                    String key = readNext(file);
                    String value = readNext(file);
                    if (data.containsKey(key)) {
                        throw new IllegalArgumentException("readFromFile: Two same keys in database file");
                    }
                    data.put(key, value);
                }
            }
        } catch (UnsupportedEncodingException | IllegalArgumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(" readFromFile: Problems with reading from database file " + e.toString());
        } catch (Exception e) {
            throw new RuntimeException("readFromFile: ", e);
        }
    }


    private String readNext(RandomAccessFile dbFile) throws IOException {
        try {
            int wordLength = dbFile.readInt();
            byte[] word = new byte[wordLength];
            dbFile.read(word, 0, wordLength);
            return new String(word, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("readNext: UTF-8 encoding is not supported");
        } catch (IOException e) {
            throw new IOException(" readNext: Can't read from database " + e.toString());
        }
    }


    @Override
    public int rollback() {
        int count = changes.size();
        Collections.reverse(changes);
        for (Change com : changes) {
            com.execute();
        }
        changes.clear();
        return count;
    }

    @Override
    public List<String> list() {
        List<String> listKey = new ArrayList<>();
        for (int i = 0; i < COUNT_OBJECT; i++) {
            for (int j = 0; j < COUNT_OBJECT; j++) {
                String adds = index(i, j);
                if (databases.containsKey(adds)) {
                    for (String key : databases.get(adds).keySet()) {
                        listKey.add(key);
                    }
                }
            }
        } return listKey;
    }

    public int unsavedChanges() {
        return changes.size();
    }
}
