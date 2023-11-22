package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("gr name", "gr header", "gr footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified group"));
    }
}
