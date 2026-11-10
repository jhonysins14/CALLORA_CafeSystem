/**
 * Connects a {@link Customer} to up to three {@link MenuItem} objects.
 * It computes the subtotal, discount, and total, and prints the receipt.
 * Items are kept in three separate fields (no arrays or collections).
 *
 * @author Callora
 * @version 1.0
 */
public class Order {

    /** Discount rate given to student customers. */
    private static final double STUDENT_DISCOUNT_RATE = 0.10;

    /** Width in characters of an item line on the receipt. */
    private static final int LINE_WIDTH = 52;

    /** The customer who placed this order. */
    private Customer customer;

    /** First item slot (null when empty). */
    private MenuItem item1;

    /** Second item slot (null when empty). */
    private MenuItem item2;

    /** Third item slot (null when empty). */
    private MenuItem item3;

    /** Number of items currently in the order. */
    private int itemCount;

    /** Identifier of this order, such as "1001". */
    private String orderId;

    /** True once loyalty points for this order have been awarded. */
    private boolean pointsAwarded;

    /**
     * Creates an empty order for a customer.
     *
     * @param orderId  the order identifier
     * @param customer the customer placing the order
     */
    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        // An order always needs a customer, so use a guest if none is given
        if (customer == null) {
            this.customer = new Customer();
        } else {
            this.customer = customer;
        }
        this.itemCount = 0;
        this.pointsAwarded = false;
    }

    /**
     * Adds an item to the first empty slot.
     *
     * @param item the menu item to add
     * @return true if the item was added, false if the order is full or the item is null
     */
    public boolean addItem(MenuItem item) {
        // Nothing to add
        if (item == null) {
            System.out.println("Cannot add a missing item.");
            return false;
        }

        // Fill the first empty slot; if all three are taken, the order is full
        if (item1 == null) {
            item1 = item;
        } else if (item2 == null) {
            item2 = item;
        } else if (item3 == null) {
            item3 = item;
        } else {
            System.out.println("Order is full. Cannot add " + item.getDescription() + ".");
            return false;
        }
        itemCount++;
        return true;
    }

    /**
     * Adds up the prices of all items in the order.
     * Each price comes from the item's own computePrice() (polymorphism).
     *
     * @return the subtotal in pesos
     */
    public double computeSubtotal() {
        double subtotal = 0.0;
        // Only filled slots contribute to the subtotal
        if (item1 != null) {
            subtotal += item1.computePrice();
        }
        if (item2 != null) {
            subtotal += item2.computePrice();
        }
        if (item3 != null) {
            subtotal += item3.computePrice();
        }
        return subtotal;
    }

    /**
     * Computes the discount for this order: 10% of the subtotal for students.
     *
     * @return the discount in pesos (0 for non-students)
     */
    public double computeDiscount() {
        // Only students get the discount
        if (customer.isStudent()) {
            return computeSubtotal() * STUDENT_DISCOUNT_RATE;
        } else {
            return 0.0;
        }
    }

    /**
     * Computes the total after the student discount (if any).
     *
     * @return the total in pesos
     */
    public double computeTotal() {
        // Students pay the subtotal minus 10%; others pay the full subtotal
        if (customer.isStudent()) {
            return computeSubtotal() - computeDiscount();
        } else {
            return computeSubtotal();
        }
    }

    /**
     * Computes the total after the student discount and a voucher.
     * The total never goes below 0.
     *
     * @param voucher the voucher amount in pesos (negative values are treated as 0)
     * @return the total in pesos
     */
    public double computeTotal(double voucher) {
        // A negative voucher would increase the bill, so ignore it
        if (voucher < 0) {
            voucher = 0.0;
        }
        double total = computeTotal() - voucher;
        // The total can never be negative
        if (total < 0) {
            return 0.0;
        } else {
            return total;
        }
    }

    /**
     * Prints the receipt without a voucher.
     * Also awards loyalty points to the customer (once per order).
     */
    public void printReceipt() {
        printReceipt(0.0);
    }

    /**
     * Prints the receipt with a voucher applied.
     * Also awards loyalty points to the customer (once per order).
     *
     * @param voucher the voucher amount in pesos (0 for none)
     */
    public void printReceipt(double voucher) {
        // Ignore negative vouchers, same as computeTotal(double)
        if (voucher < 0) {
            voucher = 0.0;
        }

        String status;
        // Show "(Student)" next to the name of student customers
        if (customer.isStudent()) {
            status = " (Student)";
        } else {
            status = "";
        }
        System.out.println("Order #" + orderId + " - " + customer.getName() + status);

        printItemLine(item1);
        printItemLine(item2);
        printItemLine(item3);

        System.out.println(String.format("Subtotal: %.2f", computeSubtotal()));

        // The discount line only appears for students
        if (customer.isStudent()) {
            System.out.println(String.format("Student discount (%d%%): -%.2f",
                    (int) Math.round(STUDENT_DISCOUNT_RATE * 100), computeDiscount()));
        }

        // The voucher line only appears when a voucher was used
        if (voucher > 0) {
            System.out.println(String.format("Voucher: -%.2f", voucher));
        }

        double total = computeTotal(voucher);
        System.out.println(String.format("TOTAL: %.2f", total));

        // Award points only the first time, so printing twice cannot double them
        if (!pointsAwarded) {
            customer.addPoints(total);
            pointsAwarded = true;
        }
        System.out.println("Loyalty points: " + customer.getLoyaltyPoints());
        System.out.println();
    }

    /**
     * Prints one receipt line: the item description, dots, and the price.
     *
     * @param item the item to print (nothing is printed if it is null)
     */
    private void printItemLine(MenuItem item) {
        // Skip empty slots
        if (item == null) {
            return;
        }
        String label = item.getDescription();
        String price = String.format("%.2f", item.computePrice());
        StringBuilder line = new StringBuilder(label).append(' ');
        int dots = LINE_WIDTH - label.length() - price.length() - 2;
        // Always leave at least two dots
        if (dots < 2) {
            dots = 2;
        }
        for (int i = 0; i < dots; i++) {
            line.append('.');
        }
        line.append(' ').append(price);
        System.out.println(line);
    }

    /**
     * Gets the customer who placed this order.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the number of items in this order.
     *
     * @return the item count (0 to 3)
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Gets the identifier of this order.
     *
     * @return the order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the identifier of this order. Blank IDs are rejected.
     *
     * @param orderId the new order ID
     */
    public void setOrderId(String orderId) {
        // Only accept an ID that has visible characters
        if (orderId != null && !orderId.trim().isEmpty()) {
            this.orderId = orderId;
        } else {
            System.out.println("Error: order ID cannot be empty.");
        }
    }
}
