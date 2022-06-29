package jp.co.internous.brown.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import jp.co.internous.brown.model.domain.MstCategory;
import jp.co.internous.brown.model.domain.MstProduct;
import jp.co.internous.brown.model.form.SearchForm;
import jp.co.internous.brown.model.mapper.MstCategoryMapper;
import jp.co.internous.brown.model.mapper.MstProductMapper;
import jp.co.internous.brown.model.session.LoginSession;

@Controller
@RequestMapping("/brown")
public class IndexController {
	
	@Autowired
	LoginSession loginSession;
	
	@Autowired
	MstProductMapper mstProductMapper;
	
	@Autowired
	MstCategoryMapper mstCategorytMapper;
	
	Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		if(loginSession.getId()==0 && loginSession.getTempId()==0) {
			
			Random rnd = new Random();
			int tempId = (-1)*(100000000 + rnd.nextInt(900000000));
			loginSession.setTempId(tempId);
		}
		
		List<MstProduct> products = null;
		List<MstCategory>categories = null;
		
		products = mstProductMapper.findAll();
		categories = mstCategorytMapper.findAll();
		
		m.addAttribute("products", products);
		m.addAttribute("categories", categories);
		m.addAttribute("loginSession", loginSession);
		
		return "index";
	}
	
	@RequestMapping(value="/searchItem", method = RequestMethod.POST)
	public String searchItem(Model m,@ModelAttribute SearchForm f) {
		
		List<MstProduct> products = null;
		List<MstCategory>categories = null;
		
		String ItemName = f.getItemName().replaceAll("　", " ").replaceAll("\\s{2,}", " ").trim();
		String[] keywords =ItemName.split(" ");
		categories = mstCategorytMapper.findAll();
		
		
		if(f.getCategoryId() ==0 && f.getItemName().isEmpty()) {
			products = mstProductMapper.findAll();
		}else if(f.getCategoryId() !=0 && f.getItemName().isEmpty()) {
			products = mstProductMapper.findByCategoryId(f.getCategoryId());
		}else if(f.getCategoryId() ==0 && !(f.getItemName().isEmpty())) {
			products = mstProductMapper.findByItemName(keywords);
		}else if(f.getCategoryId() !=0 && !(f.getItemName().isEmpty())) {
			products = mstProductMapper.findByCategoryIdAndItemName(f.getCategoryId(),keywords);
		}
		
		m.addAttribute("products", products);
		m.addAttribute("categories", categories);
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("SearchForm", f);
		
	return "index";
	}
}
