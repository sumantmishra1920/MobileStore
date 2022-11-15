package com.mycompany.oms.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//Mobile entity
@Entity
@Table(name="Mobiles")
public class Mobile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mobile_id",nullable=false)
	private int mobileId;
	@Column(name="mobile_name",nullable=false)
	@NotEmpty(message="Mobile name cannot be blank")
	private String mobileName;
	@Column(name="mobile_cost",nullable=false)
	@Min(value=0, message="Cost cannot be negative")
	private float mobileCost;
	@Column(name="mfd",nullable=false)
	private LocalDate mfd;
	@Column(name="model_number",nullable=false)
	@NotEmpty(message="Model number cannot be blank")
	private String modelNumber;
	@Column(name="company_name",nullable=false)
	@NotEmpty(message="Company name cannot be blank")
	@Size(min=2, message="Company name should be atleast 2 characters long")
	private String companyName;
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	public Mobile() {
		
	}
	public Mobile(int mobileId, String mobileName, float mobileCost, LocalDate mfd, String modelNumber,
			String companyName, Category category) {
		super();
		this.mobileId = mobileId;
		this.mobileName = mobileName;
		this.mobileCost = mobileCost;
		this.mfd = mfd;
		this.modelNumber = modelNumber;
		this.companyName = companyName;
		this.category = category;
	}
	public int getMobileId() {
		return mobileId;
	}
	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}
	public String getMobileName() {
		return mobileName;
	}
	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	public float getMobileCost() {
		return mobileCost;
	}
	public void setMobileCost(float mobileCost) {
		this.mobileCost = mobileCost;
	}
	public LocalDate getMfd() {
		return mfd;
	}
	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", mobileName=" + mobileName + ", mobileCost=" + mobileCost + ", mfd="
				+ mfd + ", modelNumber=" + modelNumber + ", companyName=" + companyName + ", category=" + category
				+ "]";
	}
	
}
