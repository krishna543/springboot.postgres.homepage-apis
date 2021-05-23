package com.ecommerce.springboot.entities;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "products")
public class Products extends Audit{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductKey")
	private long id;
	
	@Column(name = "ProductName")
	private String ProductName;
	
	@Column(name = "Description")
	private String Description;
	
	@Column(name = "Color")
	private String Color;
	
	@Column(name = "Image")
	private String Image;
	
	@Column(name = "Quantity")
	private float Quantity;
	
	@Column(name = "Units")
	private String Units;
	
	@Column(name = "AWP")
	private float AWP;

	@Column(name = "ActiveFlag")
	private boolean ActiveFlag;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subcategory_key",referencedColumnName = "sub_category_key")
	private SubCategory subCategory;
	

	//////////////////////////////////////////////////////////////////////////
	//getters and setters

	

	public String getProductName() {
		return ProductName;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public float getQuantity() {
		return Quantity;
	}

	public void setQuantity(float quantity) {
		Quantity = quantity;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public float getAWP() {
		return AWP;
	}

	public void setAWP(float aWP) {
		AWP = aWP;
	}

	public boolean isActiveFlag() {
		return ActiveFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		ActiveFlag = activeFlag;
	}
	@JsonIgnore
	public SubCategory getCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subcategory) {
		this.subCategory = subcategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//////////////////////////////////////////////////////////////////////////
	//all args constructor and no args 
	
	public Products() {
		// TODO Auto-generated constructor stub
	} Products(String productName, String description, String color, String image, float quantity, String units,
			float aWP, boolean activeFlag) {
		super();
		ProductName = productName;
		Description = description;
		Color = color;
		Image = image;
		Quantity = quantity;
		Units = units;
		AWP = aWP;
		ActiveFlag = activeFlag;
	}	

		
	
}
