package com.ecommerce.springboot.entities;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category extends Audit{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_key")
	private long id;
	
	@Column(name = "category_name")
	private String 	CategoryName;
	
	
	@Column(name = "active_flag")
	private boolean ActiveFlag;
	
	
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
	private List<SubCategory> SubCategory; 
//////////////////////////////////////////////////////////////////////////////
	//getters and setters
	
///////////////////////////////////////////////////////////////
	//No Args Constructor
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public boolean isActiveFlag() {
		return ActiveFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		ActiveFlag = activeFlag;
	}
	@JsonIgnore
	public List<SubCategory> getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(List<SubCategory> subCategory) {
		SubCategory = subCategory;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//All args Constructor
	public Category(String categoryName, boolean activeFlag) {
		super();
		CategoryName = categoryName;
		ActiveFlag = activeFlag;
	}
	

}
