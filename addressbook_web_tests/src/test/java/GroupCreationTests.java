import model.GroupDate;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup(new GroupDate("name", "header", "footer"));

    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupDate());

    }
    @Test
    public void CanCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GroupDate();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);

    }
}
