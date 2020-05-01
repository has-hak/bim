package am.nuaca.bim.endpoint.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Tigran Sargsyan on 01-May-20.
 */
@Controller
public class OutlayViewController {

	@GetMapping(value = {"/", "/outlay-calculation"})
	public String compilationsSave(Model model) {
		return "outlay-calculation";
	}
}
