package au.com.batch.report.domain;

import java.util.Objects;

public class ProductInfo {
    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expirationDate;

    public ProductInfo() {
    }

    public ProductInfo(String exchangeCode, String productGroupCode, String symbol, String expirationDate) {
        this.exchangeCode = exchangeCode;
        this.productGroupCode = productGroupCode;
        this.symbol = symbol;
        this.expirationDate = expirationDate;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return exchangeCode.equals(that.exchangeCode) &&
                productGroupCode.equals(that.productGroupCode) &&
                symbol.equals(that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeCode, productGroupCode, symbol);
    }

	@Override
	public String toString() {
		return "ProductInfo [exchangeCode=" + exchangeCode + ", productGroupCode=" + productGroupCode + ", symbol="
				+ symbol + "]";
	}
    
    
}
