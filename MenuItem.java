/**
 * Abstract parent of every item sold at the campus cafe.
 * It stores the data shared by all items and forces each subclass
 * to define its own pricing through {@link #computePrice()}.
 *
 * @author Callora
 * @version 1.0
 */
public abstract class MenuItem {

    /** Placeholder name used when no valid name is given. */
    private static final String DEFAULT_NAME = "Unnamed Item";

    /** Placeholder code used when no valid code is given. */
    private static final String DEFAULT_CODE = "N/A";

    /** Display name of the item. */
    protected String name;

    /** Price in pesos before any size, warming, or upsize charges. */
    protected double basePrice;

    /** Unique menu code such as "B-01". */
    private String itemCode;

    /**
     * Creates an item with placeholder values:
     * name "Unnamed Item", base price 0.0, and item code "N/A".
     */
    public MenuItem() {
        this.name = DEFAULT_NAME;
        this.basePrice = 0.0;
        this.itemCode = DEFAULT_CODE;
    }

    /**
     * Creates an item with the given details. Invalid values fall back
     * to the same placeholders used by the default constructor.
     *
     * @param name      the display name of the item
     * @param basePrice the base price in pesos (must not be negative)
     * @param itemCode  the unique menu code
     */
    public MenuItem(String name, double basePrice, String itemCode) {
        // A missing or blank name is replaced by the placeholder
        if (name == null || name.trim().isEmpty()) {
            this.name = DEFAULT_NAME;
        } else {
            this.name = name;
        }

        // A negative price is rejected and replaced by 0.0
        if (basePrice < 0) {
            System.out.println("Error: base price cannot be negative.");
            this.basePrice = 0.0;
        } else {
            this.basePrice = basePrice;
        }

        // A missing or blank code is replaced by the placeholder
        if (itemCode == null || itemCode.trim().isEmpty()) {
            this.itemCode = DEFAULT_CODE;
        } else {
            this.itemCode = itemCode;
        }
    }

    /**
     * Computes the final price of this item. Each subclass defines its own rule.
     *
     * @return the final price in pesos
     */
    public abstract double computePrice();

    /**
     * Describes this item using its name and item code.
     * Subclasses override this method to add their own details.
     *
     * @return a text such as "Iced Latte [B-01]"
     */
    public String getDescription() {
        return name + " [" + itemCode + "]";
    }

    /**
     * Gets the name of this item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this item. Blank names are rejected.
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
     * Gets the base price of this item.
     *
     * @return the base price in pesos
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Sets the base price of this item. Negative values are rejected
     * and the old price is kept.
     *
     * @param basePrice the new base price in pesos
     */
    public void setBasePrice(double basePrice) {
        // Prices below zero make no sense, so print an error and keep the old value
        if (basePrice < 0) {
            System.out.println("Error: base price cannot be negative.");
        } else {
            this.basePrice = basePrice;
        }
    }

    /**
     * Gets the item code of this item.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code of this item. Blank codes are rejected.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        // Only accept a code that has visible characters
        if (itemCode != null && !itemCode.trim().isEmpty()) {
            this.itemCode = itemCode;
        } else {
            System.out.println("Error: item code cannot be empty.");
        }
    }
}
