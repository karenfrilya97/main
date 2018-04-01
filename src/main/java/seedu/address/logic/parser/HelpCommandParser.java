package seedu.address.logic.parser;

import java.util.ArrayList;

import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;



//@@author jasmoon

/**
 * Parses input arguments and create a new HelpCommand object.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    public final ArrayList<String> availableCommands;

    /**
     * HelpCommandParser constructor - creates an ArrayList which contains all commands open to the help function.
     */
    public HelpCommandParser()  {
        availableCommands = new ArrayList<String>();
        availableCommands.add(RemoveCommand.COMMAND_WORD);
        availableCommands.add(EditCommand.COMMAND_WORD);
        availableCommands.add(FindCommand.COMMAND_WORD);
        availableCommands.add(SelectCommand.COMMAND_WORD);
        availableCommands.add(TaskCommand.COMMAND_WORD);
        availableCommands.add(CompleteCommand.COMMAND_WORD);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public HelpCommand parse(String args) throws ParseException {

        String commandRequest = args.trim();
        if (commandRequest.length() == 0) {
            return new HelpCommand();
        } else {
            if (availableCommands.contains(commandRequest)) {
                return new HelpCommand(args);
            } else {
                throw new ParseException(formInvalidMessage(commandRequest));
            }
        }
    }

    /**
     *
     * @param commandRequest
     * @return String message for invalid command request.
     */
    private String formInvalidMessage(String commandRequest)    {
        return "Help for '" + commandRequest + "' is unknown or not available.";
    }
}

