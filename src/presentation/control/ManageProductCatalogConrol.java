package presentation.control;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import business.BusinessConstants;
import business.SessionCache;
import business.exceptions.BackendException;
import business.externalinterfaces.Catalog;
import business.externalinterfaces.CustomerSubsystem;
import business.externalinterfaces.Product;
import business.externalinterfaces.ProductSubsystem;
import business.productsubsystem.CatalogTypesImpl;
import business.productsubsystem.ProductSubsystemFacade;
import business.usecasecontrol.ManageProductsController;
import presentation.data.BrowseSelectData;
import presentation.data.CartItemPres;
import presentation.data.CatalogPres;
import presentation.data.ManageProductsData;
import presentation.data.ProductPres;

@Controller
public class ManageProductCatalogConrol {
	
	@RequestMapping(value="/cataloglist")
	public String viewCatalogsHandler(ModelMap modelMap) {
		//System.out.println("in catlog");
		System.out.println("Data submitted");
		List<CatalogPres> catalogs = null;
		try {
			catalogs = BrowseSelectData.INSTANCE.getCatalogList();
		} catch (BackendException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("catalogs", catalogs);
		return "cataloglist";
	}
	
	@RequestMapping(value = "/addnewcatalog", method = RequestMethod.GET)
	public String newcatalogHandler(ModelMap modelMap) {
		System.out.println("newcatalogHandler.....");		
		return "newcatalog";
	}

	@RequestMapping(value = "/addnewproduct", method = RequestMethod.GET)
	public String newproductHandler(@RequestParam("id") String catid, @RequestParam("value") String catName, ModelMap modelMap) {
		System.out.println("newproductHandler.....");
		modelMap.addAttribute("catId", catid);
		modelMap.addAttribute("catName", catName);
		return "newproduct" ;
	}
	
	
	@RequestMapping(value = "/savecatalog", method = RequestMethod.POST)
	public String savecatalogHandler(@RequestParam Map<String,String> params, ModelMap modelMap) {
		System.out.println("save new catalog...");
		try {
			ProductSubsystem pss = new ProductSubsystemFacade();
			pss.saveNewCatalog(params.get("catName"));
		} catch (BackendException e) {
			e.printStackTrace();
		}
		///modelMap.addAttribute("products", products);
		//modelMap.addAttribute("catalogName", name);
		return "redirect:/cataloglist";
	}
	
	@RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
	public String saveproductHandler(@RequestParam Map<String,String> params, ModelMap modelMap) throws BackendException {
		System.out.println("save new product...");
		String catid = params.get("catId");	
		String catname = params.get("catName");		
		String name = params.get("pName");
		String date = params.get("mDate");
		String quantity = params.get("num");
		String unitprice = params.get("price");
		String description =params.get("des");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate formatedDate = LocalDate.parse(date, formatter);
		
		Catalog catalog = ProductSubsystemFacade.createCatalog(Integer.parseInt(catid), catname);		
		Product product = ProductSubsystemFacade.createProduct(catalog , 0, name, Integer.parseInt(quantity),	
				Integer.parseInt(unitprice), formatedDate, description) ;	
		ManageProductsController.INSTANCE.saveNewProduct(product);
		return "redirect:/productlist";
	}
	
	@RequestMapping(value="/productlist")
	public String viewProductListHandler(@RequestParam(value = "catId", required = false) String id,
											@RequestParam(value = "name", required = false) String name, 
											ModelMap modelMap) {
		//System.out.println("in catlog");
		System.out.println("Data submitted");
		List<CatalogPres> catalogs = null;
		List<ProductPres> products = null;
		
		try {
			catalogs = BrowseSelectData.INSTANCE.getCatalogList();
			if(id == null){
				products = BrowseSelectData.INSTANCE.getProductList(catalogs.get(0));
			}
			else{
				Catalog cat = ProductSubsystemFacade.createCatalog(Integer.parseInt(id), "");
				CatalogPres catPress = new CatalogPres();
				catPress.setCatalog(cat);
				products = BrowseSelectData.INSTANCE.getProductList(catPress);
				
			}
		} catch (BackendException e) {
			e.printStackTrace();
		}
		
		modelMap.addAttribute("products", products);
		modelMap.addAttribute("catalogs", catalogs);
		modelMap.addAttribute("selectedId", id);
		modelMap.addAttribute("name", name);
		
		return "manageproduct";
	}
}

