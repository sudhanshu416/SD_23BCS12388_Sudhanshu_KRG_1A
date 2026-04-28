import java.util.HashMap;
import java.util.Map;

enum OrderStatus {
    SUCCESSFUL, FAILED;
}
class Orders{
    Integer orderId;
    String orderName;
    Double orderPrice;
}
class AvailabilityCheck{
    private Map<String, Integer> Inventory = new HashMap<>();
    
    public boolean checkAvailability(Orders order) {
        return Inventory.containsKey(order.orderName) && Inventory.get(order.orderName) > 0;
    }
}
class PaymentProcessing{
    public OrderStatus processPayment(Orders order) {
        // Payment processing
        return OrderStatus.SUCCESSFUL;
    }
}
class EmailService{
    public void sendConfirmationEmail(Orders order) {
        // Send confirmation email
    }
}
class OrderFacade{
    private Orders order;

    public OrderFacade(Orders order) {
        this.order = order;
    }

    public void placeOrder() {
        if (new AvailabilityCheck().checkAvailability(order)) {
            if (new PaymentProcessing().processPayment(order) == OrderStatus.SUCCESSFUL) {
                new EmailService().sendConfirmationEmail(order);
                System.out.println("Order placed successfully!");
            } else {
                System.out.println("Payment failed. Order could not be placed.");
            }
        } else {
            System.out.println("Item is out of stock. Order could not be placed.");
        }
    }
}
public class DesignPatternOrders {
    public static void main(String[] args) {
        Orders order1 = new Orders();
        order1.orderId = 1;
        order1.orderName = "Laptop";
        order1.orderPrice = 999.99;

        OrderFacade orderFacade = new OrderFacade(order1);
        orderFacade.placeOrder();
    }
}