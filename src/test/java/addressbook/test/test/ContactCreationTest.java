package addressbook.test.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import addressbook.test.model.ContactData;
import addressbook.test.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src\\test\\java\\addressbook\\test\\resourses\\contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line = reader.readLine();

        }

        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contact = (List<ContactData>) xStream.fromXML(xml);
        return contact.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(
                new File("src\\test\\java\\addressbook\\test\\resourses\\contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null){
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contact = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contact.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsFromJson")
    public void initContactCreation(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().homePage();
        app.contact().addContact(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));

    }

}
