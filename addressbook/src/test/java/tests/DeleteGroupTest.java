package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class DeleteGroupTest extends TestBase {

  @Test
  public void deleteGroupTest() {
    if (!app.groups().isGroupPresent()) {
      app.groups().createGroup(new GroupData("gn", "gl", "footer"));
    }
    app.groups().removeGroup();
  }

}
