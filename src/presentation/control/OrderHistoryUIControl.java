package presentation.control;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.exceptions.BackendException;
import business.util.DataUtil;
import presentation.data.OrderItemPres;
import presentation.data.OrderPres;
import presentation.data.ViewOrdersData;

@Controller
public class OrderHistoryUIControl {

	@RequestMapping(value = "/orderhistory", method = RequestMethod.GET)
	public String viewOrderHistoryHandler(ModelMap modelMap) {


		List<OrderPres> orders = null;
		try {			
			orders = ViewOrdersData.INSTANCE.getOrderList();
			System.out.println(orders);
		} catch (BackendException e) {
			e.printStackTrace();
			modelMap.addAttribute("message", "Unfortunately error occured");
			return "redirect:/";
			
		}

		modelMap.addAttribute("orderlist", orders);

		return "orderhistory";
	}

	@RequestMapping(value = "/orderdetail/{orderid}", method = RequestMethod.GET)
	public String viewOrderHistoryDetailHandler(@PathVariable String orderid, ModelMap modelMap) {


		List<OrderItemPres> orderItems = null;
		try {

			orderItems = ViewOrdersData.INSTANCE.getOrderListDetail(Integer.parseInt(orderid));
		} catch (BackendException e) {
			e.printStackTrace();
			modelMap.addAttribute("message", "Unfortunately error occured");
			return "redirect:/";
		}

		modelMap.addAttribute("orderItemlist", orderItems);

		return "orderhistorydetail";
	}

}



