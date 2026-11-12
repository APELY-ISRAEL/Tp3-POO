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
        System.out.println("STANDARD : " + new DiscountService(new StandardDiscount()).applyDiscount(100));
        System.out.println("VIP : " + new DiscountService(new VipDiscount()).applyDiscount(100));
        System.out.println("STUDENT : " + new DiscountService(new StudentDiscount()).applyDiscount(100));
        System.out.println("SENIOR (nouvelle stratégie) : " + new DiscountService(new SeniorDiscount()).applyDiscount(100));
    }
}