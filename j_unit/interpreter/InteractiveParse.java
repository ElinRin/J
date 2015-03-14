package ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter;

import ru.fizteh.fivt.students.elina_denisova.j_unit.commands.Commands;
import ru.fizteh.fivt.students.elina_denisova.j_unit.exception.HandlerException;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InteractiveParse {
    public static final String START = "$ ";
    public static void parse(HashMap<String, Commands> listCommands) {
        Scanner in = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(START);
                String[] commands = in.nextLine().trim().split("\\s+");
                for (int i = 0; i < commands.length; ++i) {
                    commands[i].trim();
                }

                ParseCommand.parseCommand(commands, listCommands);
            }
        } catch (IllegalMonitorStateException e) {
            in.close();
            System.out.println("Goodbye");
            System.exit(0);
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            in.close();
            HandlerException.handler(e);
        }
        in.close();
    }
}
