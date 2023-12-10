package tests.GroupTest;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeleteGroupTest extends TestBase {

  @Test
  public void canDeleteGroupTest() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("", "gl", "footer", "gn"));
    }

    var oldGroups = app.groups().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroups.size());

    app.groups().removeGroup(oldGroups.get(index));

    var newGroups = app.groups().getList();

    var expextedList = new ArrayList<>(oldGroups);
    expextedList.remove(index);

    Assertions.assertEquals(newGroups, expextedList);
  }

  @Test
  public void canDeleteAllGroupsAtOnce() {
    if (app.groups().getCount() == 0) {
      app.groups().createGroup(new GroupData("", "gl", "footer", "gn"));
    }

    app.groups().removeAllGroup();

    Assertions.assertEquals(0, app.groups().getCount());
  }

}
