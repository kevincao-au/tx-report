/**
 * 
 */
package au.com.batch.report;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.batch.report.domain.ClientInfo;
import au.com.batch.report.domain.ClientProductTuple;
import au.com.batch.report.domain.ProductInfo;

/**
 * @author Kevin Cao
 *
 */

class ReportSummaryProcessorTest {
	private final static Logger logger = LoggerFactory.getLogger(ReportSummaryProcessorTest.class);  
		

	
	private Reader getFileReader(final String name) {
		return new InputStreamReader(getClass().getResourceAsStream(name));
	}
	
	
	@DisplayName("One transaction - One client with one product (18)")
	@Test
	void testParse1() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_01.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(1, tuple1.size(), "total size must be 1!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "1234", "0002", "0001"), 
						new ProductInfo("SGX", "FU", "NK", "20100910")), 18);
						
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	@DisplayName("Multiple transactions - One client with one product (-24)")
	@Test
	void testParse2() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_02.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(1, tuple1.size(), "total size must be 1!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0003", "0001"), 
						new ProductInfo("CME", "FU", "N1", "20100910")), -24);
						
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	
	
	@DisplayName("Multiple transactions - One client with multiple products")
	@Test
	void testParse3() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_03.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(2, tuple1.size(), "total size must be 2!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0003", "0001"), 
						new ProductInfo("CME", "FU", "N1", "20100910")), -48);
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0002", "0001"), 
					new ProductInfo("SGX", "FU", "NK", "20100910")), 6);

					
			
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	
	@DisplayName("Multiple transactions - One client with one product (30)")
	@Test
	void testParse4() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_04.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(1, tuple1.size(), "total size must be 1!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0003", "0001"), 
						new ProductInfo("CME", "FU", "N1", "20100910")), 30);
						
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	@DisplayName("Multiple transactions - Multiple clients with one product")
	@Test
	void testParse5() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_05.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(2, tuple1.size(), "total size must be 2!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0003", "0001"), 
						new ProductInfo("CME", "FU", "N1", "20100910")), -42);
			
			expected.put(new ClientProductTuple(new ClientInfo("CL", "1234", "0003", "0001"), 
					new ProductInfo("CME", "FU", "N1", "20100910")), 6);
			
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	
	@DisplayName("One transaction - One client with one product (10)")
	@Test
	void testParse6() throws IOException {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (Reader fileReader = getFileReader("/input/test_input_06.txt")) {
			Map<ClientProductTuple, Integer> tuple1 = reportSummaryProcessor.parse(fileReader);
			
			assertEquals(1, tuple1.size(), "total size must be 1!");
			
			Map<ClientProductTuple, Integer> expected = new HashMap<>();
			expected.put(new ClientProductTuple(new ClientInfo("CL", "4321", "0003", "0001"), 
						new ProductInfo("CME", "FU", "N1", "20100910")), 10);
						
			assertIterableEquals(expected.entrySet(), tuple1.entrySet(), "It is not matched!");
		}
	}
	
	
	@Test
	@Disabled
	void testProcess() {
		ReportSummaryProcessor reportSummaryProcessor = ReportSummaryProcessor.getInstance();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\dev\\test_input_04.csv"))) {
			
			reportSummaryProcessor.process(getFileReader("/input/test_input_04.txt"), writer);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
