package presentation.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import business.BusinessConstants;
import business.SessionCache;
import business.customersubsystem.CustomerSubsystemFacade;
import business.exceptions.BackendException;
import business.exceptions.BusinessException;
import business.exceptions.ExceptionMessage;
import business.exceptions.RuleException;
import business.externalinterfaces.CartItem;
import business.externalinterfaces.CustomerSubsystem;
import business.externalinterfaces.Product;
import business.externalinterfaces.ProductSubsystem;
import business.externalinterfaces.ShoppingCartSubsystem;
import business.productsubsystem.ProductSubsystemFacade;
import business.shoppingcartsubsystem.ShoppingCartSubsystemFacade;
import business.usecasecontrol.BrowseAndSelectController;
import presentation.data.BrowseSelectData;
import presentation.data.CartItemPres;
import presentation.data.CatalogPres;
import presentation.data.DataUtil;
import presentation.data.ManageProductsData;
import presentation.data.ProductPres;
import presentation.util.Util;

@Controller
public class BrowseSelectUIControl {
	static ExceptionMessage message = new ExceptionMessage("");
	
	@RequestMapping(value="/")
	public String viewCatalogsHandler(ModelMap modelMap) {
		System.out.println("in catlog");
		System.out.println("Data submitted");
		List<CatalogPres> catalogs = null;
		try {
			catalogs = BrowseSelectData.INSTANCE.getCatalogList();
		} catch (BackendException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("catalogs", catalogs);
		return "catalog";
	}

	@RequestMapping(value = "/viewProductList/catalogId/{id}/catalogName/{name}", method = RequestMethod.GET)
	public String viewProductListHandler(@PathVariable int id, @PathVariable String name, ModelMap modelMap) {
		//System.out.println(modelMap);
		System.out.println("id: " + id + ", name: " + name);
		List<ProductPres> products = null;
		try {
			products = BrowseSelectData.INSTANCE
					.getProductList(ManageProductsData.INSTANCE.createCatalogPres(id, name));
		} catch (BackendException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("products", products);
		modelMap.addAttribute("catalogName", name);
		return "product";
	}

	@RequestMapping(value = "/viewProductDetail")
	public String viewProductDetailHandler(@RequestParam(value = "id", required = false) int id, ModelMap modelMap) {
		
		System.out.println("viewProductsHandler2 - id: " + id);
		
		CustomerSubsystem css = (CustomerSubsystem)SessionCache.getInstance().get(BusinessConstants.CUSTOMER);
		
		ProductPres prodPres = null;
		try {
			prodPres = ManageProductsData.INSTANCE.createProductPresByName(id);
		} catch (BackendException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("product", prodPres);
		modelMap.addAttribute("exp", message);
		return "productdetail";
	}
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCartHandler(@RequestParam Map<String, String> allRequestParams, ModelMap modelMap) {
		System.out.println("addToCartHandler POST.....");
		String name = allRequestParams.get("prodName");
		double unitPrice = Double.valueOf(allRequestParams.get("unitPrice"));
		int quantity = Integer.valueOf(allRequestParams.get("quantity"));
		
		Product p = null;
		
		ProductSubsystem pess = new ProductSubsystemFacade();
		try {
			p = pess.getProductFromName(name);
			BrowseAndSelectController.INSTANCE.runQuantityRules(p, String.valueOf(quantity));
			
			CartItemPres cartPres = BrowseSelectData.INSTANCE.cartItemPresFromData(name, unitPrice, quantity);
			BrowseSelectData.INSTANCE.addToCart2(cartPres);
			BrowseSelectData.INSTANCE.updateCartData();
			List<CartItemPres> cartItems = BrowseSelectData.INSTANCE.getCartData2();
			modelMap.addAttribute("cartItems", cartItems);
			message.setMessage("");
			return "cart";
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (RuleException e) {
			message = new ExceptionMessage(e.getMessage());
			e.printStackTrace();
			return "redirect:/viewProductDetail?id="+p.getProductId();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String callBackToCartHandler(ModelMap modelMap) {
		System.out.println("addToCartHandler GET.....");
		List<CartItemPres> cartItems = BrowseSelectData.INSTANCE.getCartData2();
		modelMap.addAttribute("cartItems", cartItems);
		return "cart";
	}
	
	@RequestMapping(value = "/savecart")
	public String addToCartHandler(ModelMap modelMap) {
		System.out.println("save cart.....");
		
		if (!SessionCache.getInstance().containsKey(BusinessConstants.CUSTOMER))
			return "redirect:/login/callback/addToCart";
			
		try {
			ShoppingCartSubsystemFacade.INSTANCE.saveCart();
		} catch (BackendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/addToCart";
	}
	
	@RequestMapping(value = "/retrievesavedcart")
	public String retrieveSavedCardtHandler(ModelMap modelMap) throws BackendException {
		System.out.println("retrieved save cart.....");
		
		if (!SessionCache.getInstance().containsKey(BusinessConstants.CUSTOMER))
			return "redirect:/login/callback/retrievesavedcart";
			
		List<CartItemPres> cartItems = BrowseSelectData.INSTANCE.getSavedCart();
		modelMap.addAttribute("cartItems", cartItems);
		
		return "redirect:/addToCart";
	}

}
