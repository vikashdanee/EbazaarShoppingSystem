package business.ordersubsystem;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import business.externalinterfaces.Address;
import business.externalinterfaces.CreditCard;
import business.externalinterfaces.Order;
import business.externalinterfaces.OrderItem;

public class OrderImpl implements Order {
	private List<OrderItem> orderItems;
	private int orderId;
	private LocalDate date;
	private Address shipAddress;
	private Address billAddress;
	private CreditCard cc;
	
	
	public OrderImpl() {
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public double getTotalPrice() {
		if(orderItems == null) {
			return 0.0;
		} else {
			 DoubleSummaryStatistics summary 
			    = orderItems.stream().collect(
				    Collectors.summarizingDouble(
					   (OrderItem item) -> item.getUnitPrice() * item.getQuantity()));
			 return summary.getSum();
		}
	}
	
	public LocalDate getOrderDate() {
		//note that LocalDates are immutable
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	

	public Address getShipAddress() {
		return shipAddress;
	}

	public Address getBillAddress() {
		return billAddress;
	}

	public CreditCard getPaymentInfo() {
		return cc;
	}

	public void setShipAddress(Address add) {
		shipAddress = add;
	}

	public void setBillAddress(Address add) {
		billAddress = add;
		
	}

	public void setPaymentInfo(CreditCard cc) {
		this.cc = cc;
	}
	
}
