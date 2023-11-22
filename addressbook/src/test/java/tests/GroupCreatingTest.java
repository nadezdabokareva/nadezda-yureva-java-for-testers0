package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreatingTest extends TestBase {

    @Test
    public void createGroupTest() {
        app.groups().createGroup(new GroupData("gn", "gl", "footer"));
    }

    @Test
    public void createGroupWithEmptyNameTest() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void createGroupWithNoEmptyNameOnlyTest() {
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("naaame");
        app.groups().createGroup(groupWithName);
    }
}
