package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
     List<Order> orders;

    public OrderHistory(List<Order> orders) {
        this.orders=new ArrayList<Order>(orders);
    }
    public String formatAllOrders() {
        if (orders.isEmpty()) {
            return "No past orders found";
        }

        StringBuilder sb = new StringBuilder("Customer past orders:\n");
        int orderNumber = 1;
        List<Order> sortedOrders = new ArrayList<>(orders);
        sortedOrders.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        for (Order order : sortedOrders) {
            sb.append(order.formatForDisplay(orderNumber++)).append("\n");
        }
        return sb.toString().trim();
    }
}
