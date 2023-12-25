package kodlama.io.rentACar.business.requests.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
	@NotNull  //jakarta.validation.constraints list // Bu kontrolleri backend'de zorunlu,frontend'de opsiyonel(ama olursa daha iyi) olarak kullanmalıyız.
	@NotBlank
	@Size(min=3 , max=20)
	private String name;
	
	//@NotNull  	
	//@NotBlank
	private int brandId;
}
