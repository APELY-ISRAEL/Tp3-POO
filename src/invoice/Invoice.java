package invoice;

import java.util.ArrayList;
import java.util.List;

/** Données d'une facture : la liste des montants de ses lignes. */
public class Invoice {
    private final List<Double> items = new ArrayList<>();

    public void addItem(double amount) { items.add(amount); }

    public List<Double> getItems() { return List.copyOf(items); }
}