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
