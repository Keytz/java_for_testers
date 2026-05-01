package tests;

import model.GroupDate;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        app.groups().createGroup(new GroupDate("name", "header", "footer"));

    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupDate());

    }
    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupDate().withName("some name"));

    }
}
