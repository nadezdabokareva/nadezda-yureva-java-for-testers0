package tests.GroupTest;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupCreatingTest extends TestBase {

    @Test
    public void canCreateGroupTest() {
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

    //Вариант создания нескольких групп (с разницей в названии в зависимости от цифрв счетчика)
    @Test
    public void canCreateMultipleGroupsTest0(){
        int n = 5;

        int groupCount = app.groups().getCount();

        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData("gn" + i,"gl", "footer"));
        }

        int newGroupCount = app.groups().getCount();

        Assertions.assertEquals(groupCount + n, newGroupCount);
    }

    @Test
    public void canCreateMultipleGroupsTest(){
        int n = 5;

        int groupCount = app.groups().getCount();

        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i * 10),"gl", "footer"));
        }

        int newGroupCount = app.groups().getCount();

        Assertions.assertEquals(groupCount + n, newGroupCount);
    }
}
