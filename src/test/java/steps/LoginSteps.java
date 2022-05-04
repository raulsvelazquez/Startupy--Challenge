package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pom.home.LoginLogic;

public class LoginSteps {

    LoginLogic loginLogic = new LoginLogic();

    @Given("Navigate to Ghost Admin")
    public void navigateToGhostAdmin() throws Exception {
        loginLogic.navigateGhostAdmin();
    }

    @When("I am successfully logged in")
    public void iAmSuccessfullyLoggedIn(DataTable dataTable) throws Exception {
        loginLogic.enterUserName(dataTable.cell(0, 0));
        loginLogic.enterPassword(dataTable.cell(0, 1));
    }

    @Then("I verify that I am logged in")
    public void iVerifyThatIAmLoggedIn() throws Exception {
        loginLogic.verifyIAmLoggedIn();
    }

    @When("I create the post")
    public void iCreateThePost() {
        loginLogic.createPost();
    }

    @Then("I verify that the post was created")
    public void iVerifyThatThePostWasCreated() {
        loginLogic.verifyPostCreated();
    }
}
