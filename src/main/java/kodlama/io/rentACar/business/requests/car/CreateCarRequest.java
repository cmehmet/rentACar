package kodlama.io.rentACar.business.requests.car;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private String plate;
	private double dailyPrice;
	private int modelYear;
	private int state;
	private int modelId;

}