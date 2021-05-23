package com.ecommerce.springboot.entities;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "subcategory")
public class SubCategory extends Audit{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_key")
	private long id;
	
	@Column(name = "subcategoryname")	
	private String SubCategoryName;	
	
	@Column(name = "activeflag")
	private boolean ActiveFlag;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_key",referencedColumnName = "category_key")
	private Category category;
	
	@OneToMany(mappedBy = "subCategory" ,cascade = CascadeType.ALL)
	private List<Products> Products;
////////////////////////////////////////////////////////////////////////////
	//getters and setters

	
	//////////////////////////////////////////////////
	//all args constructor except id and no args constructor

	public SubCategory() {
		// TODO Auto-generated constructor stub
	} 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubCategoryName() {
		return SubCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		SubCategoryName = subCategoryName;
	}
	public boolean isActiveFlag() {
		return ActiveFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		ActiveFlag = activeFlag;
	}
	@JsonIgnore
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@JsonIgnore
	public List<Products> getProducts() {
		return Products;
	}
	public void setProducts(List<Products> products) {
		Products = products;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SubCategory(String subCategoryName, boolean activeFlag) {
		super();
		SubCategoryName = subCategoryName;
		ActiveFlag = activeFlag;
	}
	
	

	
	
}
