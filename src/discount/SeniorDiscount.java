package discount;

/** Nouvelle stratégie ajoutée par extension : aucune classe existante n'est modifiée. */
public class SeniorDiscount implements DiscountStrategy {
    @Override
    public double apply(double amount) { return amount * 0.85; }
}