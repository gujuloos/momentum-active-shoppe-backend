package za.co.momentum.activeshoppe.model;

public class CustomerDTO {

    private int customerId;

    private String fullName;

    private long activeDaysPoints;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getActiveDaysPoints() {
        return activeDaysPoints;
    }

    public void setActiveDaysPoints(long activeDaysPoints) {
        this.activeDaysPoints = activeDaysPoints;
    }
}
