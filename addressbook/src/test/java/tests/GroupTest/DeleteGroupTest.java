package tests.GroupTest;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class DeleteGroupTest extends TestBase {

  @Test
  public void deleteGroupTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("gn", "gl", "footer"));
    }

    int groupCount = app.groups().getCount();

    app.groups().removeGroup();

    int newGroupCount = app.groups().getCount();

    Assertions.assertEquals(groupCount - 1, newGroupCount);
  }

  @Test
  public void canDeleteAllGroupsAtOnce() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("gn", "gl", "footer"));
    }

    app.groups().removeAllGroup();

    Assertions.assertEquals(0, app.groups().getCount());
  }

}
