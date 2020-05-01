package am.nuaca.bim.endpoint.api;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import am.nuaca.bim.service.OutlayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
@RestController
@RequestMapping("api/outlay")
public class OutlayController {

	private final OutlayService outlayService;

	public OutlayController(OutlayService outlayService) {
		this.outlayService = outlayService;
	}

	@PostMapping
	public void calculate(@RequestParam("file") MultipartFile document,
						  HttpServletResponse response) throws IOException {
		String fileName = "naxahashiv.xlsx";
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		outlayService.calculateOutlayFromBudgetDocument(document.getInputStream(), response.getOutputStream());
	}
}
