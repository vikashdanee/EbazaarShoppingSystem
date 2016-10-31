package business.usecasecontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import business.BusinessConstants;
import business.SessionCache;
import business.customersubsystem.CustomerSubsystemFacade;
import business.exceptions.BackendException;
import business.exceptions.BusinessException;
import business.exceptions.RuleException;
import business.externalinterfaces.Address;
import business.externalinterfaces.CreditCard;
import business.externalinterfaces.CustomerProfile;
import business.externalinterfaces.CustomerSubsystem;
import business.externalinterfaces.OrderSubsystem;
import business.externalinterfaces.ShoppingCart;
import business.externalinterfaces.ShoppingCartSubsystem;
import business.ordersubsystem.OrderSubsystemFacade;
import business.util.DataUtil;
import presentation.util.Util;

public enum CheckoutController  {
	INSTANCE;
	
	private static final Logger LOG = Logger.getLogger(CheckoutController.class
			.getPackage().getName());
	
	
	public void runShoppingCartRules() throws RuleException, BusinessException {
		//implement
		
	}
	public List<Address> getShippingAddresses(CustomerProfile custProf) throws BackendException {
		CustomerSubsystem css = DataUtil.readCustFromCache();
		return css.getAllAddresses();
	}
	
	public Address getDefaultShippingAddress(CustomerProfile custProf) throws BackendException {
		CustomerSubsystem css = DataUtil.readCustFromCache();
		return css.getDefaultShippingAddress();
	}
	
	public Address getDefaultBillingAddress(CustomerProfile custProf) throws BackendException {
		CustomerSubsystem css = DataUtil.readCustFromCache();
		return css.getDefaultBillingAddress();
	}
	
	public void runPaymentRules(Address addr, CreditCard cc) throws RuleException, BusinessException {
		//implement
	}
	
	public Address runAddressRules(Address addr) throws RuleException, BusinessException {
		CustomerSubsystem cust = 
			(CustomerSubsystem)SessionCache.getInstance().get(BusinessConstants.CUSTOMER);
		return cust.runAddressRules(addr);
	}
	
	/** Asks the ShoppingCart Subsystem to run final order rules */
	public void runFinalOrderRules(ShoppingCartSubsystem scss) throws RuleException, BusinessException {
		//implement
	}
	
	/** Asks Customer Subsystem to check credit card against 
	 *  Credit Verification System 
	 */
	public void verifyCreditCard() throws BusinessException {
		//implement
	}
	
	public void saveNewAddress(Address addr) throws BackendException {
		CustomerSubsystem cust = 
			(CustomerSubsystem)SessionCache.getInstance().get(BusinessConstants.CUSTOMER);			
		cust.saveNewAddress(addr);
	}
	
	/** Asks Customer Subsystem to submit final order */
	public void submitFinalOrder() throws BackendException {
		System.out.println("in checkoutController ");
		CustomerSubsystem cust = (CustomerSubsystem)SessionCache.getInstance().get(BusinessConstants.CUSTOMER);
		ShoppingCart cart = cust.getShoppingCartSubsystem().getLiveCart();

		cust.getOrderSubsystem().submitOrder(cart);
		
		
	}


}
