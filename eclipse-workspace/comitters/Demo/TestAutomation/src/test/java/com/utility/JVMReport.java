package com.utility;

import java.io.File;
import java.util.*;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class JVMReport {
public static void generateReport(String jsonFile) {
	File file = new File(System.getProperty("user.dir")+"src\\test\\resources\\Reports");
	Configuration configure = new Configuration(file, "Sauce-labs");
	configure.addClassifications("OS", "Windows10");
	configure.addClassifications("Browser", "Chrome");
	List<String> jsonFiles = new ArrayList<>();
	jsonFiles.add(jsonFile);
	
	ReportBuilder report = new ReportBuilder(jsonFiles, configure);
	report.generateReports();
	
}
}
