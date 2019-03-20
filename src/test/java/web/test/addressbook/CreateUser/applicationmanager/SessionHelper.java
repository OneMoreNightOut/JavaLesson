package web.test.addressbook.CreateUser.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
        this.wd = wd;
    }

    public void login(String username, String password) {
        click(By.name("user"));
        type(By.name("user"), username);
        click(By.name("pass"));
        type(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void close() {
        click(By.linkText("Logout"));
        wd.quit();
    }
}