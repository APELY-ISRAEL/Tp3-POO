package invoice;// src/invoice.InvoiceTest.java
import java.util.Locale;

public class InvoiceTest {
    public static void main(String[] args) {
        Locale.setDefault(Locale.FRANCE);
        String nl = System.lineSeparator();

        Invoice invoice = new Invoice("F-1");
        invoice.addLine(new InvoiceLine("Kit Tomates", 2, 34.90));
        invoice.addLine(new InvoiceLine("Terreau", 1, 8.50));

        Check.eq("total", 78.3, invoice.calculateTotal());
        Check.eq("affichage",
                "=== Facture n° F-1 ===" + nl
                        + "Kit Tomates x2 : 69,80 €" + nl
                        + "Terreau x1 : 8,50 €" + nl
                        + "TOTAL : 78,30 €" + nl,
                Check.capture(invoice::print));
        Check.eq("enregistrement",
                "Facture F-1 enregistrée (2 lignes)." + nl,
                Check.capture(invoice::save));
        Check.eq("facture vide", 0.0, new Invoice("F-0").calculateTotal());
        Check.summary("invoice.InvoiceTest (SRP)");
    }
}