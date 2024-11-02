package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:build/cucumber-reports/cucumber.json",
                "rerun:build/cucumber-reports/rerun.txt",
                "html:build/cucumber-reports/cucumber-html-report.html"
        },
        glue = {"stepDefinitions", "hook"},
        features = {"src/test/java/features"},
        tags = "@Run"
)
public class AtfRunner {
}
