package pom.home;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pom.BasePage;

import static org.junit.Assert.*;

public class LoginLogic extends BasePage {

    LoginPage loginPage = new LoginPage();
    private static final Logger log = LogManager.getLogger(LoginLogic.class);

    public LoginLogic() {
        super(driver);
    }

    public void navigateGhostAdmin() throws Exception {
        log.info("Navigate to Ghost Admin");

        navigateTo("http://localhost:2368/ghost");
    }

    public void enterUserName(String criteria) throws Exception {
        log.info("I enter the user name " + criteria);

        write(loginPage.getInputEmail(), criteria);
    }

    public void enterPassword(String criteria) throws Exception {
        log.info("I enter the password " + criteria);

        write(loginPage.getInputPassword(), criteria);
        clickElement(loginPage.getBtnSignIn());
    }

    public void verifyIAmLoggedIn() throws Exception {
        log.info("I verify that I am logged in");

        assertEquals("The elements are not equal", "Sign In - QA Automations", title());
    }

    public void createPost() {
        log.info("I create the post");

        clickElement(loginPage.getIcoPost());
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\image\\1.jpg");
        write(loginPage.getInputTitlePost(), "Title of the test post");
        clickElement(loginPage.getBtnPublish());
        clickElement(loginPage.getBtnConfirmPublish());
        clickElement(loginPage.getBtnPopUpPublish());
    }

    public void verifyPostCreated() {
        log.info("I verify that the post was created");

        assertTrue("The item is not displayed", elementIsDisplayed(loginPage.getAlertArticle()));
    }
}
