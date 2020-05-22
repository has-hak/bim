package am.nuaca.bim.endpoint.api;

import am.nuaca.bim.application.model.MeasureType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("api/util")
public class UtilController {

	@GetMapping("measure-types")
	public MeasureType[] getMeasureTypes() {
		return MeasureType.values();
	}
}
