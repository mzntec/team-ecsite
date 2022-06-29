package jp.co.internous.brown.model.form;

import java.io.Serializable;

public class SearchForm implements Serializable {
	private static final long serialVersionUID =1L;
	
	private int categoryId;
	private String itemName;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
