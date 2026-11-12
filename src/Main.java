import invoice.*;
import discount.*;
import shape.*;

public class Main {
    static void printArea(String label, Shape shape) {
        System.out.println(label + " : " + shape.area());
    }

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

        System.out.println("--- Formes ---");
        printArea("Rectangle 5x4", new Rectangle(5, 4));
        printArea("Carré de côté 5", new Square(5));
        try {
            new Rectangle(0, 4);
        } catch (IllegalArgumentException e) {
            System.out.println("Dimension refusée : " + e.getMessage());
        }
    }
}