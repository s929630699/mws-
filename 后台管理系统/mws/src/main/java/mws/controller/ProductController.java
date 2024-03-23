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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mws.pojo.Page;
import mws.pojo.Product;
import mws.service.ProductService;
import mws.pojo.RequestResult;


@CrossOrigin(origins = "*") //跨域
@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RequestResult<Product> result;
	
	Page page=new Page();
	
	
	/**
	 * API接口
	 */
	@RequestMapping(value = "/list.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Product> list(){
		List<Product> products = this.productService.getProductListWhere(null);
		result.setCode(666);
		result.setMessage("成功查询到产品！");
		result.setSuccess(true);
		result.setData(products);
		return result;
	}
	
	@RequestMapping(value = "/list3.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Product> list3(Product product) {
		List<Product> products = this.productService.getProductListWhere(product);
		return products;
	}
	
	/**
	 * MVC方法
	 */
	@RequestMapping(value = "/list2.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String list2(Product product, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String select=request.getParameter("select");
		String value=request.getParameter("value");
		System.out.println("---------------select:"+select);

		int categoryId=0;
		if(value==null){
			categoryId=0;
		}else{
			if (value.equals("面包")){
				categoryId=1;
			}else if(value.equals("蛋糕")){
				categoryId=2;
			}else if(value.equals("甜品")){
				categoryId=3;
			}else if(value.equals("饮料")){
				categoryId=4;
			}
		}
		
		
		List<Product> list=new ArrayList<Product>();
		if(select==null){
			select="0";
		}
		if(select.equals("0")){
			list= this.productService.getProductListWhere(null);
		}else if (select.equals("1")){
			list =this.productService.getProductByName(value);
		}else if (select.equals("2")){
			list =this.productService.getProductByCategoryId(categoryId);
		}
		System.out.println("list:"+list);
		
		
		System.out.println(page.getCurrentPage());
		int cPage;
		if (page.getCurrentPage()<=1){
			cPage=1;
			page.setCurrentPage(cPage);
		}else{
			page.setCurrentPage(page.getCurrentPage());
		}
	        
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	/**
	 * 添加产品
	 */
	@RequestMapping(value = "/insert.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String insertProduct(Product product,Model model) {
		System.out.println("产品信息："+product);
		int state=this.productService.insertProduct(product);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "添加产品成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "添加产品失败！！！");
			model.addAttribute("second", 2);
		}
		
		return "tip";
	}
	
	/**
	 * 修改产品
	 */
	@RequestMapping(value = "/update.action", method = {RequestMethod.GET })
	public String jumpUpdate(int id,Model model) {
		Product product=this.productService.getProductById(id);
		System.out.println("product:"+product);
		model.addAttribute("product", product);
		return "updateProduct";
	}
	
	@RequestMapping(value = "/update.action", method = {RequestMethod.POST })
	public String updateProduct(Product product,Model model) {
		System.out.println("产品信息："+product);
		int state=this.productService.updateProduct(product);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改产品成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改产品失败！！！");
			model.addAttribute("second", 2);
		}
		return "tip";
	}
	
	/**
	 * 删除产品
	 */
	@RequestMapping(value = "/delete.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteProduct(int id,Model model) {
		int state=this.productService.deleteProductById(id);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除产品成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除产品失败！！！");
			model.addAttribute("second", 2);
		}
		
		return "tip";
	}
	
	
	
	@RequestMapping(value = "/upPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String upPage(Product product, Model model) {
		List<Product> list = this.productService.getProductListWhere(null);
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
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	@RequestMapping(value = "/downPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String downPage(Product product, Model model) {
		List<Product> list = this.productService.getProductListWhere(null);
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
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	@RequestMapping(value = "/homePage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String homePage(Product product, Model model) {
		List<Product> list = this.productService.getProductListWhere(null);
	        page.setCurrentPage(1);
			page.setPageSize(10);
			page.setStar(0);
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	@RequestMapping(value = "/lastPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String lastPage(Product product, Model model) {
		List<Product> list = this.productService.getProductListWhere(null);
			page.setPageSize(10);
			page.setStar((page.getTotalPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			System.out.println("totalPage:"+page.getTotalPage());
			page.setCurrentPage(page.getTotalPage());
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	@RequestMapping(value = "/jumpPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String jumpPage(Product product, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<Product> list = this.productService.getProductListWhere(null);
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
		model.addAttribute("products", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("product", product);
		return "productTable";
	}
	
	@RequestMapping("/allProduct.action")
	@ResponseBody
	public List<Product> allProduct(Product product) {
		List<Product> products = this.productService.allProduct(product);
		return products;
	}

}
