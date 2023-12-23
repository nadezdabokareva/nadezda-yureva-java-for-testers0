package tests.GroupTest;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class DeleteGroupTest extends TestBase {

  @Test
  public void canDeleteGroupTest() {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
    }

    var oldGroups = app.hbm().getGroupList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());

    app.groups().removeGroup(oldGroups.get(index));

    var newGroups = app.hbm().getGroupList();

    var expectedList = new ArrayList<>(oldGroups);
    expectedList.remove(index);

    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  public void canDeleteAllGroupsAtOnce() {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
    }

    app.groups().removeAllGroup();

    Assertions.assertEquals(0, app.hbm().getGroupCount());
  }

}
