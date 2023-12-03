package tests.GroupTest;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupCreatingTest extends TestBase {

    @Test
    public void createGroupTest() {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData("gn", "gl", "footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
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
