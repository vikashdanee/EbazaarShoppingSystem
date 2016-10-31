package presentation.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import business.BusinessConstants;
import business.SessionCache;
import business.customersubsystem.AddressImpl;
import business.customersubsystem.CreditCardImpl;
import business.customersubsystem.CustomerSubsystemFacade;

import business.exceptions.BackendException;
import business.externalinterfaces.Address;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.CreditCard;
import business.externalinterfaces.CustomerProfile;

import business.externalinterfaces.CustomerSubsystem;
import business.usecasecontrol.CheckoutController;
import business.util.DataUtil;
import presentation.data.AddressPres;
import presentation.data.BrowseSelectData;
import presentation.data.CartItemPres;
import presentation.data.CheckoutData;
import presentation.data.CustomerPres;
import presentation.data.DefaultData;
import presentation.gui.GuiConstants;
import presentation.util.Util;

@SuppressWarnings("unused")
@Controller
public class CheckoutUIControl {
	AddressPres uiShipAddress = new AddressPres();
	AddressPres uiBillAddress = new AddressPres();

	@RequestMapping(value = "/shippingbilling", method = RequestMethod.GET)
	public String selectshipaddresss(ModelMap modelMap) {
		if (!SessionCache.getInstance().containsKey(BusinessConstants.CUSTOMER)) {
			return "redirect:/login/callback/shippingbilling";
		} else {

			System.out.println("proceedFromCartToShipBill");
			CheckoutData data = CheckoutData.INSTANCE; // CustomerProfile
														// custProfile =
			data.getCustomerProfile();
			Address defaultShipAddress = data.getDefaultShippingData();

			Address defaultBillAddress = data.getDefaultBillingData();
			uiBillAddress.setAddress(defaultBillAddress);
			uiShipAddress.setAddress(defaultShipAddress);
			modelMap.addAttribute("shippingAddress", uiShipAddress);
			modelMap.addAttribute("billingAddress", uiBillAddress);
			return "shippingbilling";
		}
	}

	@RequestMapping(value = "/shippingbilling/{id}/{title}", method = RequestMethod.GET)
	public String proceedFromCartToShipBill(@PathVariable("id") String id, @PathVariable("title") String title,
			ModelMap modelMap) {
		System.out.println("kkkk" + id);

		if (!SessionCache.getInstance().containsKey(BusinessConstants.CUSTOMER)) {
			return "redirect:/login/callback/shippingbilling";
		} else {
			if (id != null) {

				System.out.println("proceedFromCartToShipBill selected address");
				try {
					System.out.println("aaa");
					// List<Address> address =
					// DataUtil.readCustFromCache().getAllAddresses();
					// System.out.println(address);
					Address add = DataUtil.readCustFromCache().getAllAddresses().stream()
							.filter(a -> (a.getId()) == Integer.parseInt(id)).findFirst().get();

					AddressPres addresspress = Util.addressToAddressPres(add);

					if (title.equals("bill")) {
						uiBillAddress.setAddress(add);
					} else {
						uiShipAddress.setAddress(add);
					}

					modelMap.put("shippingAddress", uiShipAddress);
					modelMap.put("billingAddress", uiBillAddress);
					System.out.println("bbbb");
					System.out.println(addresspress);
				} catch (BackendException e) {
					e.printStackTrace();
				}

				return "shippingbilling";

			} else {

				System.out.println("proceedFromCartToShipBill");
				CheckoutData data = CheckoutData.INSTANCE;
				// CustomerProfile custProfile = data.getCustomerProfile();
				Address defaultShipAddress = data.getDefaultShippingData();

				Address defaultBillAddress = data.getDefaultBillingData();
				uiBillAddress.setAddress(defaultBillAddress);
				uiShipAddress.setAddress(defaultShipAddress);
				modelMap.addAttribute("shippingAddress", uiShipAddress);
				modelMap.addAttribute("billingAddress", uiBillAddress);

				return "shippingbilling";
			}
		}

	}

	@RequestMapping(value = "/selectbilladdress", method = RequestMethod.GET)
	public String selectbillAddress(ModelMap modelMap) {
		CheckoutData data = CheckoutData.INSTANCE;
		try {
			List<Address> addressSelected = new ArrayList<>();
			System.out.println("here");
			List<CustomerPres> addressList = data.getCustomerBillAddresses();
			System.out.println("now");

			for (CustomerPres cp : addressList) {
				addressSelected.add(cp.getAddress());
				System.out.println(cp);
			}
			modelMap.addAttribute("addressList", addressSelected);
		} catch (BackendException e) {
			e.printStackTrace();
		}
		// modelMap.addAttribute("addressList",DefaultData.ADDRESSES_ON_FILE);
		modelMap.addAttribute("title", "bill");
		return "selectaddress";
	}


