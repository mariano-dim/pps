package edu.proyectofinal.integradorrs.model;

/**
 * Created by mariano on 24/03/16.
 */
public class Transaction extends AbstractModel {

    private String merchant;
    private String operationId;
    private String amount;
    private String emailCliente;
    private String security;


    public Transaction() {
    }

    public Transaction(String merchant, String operationId, String amount, String emailCliente, String security) {
        this.merchant = merchant;
        this.operationId = operationId;
        this.amount = amount;
        this.emailCliente = emailCliente;
        this.security = security;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "merchant='" + merchant + '\'' +
                ", operationId='" + operationId + '\'' +
                ", amount='" + amount + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                ", security='" + security + '\'' +
                '}';
    }

}
