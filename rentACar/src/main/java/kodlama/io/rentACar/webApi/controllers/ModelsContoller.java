package kodlama.io.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.absracts.ModelService;
import kodlama.io.rentACar.business.requests.model.CreateModelRequest;
import kodlama.io.rentACar.business.requests.model.UpdateModelRequest;
import kodlama.io.rentACar.business.responses.model.GetAllModelsResponse;

@RestController
@RequestMapping("/api/models")
public class ModelsContoller {
	private ModelService modelService;
	
	public ModelsContoller(ModelService modelService) {
		this.modelService = modelService;
	}
	
	@GetMapping()
	public List<GetAllModelsResponse> getAll(){
		return this.modelService.getAll();
	}
	
	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add (@RequestBody @Valid CreateModelRequest createModelRequest) {
		this.modelService.add(createModelRequest);
	}
	
	@PutMapping("/update")
	public void update (@RequestBody UpdateModelRequest updateModelRequest) {
		this.modelService.update(updateModelRequest);
	}
	
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		this.modelService.delete(id);
	}
	
	@GetMapping("/{id}")
	public void getById(@PathVariable int id) {
		this.modelService.getById(id);
	}
}
