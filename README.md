# Challenge

## Description

- The following automation template is to apply for the position of Senior QA Automations Engineer at Startupy.

## Pre-requisites

- The template is coded in Java and uses the Maven project manager with JUnit, Lombok (which can be downloaded from https://projectlombok.org/download and installed according to https://projectlombok.org/setup/javac) and preferably the Cucumber plugins installed in the IDE.

## Structure

- Page class: The Page is where the page objects are, without any interaction with the page elements, only the mappings.
- Logic class: In the Logic is where we create the logical methods for the interaction with the objects of the Page, such as: click, write and others. This class also consumes the data, which is implemented according to the business rule of each project.
- Steps class: The Steps is where we associate the steps of the BDD framework with the logical framework.
- Runner class: In the Runner is where we call the scenarios to run according to their names, id, mass type or platform (part of this data is expressed in annotations in the BDD framework).
- Evidence is generated in PDF, Json and HTML format.
- Tests are recorded.
- Compatibility to run the tests in several browsers. (it is necessary to set the global variable "browser").

## Execution via Maven

To run via the Maven command line, use: 'mvn test -Dtest=<class_name>'. Example: mvn test -Dtest=GoogleRunner.  

You can also add filters via tags, like this: mvn test -Dtest=GoogleRunner "-Dcucumber.options=--tags '@web and not @GGL_00002'"

### References

##### Maven Dependencies

```xml
	<dependencies>
			<dependency>
				<groupId>org.seleniumhq.selenium</groupId>
				<artifactId>selenium-java</artifactId>
				<version>3.141.59</version>
			</dependency>

			<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<version>4.13.1</version>
				<scope>test</scope>
			</dependency>

			<dependency>
				<groupId>io.cucumber</groupId>
				<artifactId>cucumber-java</artifactId>
				<version>${cucumber.version}</version>
			</dependency>

			<dependency>
				<groupId>io.cucumber</groupId>
				<artifactId>cucumber-junit</artifactId>
				<version>${cucumber.version}</version>
			</dependency>

			<dependency>
				<groupId>tech.grasshopper</groupId>
				<artifactId>extentreports-cucumber6-adapter</artifactId>
				<version>2.8.2</version>
			</dependency>

			<dependency>
				<groupId>com.googlecode.json-simple</groupId>
				<artifactId>json-simple</artifactId>
				<version>1.1.1</version>
			</dependency>

			<dependency>
				<groupId>commons-logging</groupId>
				<artifactId>commons-logging</artifactId>
				<version>1.2</version>
			</dependency>

			<dependency>
				<groupId>org.projectlombok</groupId>
				<artifactId>lombok</artifactId>
				<version>1.18.12</version>
				<scope>provided</scope>
			</dependency>

			<dependency>
				<groupId>org.apache.logging.log4j</groupId>
				<artifactId>log4j-core</artifactId>
				<version>2.13.3</version>
			</dependency>

			<dependency>
				<groupId>org.apache.logging.log4j</groupId>
				<artifactId>log4j-api</artifactId>
				<version>2.13.3</version>
			</dependency>

			<dependency>
				<groupId>org.jetbrains</groupId>
				<artifactId>annotations</artifactId>
				<version>RELEASE</version>
				<scope>compile</scope>
			</dependency>

			<dependency>
				<groupId>io.github.bonigarcia</groupId>
				<artifactId>webdrivermanager</artifactId>
				<version>3.8.1</version>
			</dependency>

			<dependency>
				<groupId>com.github.stephenc.monte</groupId>
				<artifactId>monte-screen-recorder</artifactId>
				<version>0.7.7.0</version>
			</dependency>
		</dependencies>  
```

##### Java Code

###### Chrome Driver

```java
  WebDriverManager.chromedriver().setup();
  driver = new ChromeDriver();
```

###### Firefox Driver

```java
  WebDriverManager.firefoxdriver().setup();
  driver = new FirefoxDriver();
```

###### Opera Driver

```java
  WebDriverManager.operadriver().setup();
  driver = new FirefoxDriver();
```

###### /src/test/java/pom/home/LoginLogic.java

```java
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
```

###### /src/test/java/pom/home/LoginPage.java

```java
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
```

###### /src/test/java/pom/BasePage.java

```java
	package pom;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.*;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.opera.OperaDriver;
	import org.openqa.selenium.opera.OperaOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.util.List;

	public class BasePage {

		protected static WebDriver driver;
		private static WebDriverWait wait;
		private static Actions action;

		static {
			String browserName = System.getenv("browser");

			switch (browserName)
			{
				case "CHROME":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(chromeOptions());
					wait = new WebDriverWait(driver, 10);
					break;
				case "FIREFOX":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver(firefoxOptions());
					wait = new WebDriverWait(driver, 10);
					break;
				case "OPERA":
					WebDriverManager.operadriver().setup();
					driver = new OperaDriver(operaOptions());
					wait = new WebDriverWait(driver, 10);
					break;
				default:
					throw new IllegalArgumentException(browserName);
			}
		}

		private static ChromeOptions chromeOptions() {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			return chromeOptions;
		}

		private static FirefoxOptions firefoxOptions(){
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--headless");
			return firefoxOptions;
		}

		private static OperaOptions operaOptions(){
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.addArguments("--headless");
			return operaOptions;
		}

		public BasePage(WebDriver driver) {
			BasePage.driver = driver;
			wait = new WebDriverWait(driver, 10);
			action = new Actions(driver);
		}

		public static void navigateTo(String url) {
			driver.get(url);
			driver.manage().window().maximize();
		}

		public static void closeBrowser() {
			driver.quit();
		}

		protected WebElement find(String locator) {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		}

		private List<WebElement> finds(String locator) {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
		}

		public String title() {
			return driver.getTitle();
		}

		public void clickElement(String locator) {
			find(locator).click();
		}

		public void write(String locator, String textToWrite) {
			find(locator).clear();
			find(locator).sendKeys(textToWrite);
		}

		public String textFromElement(String locator) {
			return find(locator).getText();
		}

		public boolean elementIsDisplayed(String locator) {
			return find(locator).isDisplayed();
		}
	}
```

###### /src/test/java/pom/recordingTest/MyScreenRecorder.java

```java
	package pom.recordingTest;

	import java.awt.AWTException;
	import java.awt.Dimension;
	import java.awt.GraphicsConfiguration;
	import java.awt.GraphicsEnvironment;
	import java.awt.Rectangle;
	import java.awt.Toolkit;
	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.monte.media.Format;
	import org.monte.media.FormatKeys.MediaType;
	import org.monte.media.Registry;
	import org.monte.media.math.Rational;
	import org.monte.screenrecorder.ScreenRecorder;

	import static org.monte.media.AudioFormatKeys.*;
	import static org.monte.media.VideoFormatKeys.*;

	public class MyScreenRecorder extends ScreenRecorder{

		public static ScreenRecorder screenRecorder;
		public String name;

		public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
								Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name)
				throws IOException, AWTException {
			super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
			this.name = name;

		}

		@Override
		protected File createMovieFile(Format fileFormat) throws IOException {

			if (!movieFolder.exists()) {
				movieFolder.mkdirs();
			} else if (!movieFolder.isDirectory()) {
				throw new IOException("\"" + movieFolder + "\" is not a directory.");
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
			return new File(movieFolder,
					name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));

		}

		public static void startRecording(String methodName) throws Exception {
			File file = new File("./recordings/");

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = screenSize.width;
			int height = screenSize.height;

			Rectangle captureSize = new Rectangle(0, 0, width, height);

			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
					getDefaultScreenDevice()
					.getDefaultConfiguration();

			screenRecorder = new MyScreenRecorder(gc, captureSize,
					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
							Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
					null, file, methodName);

			screenRecorder.start();

		}

		public static void stopRecording() throws Exception {
			screenRecorder.stop();
		}
	}	
```

###### /src/test/java/runner/TestRunner.java

```java
	package runner;

	import io.cucumber.junit.Cucumber;
	import io.cucumber.junit.CucumberOptions;
	import org.junit.AfterClass;
	import org.junit.runner.RunWith;
	import org.junit.runners.Parameterized;
	import pom.BasePage;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = {"src/test/resources/features"},
			glue = {"steps"},
			plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-reports.json"},
			monochrome = true,
			snippets = CucumberOptions.SnippetType.CAMELCASE
	)

	public class TestRunner {

		@AfterClass
		public static void cleanDriver() {
			BasePage.closeBrowser();
		}
	}
```

###### /src/test/java/steps/Hooks.java

```java
	package steps;

	import io.cucumber.java.After;
	import io.cucumber.java.Before;
	import io.cucumber.java.Scenario;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import pom.BasePage;
	import pom.recordingTest.MyScreenRecorder;

	public class Hooks extends BasePage {

		public Hooks() {
			super(driver);
		}

		@Before
		public void test() throws Exception {
			MyScreenRecorder.startRecording("Video Recording");
		}

		@After
		public void tearDown(Scenario scenario) {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "The failure scenario, refer to the attached image.");
			}
		}
	}
```

###### /src/test/java/steps/LoginSteps.java

```java
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
```