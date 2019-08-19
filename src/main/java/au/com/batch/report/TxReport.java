package au.com.batch.report;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TxReport {
	private final static Logger logger = LoggerFactory.getLogger(TxReport.class);  

	public static void main(String[] args) {
		if (args.length < 2) {
			logger.info("Usage: TxReport inputFile outputFile");
			System.exit(0);
		}
		String txtFileName = args[0];
		String csvFileName = args[1];

		logger.info("Input File : {}", txtFileName);

		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName))) {
			
			reportSummaryProcessor.process(new FileReader(txtFileName), writer);
			logger.info("Generate a daily summary report successful - '{}'", csvFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
        	
    }
	
}
