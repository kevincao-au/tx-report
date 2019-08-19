package au.com.batch.report.domain;

import java.util.Objects;

/**
 *
 */
public class ClientProductTuple {
    private ClientInfo clientInfo;
    private ProductInfo productInfo;

    public ClientProductTuple() {
    }

    public ClientProductTuple(ClientInfo clientInfo, ProductInfo productInfo) {
        this.clientInfo = clientInfo;
        this.productInfo = productInfo;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientProductTuple that = (ClientProductTuple) o;
        return clientInfo.equals(that.clientInfo) &&
                productInfo.equals(that.productInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientInfo, productInfo);
    }

	@Override
	public String toString() {
		return "ClientProductTuple [clientInfo=" + clientInfo + ", productInfo=" + productInfo + "]";
	}
    
    
}
