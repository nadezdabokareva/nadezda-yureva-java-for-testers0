package tests.GroupTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.RandomStringGenerator;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreatingTest extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("", "group footer")) {
//                    result.add(new GroupData()
//                            .withName(name)
//                            .withHeader(header)
//                            .withFooter(footer));
//                }
//            }

        //чтение файла построчно
            var json = " ";
            try (var reader = new FileReader("groups.json");
                var breader = new BufferedReader(reader)
            ) {
                var line = breader.readLine();
                while (line != null) {
                    json = json + line;
                    line = breader.readLine();
                }
            }

            //чтение файла целиком
//            var json = Files.readString(Paths.get("groups.json"));
            ObjectMapper mapper = new ObjectMapper();
            var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});
            result.addAll(value);
            return result;
        }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "", "", "group name'")));
        return result;
    }

    public static Stream<GroupData> singleRandomGroupProvider() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(RandomStringGenerator.randomString(10))
                .withHeader(RandomStringGenerator.randomString(20))
                .withFooter((RandomStringGenerator.randomString(30)));
        return Stream.generate(randomGroup).limit(3);
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroupsTest(GroupData group){
        var oldGroups = app.groups().getList();

        app.groups().createGroup(group);

        var newGroups = app.groups().getList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);

        var expectedList = new ArrayList<GroupData>(oldGroups);

        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));

        expectedList.sort(compareById);

        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannotCreateGroup(GroupData group){
        var oldGroups = app.groups().getList();

        app.groups().createGroup(group);

        var newGroups = app.groups().getList();

        Assertions.assertEquals(newGroups, oldGroups);
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroupProvider")
    public void canCreateGroupsFromDBTest(GroupData group){
        var oldGroups = app.jdbc().getGroupList();

        app.groups().createGroup(group);

        var newGroups = app.jdbc().getGroupList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();

        var expectedList = new ArrayList<GroupData>(oldGroups);

        expectedList.add(group.withId(maxId));

        expectedList.sort(compareById);

        Assertions.assertEquals(newGroups, expectedList);

    }

    @Test
    public void checkUIGroupListWithDbGroupLisT(){
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var dBGroups = app.jdbc().getGroupList();
        dBGroups.sort(compareById);

        var uiGroups = app.groups().getList();
        uiGroups.sort(compareById);

        Assertions.assertEquals(dBGroups, uiGroups);
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroupProvider")
    public void canCreateGroupsWithHbmTest(GroupData group){
        var oldGroups = app.hbm().getGroupList();

        app.groups().createGroup(group);

        var newGroups = app.hbm().getGroupList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();

        var expectedList = new ArrayList<GroupData>(oldGroups);

        expectedList.add(group.withId(maxId));

        expectedList.sort(compareById);

        Assertions.assertEquals(newGroups, expectedList);

    }
}
