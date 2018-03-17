package seedu.address.testutil;

import seedu.address.model.activity.Activity;
import seedu.address.model.activity.DateTime;
import seedu.address.model.activity.Event;
import seedu.address.model.activity.Name;
import seedu.address.model.activity.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

public class EventBuilder extends ActivityBuilder{

    public static final String DEFAULT_NAME = "CIP";
    public static final String DEFAULT_DATETIME = "04/04/2018 08:10";
    public static final String DEFAULT_REMARK = "123, Jurong West Ave 6";
    public static final String DEFAULT_TAGS = "optional";

    private Name name;
    private DateTime dateTime;
    private Remark remark;
    private Set<Tag> tags;

    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        dateTime = new DateTime(DEFAULT_DATETIME);
        remark = new Remark(DEFAULT_REMARK);
        tags = SampleDataUtil.getTagSet(DEFAULT_TAGS);
    }

    /**
     * Initializes the ActivityBuilder with the data of {@code activityToCopy}.
     */
    public EventBuilder(Activity activityToCopy) {
        name = activityToCopy.getName();
        dateTime = activityToCopy.getDateTime();
        remark = activityToCopy.getRemark();
        tags = new HashSet<>(activityToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Activity} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Activity} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Activity} that we are building.
     */
    public EventBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Activity} that we are building.
     */
    public EventBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }


    public Activity build() {
        return new Activity(name, dateTime, remark, tags);
    }

}
