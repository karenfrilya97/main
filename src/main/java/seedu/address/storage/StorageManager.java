package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.DeskBoardChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDeskBoard;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of DeskBoard data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DeskBoardStorage deskBoardStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(DeskBoardStorage deskBoardStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.deskBoardStorage = deskBoardStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public String getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ DeskBoard methods ==============================

    @Override
    public String getDeskBoardFilePath() {
        return deskBoardStorage.getDeskBoardFilePath();
    }

    @Override
    public Optional<ReadOnlyDeskBoard> readDeskBoard() throws DataConversionException, IOException {
        return readDeskBoard(deskBoardStorage.getDeskBoardFilePath());
    }

    @Override
    public Optional<ReadOnlyDeskBoard> readDeskBoard(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return deskBoardStorage.readDeskBoard(filePath);
    }

    @Override
    public void saveDeskBoard(ReadOnlyDeskBoard deskBoard) throws IOException {
        saveDeskBoard(deskBoard, deskBoardStorage.getDeskBoardFilePath());
    }

    @Override
    public void saveDeskBoard(ReadOnlyDeskBoard deskBoard, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        deskBoardStorage.saveDeskBoard(deskBoard, filePath);
    }

    @Override
    @Subscribe
    public void handleDeskBoardChangedEvent(DeskBoardChangedEvent dbce) {
        logger.info(LogsCenter.getEventHandlingLogMessage(dbce, "Local data changed, saving to file"));
        try {
            saveDeskBoard(dbce.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }

    //@@author karenfrilya97
    @Override
    public void exportDeskBoard(ReadOnlyDeskBoard deskBoard, String filePath) throws IOException {
        logger.info("Exporting data to file: " + filePath);
        deskBoardStorage.saveDeskBoard(deskBoard, filePath);
    }
}
