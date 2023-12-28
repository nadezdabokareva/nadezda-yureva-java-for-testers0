package tests.GroupTest;

import common.RandomStringGenerator;
import model.GroupData;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "gl", "footer", "gn"));
        }


        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());

        var testData = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));

        app.groups().modifyGroup(oldGroups.get(index), testData);

        var newGroups = app.hbm().getGroupList();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));



        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

}
