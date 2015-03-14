package ru.fizteh.fivt.students.elina_denisova.j_unit.interpreter;

import ru.fizteh.fivt.students.elina_denisova.j_unit.commands.Commands;
import ru.fizteh.fivt.students.elina_denisova.j_unit.exception.HandlerException;

import java.util.ArrayList;
import java.util.HashMap;

public class PackageParse {
    public static void parse(HashMap<String, Commands> listCommands, String[] arg) {
        try {
            ArrayList<String> current = new ArrayList<String>();
            for (int i = 0; i < arg.length; ++i) {
                current.clear();
                while (i < arg.length) {
                    if (!(arg[i].contains(";"))) {
                        current.add(arg[i]);
                        i++;
                    } else {
                        current.add(arg[i].substring(0, arg[i].indexOf(";")));
                        break;
                    }
                }
                if (current.size() == 0) {
                    return;
                }
                String[] commands = new String[current.size()];
                commands = current.toArray(commands);
                ParseCommand.parseCommand(commands, listCommands);
            }
        } catch (IllegalMonitorStateException e) {
            System.out.println("Goodbye");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            HandlerException.handler("Wrong arguments", e);
        } catch (Exception e) {
            HandlerException.handler( e);
        }
    }
}
