package seedu.address.logic.parser;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNMENT3_DEMO1_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.EXPORT_FILE_PATH;
import static seedu.address.logic.commands.CommandTestUtil.FILE_PATH_DESC_EXPORT;
import static seedu.address.logic.commands.CommandTestUtil.FILE_PATH_DESC_IMPORT;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.FilePath;
import seedu.address.model.activity.Event;
import seedu.address.model.activity.Task;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

//@@author Kyomian
public class DeskBoardParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DeskBoardParser parser = new DeskBoardParser();

    @Test
    public void parseCommand_task() throws Exception {
        Task task = new TaskBuilder().build();
        TaskCommand command = (TaskCommand) parser.parseCommand(TaskUtil.getTaskCommand(task));
        assertEquals(new TaskCommand(task), command);
    }

    //@@author jasmoon
    @Test
    public void parseCommand_event() throws Exception {
        Event event = new EventBuilder().build();
        EventCommand command = (EventCommand) parser.parseCommand(EventUtil.getEventCommand(event));
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parseCommandPlusAlias_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_ALIAS + " 3") instanceof ClearCommand);
    }

   /* @Test
    public void parseCommand_remove() throws Exception {
        RemoveCommand command = (RemoveCommand) parser.parseCommand(
                RemoveCommand.COMMAND_WORD + " " + INDEX_FIRST_ACTIVITY.getOneBased());
        assertEquals(new RemoveCommand("task", INDEX_FIRST_ACTIVITY), command);
    }*/

//
//    public void parseCommand_edit() throws Exception {
//        Person person = new PersonBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
//        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
//                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getPersonDetails(person));
//        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
//    }
//
//
//    public void parseCommand_exit() throws Exception {
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
//        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
//    }
//
//
//    public void parseCommand_find() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindCommand command = (FindCommand) parser.parseCommand(
//                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
//        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }
//
//
    //@@author jasmoon
    @Test
    public void parseCommandPlusAlias_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_ALIAS + " task") instanceof HelpCommand);
        try {
            parser.parseCommand(HelpCommand.COMMAND_WORD + " 3");
            fail("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(String.format(Messages.MESSAGE_INVALID_HELP_REQUEST, "3"), pe.getMessage());
        }
    }
//
//
//    public void parseCommand_history() throws Exception {
//        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
//        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);
//
//        try {
//            parser.parseCommand("histories");
//            fail("The expected ParseException was not thrown.");
//        } catch (ParseException pe) {
//            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
//        }
//    }
//
//
    @Test
    public void parseCommandPlusAlias_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_ALIAS + " task") instanceof ListCommand);
    }
//
//
//    public void parseCommand_select() throws Exception {
//        SelectCommand command = (SelectCommand) parser.parseCommand(
//                SelectCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
//        assertEquals(new SelectCommand(INDEX_FIRST_PERSON), command);
//    }
//
//
//    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
//        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
//        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
//    }
//
//
//    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
//        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
//        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
//    }
//

    //@@author karenfrilya97
    @Test
    public void parseCommand_import() throws Exception {
        FilePath filePath = new FilePath(ASSIGNMENT3_DEMO1_FILE_PATH);
        ImportCommand command = (ImportCommand) parser.parseCommand(ImportCommand.COMMAND_WORD + FILE_PATH_DESC_IMPORT);
        assertEquals(new ImportCommand(filePath), command);
    }

    @Test
    public void parseCommand_export() throws Exception {
        FilePath filePath = new FilePath(EXPORT_FILE_PATH);
        ExportCommand command = (ExportCommand) parser.parseCommand(ExportCommand.COMMAND_WORD + FILE_PATH_DESC_EXPORT);
        assertEquals(new ExportCommand(filePath), command);
    }

    //@@author
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand(" ");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
