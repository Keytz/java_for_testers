package tests;

import model.GroupDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {
    @Test
    void canModifyGroup(){
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupDate("", "name", "header", "footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testDate = new GroupDate().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testDate);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testDate.withId(oldGroups.get(index).id()));
        Comparator<GroupDate> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

    }

}
