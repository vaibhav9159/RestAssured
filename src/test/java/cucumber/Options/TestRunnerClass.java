package cucumber.Options;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "/Users/vsrivastava/eclipse-workspace/RestAssuredTest/src/test/java/features",
					glue = {"stepdefinations"}
				
		)
public class TestRunnerClass extends AbstractTestNGCucumberTests{

}
