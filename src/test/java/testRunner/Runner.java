package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import stepDefiniton.stepDefinition;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\ECPLLISE2022\\Mavan_Stock\\FeatureFiles\\Supplier.feature", glue="stepDefiniton", dryRun = false)
public class Runner 
{
	

}
