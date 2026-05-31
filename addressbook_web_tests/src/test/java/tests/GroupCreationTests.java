package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


    public static List<GroupDate> groupProvider() throws IOException {
        var result = new ArrayList<GroupDate>();
        //   for (var name : List.of("", "group name")){
        //      for (var header : List.of("", "group header")){
        //          for (var footer : List.of("", "group footer")) {
        //              result.add(new GroupDate()
        //                       .withName(name)
        //                        .withHeader(header)
        //                        .withFooter(footer));
        //            }
        //       }
        //    }
        var mapper = new XmlMapper();

        var value = mapper.readValue(new File("group.xml"), new TypeReference<List<GroupDate>>() {
        });
        result.addAll(value);
        return result;
    }
    public static List<GroupDate> singleRandomGroup(){
       return List.of(new GroupDate()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30)));
    }


    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupDate group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupDate> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

    public static List<GroupDate> negativeGroupProvider() {
        var result = new ArrayList<GroupDate>(List.of(
                new GroupDate("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupDate group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);

    }
}
