/**
 * Represents a person who places an order at the campus cafe.
 * Students receive a discount, and every customer earns loyalty points.
 *
 * @author Callora
 * @version 1.0
 */
public class Customer {

    /** Name used when no valid name is given. */
    private static final String DEFAULT_NAME = "Guest";

    /** Amount in pesos that earns one loyalty point. */
    private static final double PESOS_PER_POINT = 50.0;

    /** Name of the customer. */
    private String name;

    /** True if the customer is a student. */
    private boolean student;

    /** Loyalty points earned so far (never negative). */
    private int loyaltyPoints;

    /**
     * Creates a non-student customer named "Guest" with no points.
     */
    public Customer() {
        this(DEFAULT_NAME, false);
    }

    /**
     * Creates a non-student customer with the given name.
     *
     * @param name the customer's name
     */
    public Customer(String name) {
        this(name, false);
    }

    /**
     * Creates a customer with the given name and student status.
     *
     * @param name    the customer's name
     * @param student true if the customer is a student
     */
    public Customer(String name, boolean student) {
        // A missing or blank name is replaced by "Guest"
        if (name == null || name.trim().isEmpty()) {
            this.name = DEFAULT_NAME;
        } else {
            this.name = name;
        }
        this.student = student;
        this.loyaltyPoints = 0;
    }

    /**
     * Adds loyalty points directly. Negative amounts are rejected.
     *
     * @param points the number of points to add
     */
    public void addPoints(int points) {
        // Points can only be added, never subtracted
        if (points < 0) {
            System.out.println("Error: points cannot be negative.");
        } else {
            loyaltyPoints += points;
        }
    }

    /**
     * Adds loyalty points based on money spent: 1 point for every 50 pesos.
     * Partial amounts are not counted.
     *
     * @param amountSpent the amount spent in pesos
     */
    public void addPoints(double amountSpent) {
        // A negative amount earns nothing
        if (amountSpent < 0) {
            System.out.println("Error: amount spent cannot be negative.");
        } else {
            addPoints((int) (amountSpent / PESOS_PER_POINT));
        }
    }

    /**
     * Gets the customer's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name. Blank names are rejected.
     *
     * @param name the new name
     */
    public void setName(String name) {
        // Only accept a name that has visible characters
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: name cannot be empty.");
        }
    }

    /**
     * Tells whether the customer is a student.
     *
     * @return true if the customer is a student
     */
    public boolean isStudent() {
        return student;
    }

    /**
     * Sets the student status.
     *
     * @param student true if the customer is a student
     */
    public void setStudent(boolean student) {
        this.student = student;
    }

    /**
     * Gets the customer's loyalty points.
     *
     * @return the loyalty points
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Sets the loyalty points. Negative values are rejected
     * and the old value is kept.
     *
     * @param loyaltyPoints the new number of points
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        // Loyalty points may never go below zero
        if (loyaltyPoints < 0) {
            System.out.println("Error: loyalty points cannot be negative.");
        } else {
            this.loyaltyPoints = loyaltyPoints;
        }
    }
}
