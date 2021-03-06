//@author jasmoon
package seedu.address.ui;

import static seedu.address.ui.util.DateTimeUtil.getDisplayedEndDateTime;
import static seedu.address.ui.util.DateTimeUtil.getDisplayedStartDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.activity.Activity;
import seedu.address.model.activity.Event;

/**
 * An UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventListCard.fxml";

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startDateTime;
    @FXML
    private Label endDateTime;
    @FXML
    private Label locationEvent;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;

    public EventCard(Activity event, int displayedIndex)   {
        super(FXML);
        assert (event instanceof  Event) : "The activity passed in should be of type Event";
        this.event = (Event) event;
        id.setText(displayedIndex + ". ");
        name.setText(this.event.getName().fullName);
        startDateTime.setText(getDisplayedStartDateTime(this.event));
        endDateTime.setText(getDisplayedEndDateTime(this.event));
        if (this.event.getLocation() != null) {
            locationEvent.setText(this.event.getLocation().toString());
        } else {
            locationEvent.setVisible(false);
        }
        if (this.event.getLocation() != null) {
            remark.setText(this.event.getRemark().value);
        } else {
            remark.setVisible(false);
        }
        event.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventCard)) {
            return false;
        }

        // state check
        EventCard card = (EventCard) other;
        return id.getText().equals(card.id.getText())
                && event.equals(card.event);
    }
}
