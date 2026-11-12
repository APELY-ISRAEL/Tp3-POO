package discount;

/** AVANT : il faut modifier cette méthode à chaque nouveau type de remise (violation OCP). */
public class DiscountService {
    public double applyDiscount(String type, double amount) {
        if ("STANDARD".equals(type)) return amount * 0.95;
        if ("VIP".equals(type)) return amount * 0.80;
        if ("STUDENT".equals(type)) return amount * 0.90;
        return amount;
    }
}