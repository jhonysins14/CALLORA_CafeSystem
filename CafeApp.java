/**
 * Main class of the Campus Cafe Ordering System.
 * It creates customers, menu items, and orders, then prints two receipts.
 *
 * @author Callora
 * @version 1.0
 */
public class CafeApp {

    /**
     * Runs the demonstration program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Customers created with two different constructors
        Customer ana = new Customer("Ana Reyes", true);
        Customer ben = new Customer("Ben Cruz");

        // Items created with parameterized constructors
        Beverage icedLatte = new Beverage("Iced Latte", 95.0, "B-01", "L");
        Pastry ensaymada = new Pastry("Ensaymada", 45.0, "P-01", true);
        Meal chickenAdobo = new Meal("Chicken Adobo", 110.0, "M-01", true, 1);

        // An extra pastry created with the default constructor, then filled with setters
        Pastry cookie = new Pastry();
        cookie.setName("Cookie");
        cookie.setItemCode("P-02");
        cookie.setBasePrice(35.0);

        // Order #1001: three items fit, the fourth is refused
        Order order1 = new Order("1001", ana);
        order1.addItem(icedLatte);
        order1.addItem(ensaymada);
        order1.addItem(chickenAdobo);
        order1.addItem(cookie);
        order1.printReceipt();

        // Hot Chocolate: default constructor, then setters (the first price is invalid)
        Beverage hotChocolate = new Beverage();
        hotChocolate.setName("Hot Chocolate");
        hotChocolate.setItemCode("B-02");
        hotChocolate.setBasePrice(-5.0);
        hotChocolate.setBasePrice(80.0);
        hotChocolate.setSize("M");

        // Beef Tapa: default constructor, then setters
        Meal beefTapa = new Meal();
        beefTapa.setName("Beef Tapa");
        beefTapa.setItemCode("M-02");
        beefTapa.setBasePrice(130.0);
        beefTapa.setUpsized(false);
        beefTapa.setExtraSides(0);

        // Order #1002: a non-student customer with a 20 peso voucher
        Order order2 = new Order("1002", ben);
        order2.addItem(hotChocolate);
        order2.addItem(beefTapa);
        order2.printReceipt(20.0);
    }
}
