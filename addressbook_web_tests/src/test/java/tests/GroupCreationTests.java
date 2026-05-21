package tests;

import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {


    public static List<GroupDate> groupProvider() {
        var result = new ArrayList<GroupDate>();
        for (var name : List.of("", "group name")){
            for (var header : List.of("", "group header")){
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupDate(name, header, footer));
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new GroupDate(randomString(i * 10), randomString(i*10), randomString(i*10)));
        }
        return result;
    }




    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupDate group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);

    }

    public static List<GroupDate> negativeGroupProvider() {
        var result = new ArrayList<GroupDate>(List.of(
                new GroupDate("group name'", "","")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateMultipleGroup(GroupDate group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount , newGroupCount);

    }
}
