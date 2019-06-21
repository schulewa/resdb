package com.apschulewitz.resdb;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "junit:target/junit-report.xml" }, features = "classpath:bdd-features")
@ActiveProfiles(value = "test")
public class CucumberFeatureRunner {
}
