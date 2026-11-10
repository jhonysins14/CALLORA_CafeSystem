/**
 * Represents a drink sold at the campus cafe.
 * The price depends on the selected size (S, M, or L).
 *
 * @author Callora
 * @version 1.0
 */
public class Beverage extends MenuItem {

    /** Extra charge in pesos for a medium drink. */
    private static final double MEDIUM_SURCHARGE = 15.0;

    /** Extra charge in pesos for a large drink. */
    private static final double LARGE_SURCHARGE = 25.0;

    /** Size of the drink: "S", "M", or "L". */
    private String size;

    /**
     * Creates a small beverage with placeholder name, price, and code.
     */
    public Beverage() {
        super();
        this.size = "S";
    }

    /**
     * Creates a beverage with the given details.
     * An invalid size falls back to "S".
     *
     * @param name      the display name
     * @param basePrice the price of a small drink in pesos
     * @param itemCode  the unique menu code
     * @param size      the size: "S", "M", or "L"
     */
    public Beverage(String name, double basePrice, String itemCode, String size) {
        super(name, basePrice, itemCode);
        // Keep the size only if it is S, M, or L; otherwise default to small
        if (isValidSize(size)) {
            this.size = size.toUpperCase();
        } else {
            this.size = "S";
        }
    }

    /**
     * Checks whether a text is an allowed drink size.
     *
     * @param size the text to check
     * @return true if the text is S, M, or L (any letter case)
     */
    private static boolean isValidSize(String size) {
        return size != null
                && (size.equalsIgnoreCase("S")
                || size.equalsIgnoreCase("M")
                || size.equalsIgnoreCase("L"));
    }

    /**
     * Computes the final price based on size.
     *
     * @return the price of this beverage in pesos
     */
    @Override
    public double computePrice() {
        // Large adds 25, Medium adds 15, Small uses the base price
        if (size.equals("L")) {
            return basePrice + LARGE_SURCHARGE;
        } else if (size.equals("M")) {
            return basePrice + MEDIUM_SURCHARGE;
        } else {
            return basePrice;
        }
    }

    /**
     * Describes this beverage including its size.
     *
     * @return a text such as "Iced Latte [B-01] Size: L"
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " Size: " + size;
    }

    /**
     * Gets the size of this beverage.
     *
     * @return "S", "M", or "L"
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of this beverage. Only S, M, or L are accepted;
     * any other value keeps the old size.
     *
     * @param size the new size
     */
    public void setSize(String size) {
        // Accept only valid sizes; otherwise keep the old value
        if (isValidSize(size)) {
            this.size = size.toUpperCase();
        } else {
            System.out.println("Error: size must be S, M, or L.");
        }
    }
}
