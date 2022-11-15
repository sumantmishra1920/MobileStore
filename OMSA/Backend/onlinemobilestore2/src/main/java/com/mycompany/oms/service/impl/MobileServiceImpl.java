package com.mycompany.oms.service.impl;

import com.mycompany.oms.entities.Category;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.repository.ICategoryRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.service.IMobileService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Mobile service implementation
@Service
public class MobileServiceImpl implements IMobileService{

	@Autowired
	private IMobileRepository mobileRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	public MobileServiceImpl(IMobileRepository mobileRepository, ICategoryRepository categoryRepository) {
		this.mobileRepository = mobileRepository;
		this.categoryRepository = categoryRepository;
	}
	
	//To add a new mobile to the database
	@Override
	public Mobile addMobile(Mobile mobile) {
		return mobileRepository.save(mobile);
	}

	//To update a specific mobile's details
	@Override
	public Mobile updateMobile(int id, Mobile mobile) throws MobileNotFoundException {
		Optional<Mobile> opt = mobileRepository.findById(id);
		if(opt.isPresent()) {
			Mobile existingMobile = opt.get();
			existingMobile.setMobileName(mobile.getMobileName());
			existingMobile.setMobileCost(mobile.getMobileCost());
			existingMobile.setModelNumber(mobile.getModelNumber());
			existingMobile.setCompanyName(mobile.getCompanyName());
			existingMobile.setMfd(mobile.getMfd());
			existingMobile.setCategory(mobile.getCategory());
			mobileRepository.save(existingMobile);
			return existingMobile;
		} else {
			throw new MobileNotFoundException("Mobile not found in the inventory");
		}
	}

	//To remove a mobile from database
	@Override
	public Mobile deleteMobile(int id) throws MobileNotFoundException {
		Optional<Mobile> opt = mobileRepository.findById(id);
		if(opt.isPresent()) {
			Mobile deletedMobile = opt.get();
			mobileRepository.deleteById(id);
			return deletedMobile;
		} else {
			throw new MobileNotFoundException("Mobile not found in the inventory");
		}
	}

	//To show list of all mobiles
	@Override
	public List<Mobile> showAllMobiles() {
		return mobileRepository.findAll();
	}

	//To show details of a specific mobile
	@Override
	public Mobile showMobileById(int id) throws MobileNotFoundException {
		Optional<Mobile> opt = mobileRepository.findById(id);
		if(opt.isPresent()) {
			Mobile existingMobile = opt.get();
			return existingMobile;
		} else {
			throw new MobileNotFoundException("Mobile not found in the inventory");
		}
	}
	
	//To get list of all the same mobiles that are available in store
	@Override
	public List<Mobile> showMobilesByName(String mobileName) {
		return mobileRepository.findByMobileName(mobileName);
	}

	//To check the available quantity of a specific mobile in stock
	@Override
	public long availableQuantity(String mobileName) {
		
		return mobileRepository.countByMobileName(mobileName);
	}

	//To show a list of mobiles that are available in the specific price range
	@Override
	public List<Mobile> showMobilesByPriceRange(float price1, float price2) {
		
		return mobileRepository.findMobilesInPriceRange(price1, price2);
	}

	//To show a list of mobiles belonging to a specific category
	@Override
	public List<Mobile> showMobilesByCategory(int categoryId) throws NoSuchCategoryException{
		Optional<Category> opt = categoryRepository.findById(categoryId);
		if(opt.isPresent()) {
			Category category = opt.get();
			return mobileRepository.findMobilesInCategory(category);
		} else {
			throw new NoSuchCategoryException("No category with id "+categoryId);
		}
	}

	//To show a list of mobiles from a specific company
	@Override
	public List<Mobile> showMobilesByCompany(String companyName) {
		return mobileRepository.findAllMobilesFromCompany(companyName);
	}
}
