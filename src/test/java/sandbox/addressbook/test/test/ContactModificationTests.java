package sandbox.addressbook.test.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import sandbox.addressbook.test.module.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        if (! appManage.getContactHelper().isThereAContact()){
            appManage.getNavigationHelper().gotoNewContact();
            appManage.getContactHelper().createContact(new ContactData("James", "Jones",
                    "Jam.Jones", "QA", "Infotecs",
                    "Manchester, Stadium Old Trafford", "2780857",
                    "89053555178", "james.jones@oldtrafford.com",
                    "17", "January", "1985", "test1"));
        }

        List<ContactData> before = appManage.getContactHelper().getContactList();
        appManage.getContactHelper().selectedContact(before.size() - 1);
        appManage.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() -1).getId(),"James", "Jones",
                "Jam.Jones", "QA", "Infotecs",
                "Manchester, Stadium Old Trafford", "2780857",
                "89053555178", "james.jones@oldtrafford.com", "17",
                "January", "1985", null);
        appManage.getContactHelper().fillContactForm(contact);
        appManage.getContactHelper().submitContactUpdate();
        appManage.getSessionHelper();
        List<ContactData> after = appManage.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