	@RequestMapping(value = "/selectshipaddress", method = RequestMethod.GET)
	public String selectshipAddress(ModelMap modelMap) {
		CheckoutData data = CheckoutData.INSTANCE;
		try {
			List<Address> addressSelected = new ArrayList<>();
			System.out.println("here");
			List<CustomerPres> addressList = data.getCustomerShipAddresses();
			System.out.println("now");

			for (CustomerPres cp : addressList) {
				addressSelected.add(cp.getAddress());
				System.out.println(cp);
			}
			modelMap.addAttribute("addressList", addressSelected);

		} catch (BackendException e) {
			e.printStackTrace();
		}
		// modelMap.addAttribute("addressList",DefaultData.ADDRESSES_ON_FILE);
		modelMap.addAttribute("title", "ship");
		return "selectaddress";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String proceedToPayment(@RequestParam Map<String, String> allRequestParams, ModelMap modelMap) {
		System.out.println("submitAddress:: shipping address dilip" + allRequestParams.get("shippingStreet"));
		System.out.println("submitAddress:: billing address " + allRequestParams.get("billingStreet"));
		System.out.println("zip::shipping address" + allRequestParams.get("shippingZip"));

	
		CustomerSubsystem css = (CustomerSubsystem) SessionCache.getInstance().get(BusinessConstants.CUSTOMER);
		CreditCard cardInfo = css.getDefaultPaymentInfo();

		System.out.println("Customer ID : " + css.getCustomerProfile().getCustId());
		
		modelMap.addAttribute("cnumber", cardInfo.getCardNum());
		modelMap.addAttribute("ctype", cardInfo.getCardType());
		modelMap.addAttribute("cdate", cardInfo.getExpirationDate());
		modelMap.addAttribute("cname", cardInfo.getNameOnCard());
		
		Address addressShipping = new AddressImpl();
		Address addressBilling = new AddressImpl();
		
		addressShipping.setCity(allRequestParams.get("shippingCity"));
		addressShipping.setState(allRequestParams.get("shippingState"));
		addressShipping.setStreet(allRequestParams.get("shippingStreet"));
		addressShipping.setZip(allRequestParams.get("shippingZip"));
		try {
			CheckoutController.INSTANCE.saveNewAddress(addressShipping);
			System.out.println("shipping address from shipping billing page "+addressShipping.getState());
		} catch (BackendException e) {
			e.printStackTrace();
		}
		
		css.setDefaultShipAddress(addressShipping);
		
		addressBilling.setCity(allRequestParams.get("billingCity"));
		addressBilling.setState(allRequestParams.get("billingState"));
		addressBilling.setStreet(allRequestParams.get("billingStreet"));
		addressBilling.setZip(allRequestParams.get("billingZip"));
		try {
			CheckoutController.INSTANCE.saveNewAddress(addressBilling);
			System.out.println("billing address from shipping billing page "+addressShipping.getCity());

		} catch (BackendException e) {
			e.printStackTrace();
		}
		
		css.setDefaultBillAddress(addressBilling);
		
		return "payment";
	}

	@RequestMapping(value = "/terms", method = RequestMethod.POST)
	public String proceedToTermsAndCondition(ModelMap modelMap) {
		return "redirect:/terms";
	}

	@RequestMapping(value = "/finalorder")
	public String proceedToFinalOrder(ModelMap modelMap) {
		List<CartItemPres> finalOrder = BrowseSelectData.INSTANCE.getCartData2();
		modelMap.addAttribute("finalOrders", finalOrder);
		return "finalorder";
	}

	@RequestMapping(value = "terms", method = RequestMethod.GET)
	public String proceedToTermsAndConditionget(ModelMap modelMap) {
		modelMap.addAttribute("terms", GuiConstants.TERMS_MESSAGE);
		return "termsncondition";
	}

	@RequestMapping(value = "successfull", method = RequestMethod.GET)
	public String proceedToSuccessfullSubmission(ModelMap modelMap) {
		try {
			CheckoutController.INSTANCE.submitFinalOrder();
		} catch (BackendException e) {
			e.printStackTrace();
		}
		return "successfull";
	}

}
