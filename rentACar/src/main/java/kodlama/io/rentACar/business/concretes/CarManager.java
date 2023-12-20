package kodlama.io.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.absracts.CarService;
import kodlama.io.rentACar.business.requests.car.CreateCarRequest;
import kodlama.io.rentACar.business.requests.car.UpdateCarRequest;
import kodlama.io.rentACar.business.responses.car.GetAllCarsResponse;
import kodlama.io.rentACar.business.responses.car.GetByIdCarResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.CarRepository;
import kodlama.io.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService{
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}
	
	
	@Override
	public List<GetAllCarsResponse> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> carsResponse = cars.stream().map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class)).toList();
		
		return carsResponse;
	}

	@Override
	public GetByIdCarResponse getById(int id) {
		Car car = this.carRepository.getReferenceById(id);
		GetByIdCarResponse carResponse = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
		return carResponse;
	}


	@Override
	public void add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carRepository.save(car);
	}


	@Override
	public void update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carRepository.save(car);
	}


	@Override
	public void delete(int id) {
		this.carRepository.deleteById(id);
	}

}
