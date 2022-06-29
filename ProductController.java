package jp.co.internous.brown.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.gson.Gson;

import jp.co.internous.brown.model.domain.MstProduct;
import jp.co.internous.brown.model.mapper.MstProductMapper;
import jp.co.internous.brown.model.session.LoginSession;

@Controller
@RequestMapping("/brown/product")
public class ProductController {
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	MstProductMapper mstProductMapper;
	
	Gson gson = new Gson();
	
	@RequestMapping(value="/{id}")
	public String index(@PathVariable("id") int id, Model m) {
		
		List<MstProduct> product = null;
		product = mstProductMapper.findByProductId(id);
		
		m.addAttribute("product", product);
		m.addAttribute("loginSession", loginSession);
		
		return "product_detail";
	}
	
}
