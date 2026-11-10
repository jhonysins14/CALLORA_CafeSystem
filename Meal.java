/**
 * Represents a rice meal sold at the campus cafe.
 * A meal can be upsized and can have extra sides.
 *
 * @author Callora
 * @version 1.0
 */
public class Meal extends MenuItem {

    /** Extra charge in pesos for an upsized meal. */
    private static final double UPSIZE_FEE = 20.0;

    /** Charge in pesos for each extra side. */
    private static final double EXTRA_SIDE_PRICE = 12.0;

    /** True if the meal is upsized. */
    private boolean upsized;

    /** Number of extra sides ordered (never negative). */
    private int extraSides;

    /**
     * Creates a regular meal with no extra sides and placeholder name, price, and code.
     */
    public Meal() {
        super();
        this.upsized = false;
        this.extraSides = 0;
    }

    /**
     * Creates a meal with the given details.
     * A negative number of sides is rejected and replaced by 0.
     *
     * @param name       the display name
     * @param basePrice  the price in pesos of a regular meal
     * @param itemCode   the unique menu code
     * @param upsized    true if the meal is upsized
     * @param extraSides the number of extra sides
     */
    public Meal(String name, double basePrice, String itemCode, boolean upsized, int extraSides) {
        super(name, basePrice, itemCode);
        this.upsized = upsized;
        // Extra sides cannot be negative
        if (extraSides < 0) {
            System.out.println("Error: extra sides cannot be negative.");
            this.extraSides = 0;
        } else {
            this.extraSides = extraSides;
        }
    }

    /**
     * Computes the final price: base price, plus 20 if upsized,
     * plus 12 for each extra side.
     *
     * @return the price of this meal in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Upsizing costs 20 pesos more
        if (upsized) {
            price += UPSIZE_FEE;
        }
        price += EXTRA_SIDE_PRICE * extraSides;
        return price;
    }

    /**
     * Describes this meal including upsize status and number of extra sides.
     *
     * @return a text such as "Chicken Adobo [M-01] Upsized, 1 side"
     */
    @Override
    public String getDescription() {
        String sizeText;
        // Show whether the meal is upsized or regular
        if (upsized) {
            sizeText = "Upsized";
        } else {
            sizeText = "Regular";
        }

        String sideText;
        // Use the singular word for exactly one side
        if (extraSides == 1) {
            sideText = "1 side";
        } else {
            sideText = extraSides + " sides";
        }
        return super.getDescription() + " " + sizeText + ", " + sideText;
    }

    /**
     * Tells whether this meal is upsized.
     *
     * @return true if upsized
     */
    public boolean isUpsized() {
        return upsized;
    }

    /**
     * Sets whether this meal is upsized.
     *
     * @param upsized true to upsize the meal
     */
    public void setUpsized(boolean upsized) {
        this.upsized = upsized;
    }

    /**
     * Gets the number of extra sides.
     *
     * @return the number of extra sides
     */
    public int getExtraSides() {
        return extraSides;
    }

    /**
     * Sets the number of extra sides. Negative values are rejected
     * and the old value is kept.
     *
     * @param extraSides the new number of extra sides
     */
    public void setExtraSides(int extraSides) {
        // A negative count is invalid, so keep the old value
        if (extraSides < 0) {
            System.out.println("Error: extra sides cannot be negative.");
        } else {
            this.extraSides = extraSides;
        }
    }
}
