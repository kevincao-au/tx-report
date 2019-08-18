package au.com.batch.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import static java.util.stream.Collectors.*;

import au.com.batch.report.domain.ClientInfo;
import au.com.batch.report.domain.ClientProductTuple;
import au.com.batch.report.domain.ProductInfo;
import net.sf.flatpack.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.flatpack.Parser;
import net.sf.flatpack.brparse.BuffReaderParseFactory;

public class ReportSummaryProcessor {
	private final Logger logger = LoggerFactory.getLogger(getClass());
		
	public final static InputStream TX_PZMAP_XML_INPUT_STREAM;
	
	static {
		TX_PZMAP_XML_INPUT_STREAM = ReportSummaryProcessor.class.getResourceAsStream("/tx.pzmap.xml");
	}


	/**
	 *
	 * @param fileReader
	 * @return
	 */
	public Map<ClientProductTuple, Integer> parse(final Reader fileReader) {
		Parser txParser = BuffReaderParseFactory.getInstance().newFixedLengthParser(
				new InputStreamReader(TX_PZMAP_XML_INPUT_STREAM), fileReader);

		return txParser.parseAsStream().stream()
				.collect(groupingBy(r -> new ClientProductTuple(
						new ClientInfo(
								r.getString("CLIENT_TYPE"),
								r.getString("CLIENT_NUMBER"),
								r.getString("ACCOUNT_NUMBER"),
								r.getString("SUBACCOUNT_NUMBER")),
						new ProductInfo(
								r.getString("EXCHANGE_CODE"),
								r.getString("PRODUCT_GROUP_CODE"),
								r.getString("SYMBOL"),
								r.getString("EXPIRATION_DATE")
						)), LinkedHashMap::new, summingInt(r -> r.getInt("QUANTITY_LONG") - r.getInt("QUANTITY_SHORT"))));

	}


	/**
	 *
	 * @param fileReader
	 * @param fileWriter
	 */
	public void process(final Reader fileReader, final Writer fileWriter) {
		parse(fileReader).entrySet().stream().forEach(entry -> {
			try {
				ClientProductTuple cpt = entry.getKey();
				ClientInfo clientInfo = cpt.getClientInfo();
				ProductInfo productInfo = cpt.getProductInfo();
				fileWriter.write(clientInfo.getClientType());
				fileWriter.write(clientInfo.getClientNumber());
				fileWriter.write(clientInfo.getAccountNumber());
				fileWriter.write(clientInfo.getSubaccountNumber());
				fileWriter.write(',');
				fileWriter.write(productInfo.getExchangeCode());
				fileWriter.write(productInfo.getProductGroupCode());
				fileWriter.write(productInfo.getSymbol());
				fileWriter.write(productInfo.getExpirationDate());
				fileWriter.write(',');
				fileWriter.write(String.valueOf(entry.getValue()));
				fileWriter.write(System.lineSeparator());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	

}
