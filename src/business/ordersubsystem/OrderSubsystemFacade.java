package business.ordersubsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import middleware.exceptions.DatabaseException;
import business.BusinessConstants;
import business.SessionCache;
import business.exceptions.BackendException;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.CustomerProfile;
import business.externalinterfaces.CustomerSubsystem;
import business.externalinterfaces.Order;
import business.externalinterfaces.OrderItem;
import business.externalinterfaces.OrderSubsystem;
import business.externalinterfaces.Product;
import business.externalinterfaces.ShoppingCart;
import business.productsubsystem.ProductSubsystemFacade;
import jdk.net.NetworkPermission;
import business.productsubsystem.ProductImpl;
import business.shoppingcartsubsystem.CartItemImpl;
import business.usecasecontrol.ManageProductsController;
import business.usecasecontrol.ViewOrdersController;

public class OrderSubsystemFacade implements OrderSubsystem {
	private static final Logger LOG = Logger.getLogger(OrderSubsystemFacade.class.getPackage().getName());
	CustomerProfile custProfile;

	public OrderSubsystemFacade(CustomerProfile custProfile) {
		this.custProfile = custProfile;
	}

	public void submitOrder(ShoppingCart cart) throws BackendException {
		System.out.println("in ordersubsystemFacade");
		DbClassOrder dbClassOrder = new DbClassOrder();
		int orderId = 1;
		String orderDate = LocalDate.now().toString();
		double totalPrice = presentation.util.Util.computeTotal(cart.getCartItems());
		
		Order ord = createOrder(orderId, orderDate, String.valueOf(totalPrice));
		List<OrderItem> items = new ArrayList<>();
		for (int i = 0; i < cart.getCartItems().size(); i++) {
			CartItem cartitem = cart.getCartItems().get(i);
			OrderItem item = createOrderItem(cartitem.getProductid(), orderId, cartitem.getQuantity(), cartitem.getTotalprice());
			items.add(item);
		}
		ord.setOrderItems(items);
		
		ord.setShipAddress(business.util.DataUtil.readCustFromCache().getDefaultShippingAddress());
		ord.setBillAddress(business.util.DataUtil.readCustFromCache().getDefaultBillingAddress());
		ord.setPaymentInfo(business.util.DataUtil.readCustFromCache().getDefaultPaymentInfo());
	
		try {
			dbClassOrder.submitOrder(this.custProfile, ord);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used whenever an order item needs to be created from outside the order
	 * subsystem
	 */

    public List<Order> getOrderHistory() throws BackendException {
    	//implement
    	ProductSubsystemFacade pss = new ProductSubsystemFacade();
    	
    	List<Order> orderList=new ArrayList<>();
    	try {
    		List<Integer> idOfOrders=getAllOrderIds();
        	for (Integer id:idOfOrders) {
        		// get each order data
    			Order order=getOrderData(id);
    
        		System.out.println(order.getOrderId());
    			
    			// get each orderitem of each order
    			List<OrderItem> listOrderItem = getOrderItems(id);
    			for (OrderItem orderItem : listOrderItem) {
    				Product prod = pss.getProductFromId(orderItem.getProductId());
					orderItem.setProductName(prod.getProductName());
				}
    			order.setOrderItems(listOrderItem);
    			orderList.add(order);
    		}
    	} catch(DatabaseException e) {
    		throw new BackendException("database exception view Order History");
    	}
    	return orderList;
    	
    }
    
    ///////////// Methods internal to the Order Subsystem -- NOT public
    List<Integer> getAllOrderIds() throws DatabaseException {
        DbClassOrder dbClass = new DbClassOrder();
        return dbClass.getAllOrderIds(custProfile);
        
    }
    
    /** Part of getOrderHistory */
    List<OrderItem> getOrderItems(Integer orderId) throws DatabaseException {
        DbClassOrder dbClass = new DbClassOrder();
        return dbClass.getOrderItems(orderId);
    }
    
    /** Uses cust id to locate top-level order data for customer -- part of getOrderHistory */
    OrderImpl getOrderData(Integer custId) throws DatabaseException {
    	DbClassOrder dbClass = new DbClassOrder();
    	return dbClass.getOrderData(custId);
    }

	public static OrderItem createOrderItem(Integer prodId, Integer orderId, String quantityReq, String totalPrice) {
		OrderItem orderItem = null;
		try {
			Product product = (ProductImpl) ManageProductsController.INSTANCE.getProductById(prodId);
			String name = product.getProductName();
			int quantity = Integer.parseInt(quantityReq);
			Double price = Double.parseDouble(totalPrice);
			orderItem = new OrderItemImpl(name, quantity, price);
			orderItem.setOrderId(orderId);

		} catch (BackendException e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	/** to create an Order object from outside the subsystem */
	public static Order createOrder(Integer orderId, String orderDate, String totalPrice) {
		OrderImpl ord = new OrderImpl();
		ord.setOrderId(orderId);
		ord.setDate(LocalDate.now());
		
		return ord;
	}
}
