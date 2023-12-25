package kodlama.io.rentACar.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.rentACar.business.absracts.CarService;
import kodlama.io.rentACar.business.requests.car.CreateCarRequest;
import kodlama.io.rentACar.business.requests.car.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.car.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.car.GetByIdCarResponse;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;
	
	public CarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping()
	public List<GetAllCarsResponse> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping("/{id}")
	public GetByIdCarResponse getById(@PathVariable() int id) {
		return this.carService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(CreateCarRequest createCarRequest) {
		this.carService.add(createCarRequest);
	}
	
	@PutMapping("/update")
	public void update (UpdateCarRequest updateCarRequest) {
		this.carService.update(updateCarRequest);
	}
	
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable() int id) {
		this.carService.delete(id);
	}
}
