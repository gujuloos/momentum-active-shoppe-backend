package za.co.momentum.activeshoppe.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "active_days_points")
    private long activeDaysPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
