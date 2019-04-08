package sandbox.addressbook.test.test;

import org.testng.Assert;
import org.testng.annotations.*;
import sandbox.addressbook.test.module.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        appManage.getNavigationHelper().goToGroupPage();
        if (!appManage.getGroupHelper().isThereAGroup()) {
            appManage.getGroupHelper().createGroup(new GroupData("test1", null,
                    null));
        }
        List<GroupData> before = appManage.getGroupHelper().getGroupList();
        appManage.getGroupHelper().selectGroup(before.size() - 1);
        appManage.getGroupHelper().deleteSelectGroup();
        List<GroupData> after = appManage.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

    }

}
