/**
 * Represents a baked item sold at the campus cafe.
 * A pastry can be warmed for an extra fee.
 *
 * @author Callora
 * @version 1.0
 */
public class Pastry extends MenuItem {

    /** Extra charge in pesos for warming the pastry. */
    private static final double WARMING_FEE = 10.0;

    /** True if the customer wants the pastry warmed. */
    private boolean warmed;

    /**
     * Creates an unwarmed pastry with placeholder name, price, and code.
     */
    public Pastry() {
        super();
        this.warmed = false;
    }

    /**
     * Creates a pastry with the given details.
     *
     * @param name      the display name
     * @param basePrice the price in pesos before the warming fee
     * @param itemCode  the unique menu code
     * @param warmed    true if the pastry is served warm
     */
    public Pastry(String name, double basePrice, String itemCode, boolean warmed) {
        super(name, basePrice, itemCode);
        this.warmed = warmed;
    }

    /**
     * Computes the final price, adding the warming fee when warmed.
     *
     * @return the price of this pastry in pesos
     */
    @Override
    public double computePrice() {
        double price = basePrice;
        // Warmed pastries cost 10 pesos more
        if (warmed) {
            price += WARMING_FEE;
        }
        return price;
    }

    /**
     * Describes this pastry, adding "(Warmed)" when it is warmed.
     *
     * @return a text such as "Ensaymada [P-01] (Warmed)"
     */
    @Override
    public String getDescription() {
        // Mention the warming only when it applies
        if (warmed) {
            return super.getDescription() + " (Warmed)";
        } else {
            return super.getDescription();
        }
    }

    /**
     * Tells whether this pastry is warmed.
     *
     * @return true if warmed
     */
    public boolean isWarmed() {
        return warmed;
    }

    /**
     * Sets whether this pastry is warmed.
     *
     * @param warmed true to serve the pastry warm
     */
    public void setWarmed(boolean warmed) {
        this.warmed = warmed;
    }
}
