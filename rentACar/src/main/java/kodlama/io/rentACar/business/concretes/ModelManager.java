package kodlama.io.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.absracts.ModelService;
import kodlama.io.rentACar.business.requests.model.CreateModelRequest;
import kodlama.io.rentACar.business.requests.model.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.model.GetAllModelsResponse;
import kodlama.io.rentACar.business.responses.model.GetByIdModelResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentACar.entities.concretes.Model;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService{
	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	
	
	@Override
	public List<GetAllModelsResponse> getAll() {
		// business rules
		List<Model> models = modelRepository.findAll();
		List<GetAllModelsResponse> modelsResponse = models.stream()
													.map(model-> modelMapperService.forResponse().map(model, GetAllModelsResponse.class))
													.toList();
		return modelsResponse;
	}


	@Override
	public void add(CreateModelRequest createModelRequest) {
		// business rules
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		this.modelRepository.save(model);
		
	}


	@Override
	public void update(UpdateModelRequest updateModelRequest) {
		Model model = this.modelMapperService.forRequest().map(updateModelRequest, Model.class);
		modelRepository.save(model);

	}


	@Override
	public void delete(int id) {
		this.modelRepository.deleteById(id);
		
	}


	@Override
	public GetByIdModelResponse getById(int id) {
		Model model = this.modelRepository.getReferenceById(id);
		GetByIdModelResponse modelResponse = this.modelMapperService.forResponse().map(model, GetByIdModelResponse.class);
		return modelResponse;
	}

}
