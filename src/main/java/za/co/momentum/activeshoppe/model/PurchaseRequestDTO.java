package za.co.momentum.activeshoppe.model;

import java.util.List;

public class PurchaseRequestDTO {

    private int customerId;

    private List<String> productCodes;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<String> getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(List<String> productCodes) {
        this.productCodes = productCodes;
    }
}
