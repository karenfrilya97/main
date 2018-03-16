package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.DeskBoard;
import seedu.address.testutil.TypicalPersons;

public class XmlSerializableDeskBoardTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlSerializableDeskBoardTest/");
    private static final File TYPICAL_PERSONS_FILE = new File(TEST_DATA_FOLDER + "typicalPersonsAddressBook.xml");
    private static final File INVALID_PERSON_FILE = new File(TEST_DATA_FOLDER + "invalidPersonAddressBook.xml");
    private static final File INVALID_TAG_FILE = new File(TEST_DATA_FOLDER + "invalidTagAddressBook.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        XmlSerializableDeskBoard dataFromFile = XmlUtil.getDataFromFile(TYPICAL_PERSONS_FILE,
                XmlSerializableDeskBoard.class);
        DeskBoard addressBookFromFile = dataFromFile.toModelType();
        DeskBoard typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        XmlSerializableDeskBoard dataFromFile = XmlUtil.getDataFromFile(INVALID_PERSON_FILE,
                XmlSerializableDeskBoard.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test

    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        XmlSerializableDeskBoard dataFromFile = XmlUtil.getDataFromFile(INVALID_TAG_FILE,
                XmlSerializableDeskBoard.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }
}