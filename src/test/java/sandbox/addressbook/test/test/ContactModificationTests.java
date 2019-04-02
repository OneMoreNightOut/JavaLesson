package sandbox.addressbook.test.test;

import org.testng.annotations.Test;
import sandbox.addressbook.test.module.ContactData;

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
        appManage.getContactHelper().initContactModification();
        appManage.getContactHelper().fillContactForm(new ContactData("James", "Jones",
                "Jam.Jones", "QA", "Infotecs",
                "Manchester, Stadium Old Trafford", "2780857",
                "89053555178", "james.jones@oldtrafford.com", "17",
                "January", "1985", null));
        appManage.getContactHelper().submitContactUpdate();
        appManage.getSessionHelper();

    }

}
