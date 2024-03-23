package mws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mws.pojo.Order;
import mws.pojo.OrderItem;
import mws.pojo.Page;
import mws.pojo.Product;
import mws.pojo.RequestResult;
import mws.service.OrderService;

@CrossOrigin(origins = "*") //跨域
@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RequestResult<Order> result;
	
	Page page=new Page();
	
	/**
	 * API接口
	 */
	@RequestMapping(value = "/list.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> list(){
		List<Order> orders = this.orderService.getOrderListWhere(null);
		result.setCode(666);
		result.setMessage("成功查询到订单！");
		result.setSuccess(true);
		result.setData(orders);
		return result;
	}
	
	/**
	 * MVC方法
	 */
	@RequestMapping(value = "/list2.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String list2(Order order, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String select=request.getParameter("select");
		String value=request.getParameter("value");
		System.out.println("---------------select:"+select);
		
		List<Order> list=new ArrayList<Order>();
		if(select==null){
			select="0";
		}
		if(select.equals("0")){
			list = this.orderService.getOrderListWhere(null);
		}else if(select.equals("1")){
			Order order1=this.orderService.getOrderById(value);
			list.add(order1);
		}else if(select.equals("2")){
			int userId=Integer.parseInt(value);
			list = this.orderService.getOrderByUserId(userId);
		}
		
		System.out.println("-------------list:"+list);
		
		System.out.println(page.getCurrentPage());
	        page.setCurrentPage(1);
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		model.addAttribute("orders", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("order", order);
		return "orderTable";
	}
	
	/**
	 * 修改产品
	 */
	@RequestMapping(value = "/update.action", method = {RequestMethod.GET })
	public String jumpUpdate(String id,Model model) {
		System.out.println("id:"+id);
		Order order=this.orderService.getOrderById(id);
		List<OrderItem> orderItem=this.orderService.getOrderItemById(id);
		System.out.println("order:"+order);
		model.addAttribute("order", order);
		model.addAttribute("orderItem", orderItem);
		return "updateOrder";
	}
	
	@RequestMapping(value="/update.action",method={RequestMethod.POST})
	public String updateOrder(Order order,Model model){
		System.out.println("订单信息："+order);
		int state=this.orderService.updateOrder(order);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改订单信息成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改订单信息失败！！！");
			model.addAttribute("second", 2);
		}
		return "tip";
	}
	
	/**
	 * 删除产品
	 */
	@RequestMapping(value = "/delete.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteOrder(String id,Model model) {
		int state=this.orderService.deleteOrder(id);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除订单成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除订单失败！！！");
			model.addAttribute("second", 2);
		}
		
		return "tip";
	}
	
	@RequestMapping(value = "/upPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String upPage(Order order, Model model) {
		List<Order> list = this.orderService.getOrderListWhere(null);
		System.out.println(page.getCurrentPage());
		if (page.getCurrentPage() == 0){
	        page.setCurrentPage(1);
	    }else if(page.getCurrentPage()>1){
	    	page.setCurrentPage(page.getCurrentPage()-1);
	    }
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("orders", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("order", order);
			return "orderTable";
	}
	
	@RequestMapping(value = "/downPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String downPage(Order order, Model model) {
		List<Order> list = this.orderService.getOrderListWhere(null);
		System.out.println(page.getCurrentPage());
		if (page.getCurrentPage() == 0){
	        page.setCurrentPage(1);
	    }else if(page.getCurrentPage()<page.getTotalPage()){
	    	page.setCurrentPage(page.getCurrentPage()+1);
	    }
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("orders", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("order", order);
			return "orderTable";
	}
	
	@RequestMapping(value = "/homePage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String homePage(Order order, Model model) {
		List<Order> list = this.orderService.getOrderListWhere(null);
	        page.setCurrentPage(1);
			page.setPageSize(10);
			page.setStar(0);
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("orders", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("order", order);
			return "orderTable";
	}
	
	@RequestMapping(value = "/lastPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String lastPage(Order order, Model model) {
		List<Order> list = this.orderService.getOrderListWhere(null);
			page.setPageSize(10);
			page.setStar((page.getTotalPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			System.out.println("totalPage:"+page.getTotalPage());
			page.setCurrentPage(page.getTotalPage());
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("orders", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("order", order);
			return "orderTable";
	}
	
	@RequestMapping(value = "/jumpPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String jumpPage(Order order, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<Order> list = this.orderService.getOrderListWhere(null);
		System.out.println("pageNumber:"+request.getParameter("pageNumber"));
        int currentPage=1;
		
		if(request.getParameter("pageNumber")==null||request.getParameter("pageNumber")==""){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}else{
			currentPage=Integer.parseInt(request.getParameter("pageNumber"));
		}
		System.out.println("currentPage:"+currentPage);
			page.setCurrentPage(currentPage);
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("orders", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("order", order);
			return "orderTable";
	}

}
