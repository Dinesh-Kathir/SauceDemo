package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.utility.JVMReport;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src\\test\\resources",glue = "com.stepdefiniton" , dryRun = false,monochrome = true, 
		plugin = {"pretty","json:src\\test\\resources\\Reports\\cucumber.json"},tags = "@cart")
public class TestRunner {
	@AfterClass
	public static  void afterClass() {
		// TODO Auto-generated method stub
		JVMReport.generateReport(System.getProperty("user.dir")+"src\\test\\resources\\Reports\\cucumber.json");

	}

}
