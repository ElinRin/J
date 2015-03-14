package ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter;


import ru.fizteh.fivt.students.elina_denisova.j_unit.commands.Commands;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ParseCommand {

    public static void parseCommand(String[] commands, HashMap<String, Commands> listCommands) {
        try {
            Commands command;
            if (commands[0].equals("")) {
                throw new NoSuchElementException();
            }
            if (commands[0].equals("show")) {
                commands[0] += "_" + commands[1];
            }

            if (listCommands.containsKey(commands[0])) {
                command = listCommands.get(commands[0]);
                if (commands.length - 1 != command.numberOfArguments()) {
                    throw new NoSuchElementException("Unexpected number of arguments: "
                            + command.numberOfArguments() + " required");
                }
                command.putArguments(commands);
            } else {
                throw new NoSuchElementException(commands[0] + " is unknown command");
            }
            command.execute();

        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }
}
