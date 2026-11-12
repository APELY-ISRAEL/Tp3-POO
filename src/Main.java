import invoice.*;
import discount.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Facture ---");
        Invoice invoice = new Invoice();
        invoice.addItem(10.0);
        invoice.addItem(20.0);
        invoice.addItem(5.5);

        InvoiceCalculator calculator = new InvoiceCalculator();
        new InvoicePrinter(calculator).print(invoice);
        new InvoiceRepository().save(invoice);

        System.out.println("--- Remises ---");
        DiscountService service = new DiscountService();
        System.out.println("STANDARD : " + service.applyDiscount("STANDARD", 100));
        System.out.println("VIP : " + service.applyDiscount("VIP", 100));
        System.out.println("STUDENT : " + service.applyDiscount("STUDENT", 100));
        System.out.println("INCONNU : " + service.applyDiscount("INCONNU", 100));
    }
}