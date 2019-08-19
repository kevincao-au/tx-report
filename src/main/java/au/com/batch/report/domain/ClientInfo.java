package au.com.batch.report.domain;

import java.util.Objects;

public class ClientInfo {
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subaccountNumber;

    public ClientInfo() {
    }

    public ClientInfo(String clientType, String clientNumber, String accountNumber, String subaccountNumber) {
        this.clientType = clientType;
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
        this.subaccountNumber = subaccountNumber;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSubaccountNumber() {
        return subaccountNumber;
    }

    public void setSubaccountNumber(String subaccountNumber) {
        this.subaccountNumber = subaccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return clientType.equals(that.clientType) &&
                clientNumber.equals(that.clientNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientType, clientNumber);
    }

	@Override
	public String toString() {
		return "ClientInfo [clientType=" + clientType + ", clientNumber=" + clientNumber + ", accountNumber="
				+ accountNumber + ", subaccountNumber=" + subaccountNumber + "]";
	}
    
    
}
