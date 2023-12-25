package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.absracts.BrandService;
import kodlama.io.rentACar.business.requests.brand.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.brand.DeleteBrandRequest;
import kodlama.io.rentACar.business.requests.brand.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.brand.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.brand.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;

@Service // Bu sınıf bir business nesnesidir.
public class BrandManager implements BrandService{
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;
	

	@Autowired
	public BrandManager(BrandRepository brandRepository,ModelMapperService modelMapperService,BrandBusinessRules brandBusinessRules) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
		this.brandBusinessRules = brandBusinessRules;
	}
	
		
	@Override
	public List<GetAllBrandsResponse> getAll() {
		// business rules		
		List<Brand> brands = brandRepository.findAll();
		List<GetAllBrandsResponse> brandsResponse = brands.stream().map(
				brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return brandsResponse;
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {	
		//business
		this.brandBusinessRules.checkIfBrandNameExsists(createBrandRequest.getName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
		brandRepository.save(brand);
		
	}


	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand = brandRepository.getReferenceById(id);
		GetByIdBrandResponse brandResponse = modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		return brandResponse;
	}


	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
	}


	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandRepository.delete(brand);
	}


	@Override
	public void delete(int id) {
		this.brandRepository.deleteById(id);
		
		
	}


	
}
