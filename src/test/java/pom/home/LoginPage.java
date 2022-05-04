package pom.home;

import lombok.Getter;

@Getter
public class LoginPage {

    private final String inputEmail = "//input[@id='ember7']";
    private final String inputPassword = "//input[@id='ember9']";
    private final String btnSignIn = "//button[@id='ember11']";
    private final String icoPost = "//span[@class='icon green']";
    private final String inputTitlePost = "//textarea[@placeholder='Post title']";
    private final String btnPublish = "//div[@class='gh-publishmenu ember-view']";
    private final String btnConfirmPublish = "//button[@class='gh-btn gh-btn-black gh-publishmenu-button gh-btn-icon ember-view']";
    private final String btnPopUpPublish = "//button[@class='gh-btn gh-btn-black gh-btn-icon ember-view']";
    private final String alertArticle = "//article[@class='gh-notification gh-notification-passive ember-view']";

}
