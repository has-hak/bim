package am.nuaca.bim.endpoint.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import am.nuaca.bim.dto.BudgetDocumentDto;
import am.nuaca.bim.dto.BudgetDocumentDto.MachineDto;
import am.nuaca.bim.dto.BudgetDocumentDto.MaterialDto;
import am.nuaca.bim.dto.BudgetDocumentDto.ResourceDto;
import am.nuaca.bim.dto.BudgetDocumentDto.WorkforceDto;
import am.nuaca.bim.entity.Machine;
import am.nuaca.bim.entity.Material;
import am.nuaca.bim.entity.Resource;
import am.nuaca.bim.entity.Workforce;
import am.nuaca.bim.service.OutlayService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ani Khachatryan on 15-Sep-21.
 */
@RestController
@RequestMapping("api/outlay")
public class OutlayController {

	private final OutlayService outlayService;

	public OutlayController(OutlayService outlayService) {
		this.outlayService = outlayService;
	}

	@PostMapping("/process-budget-document")
	public BudgetDocumentDto processBudgetDocument(@RequestParam("file") MultipartFile document) throws IOException {
		List<Resource> resources = outlayService.processBudgetDocument(document.getInputStream());

		return new BudgetDocumentDto(
				resources.stream().map(OutlayController::convertResource).collect(Collectors.toList()));
	}

	@PostMapping("/calculate-from-budget-document")
	public void calculate(@RequestParam("file") MultipartFile document,
						  HttpServletResponse response) throws IOException {
		String fileName = "naxahashiv.xlsx";
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		outlayService.calculateOutlayFromBudgetDocument(document.getInputStream(), response.getOutputStream());
	}

	private static ResourceDto convertResource(Resource resource) {
		return new ResourceDto(resource.getId(), resource.getCode().toString(), resource.getTitle(),
				convertWorkforce(resource.getWorkforces()), convertMachine(resource.getMachines()),
				convertMaterial(resource.getMaterials()));
	}

	private static List<WorkforceDto> convertWorkforce(List<Workforce> workforces) {
		return workforces.stream().map(OutlayController::convertWorkforce).collect(Collectors.toList());
	}

	private static WorkforceDto convertWorkforce(Workforce workforce) {
		return new WorkforceDto(workforce.getId(), workforce.getCode().toString(), workforce.getTitle(),
				workforce.getUnit(), workforce.getUnitCost());
	}

	private static List<MachineDto> convertMachine(List<Machine> machines) {
		return machines.stream().map(OutlayController::convertMachine).collect(Collectors.toList());
	}

	private static MachineDto convertMachine(Machine machine) {
		return new MachineDto(machine.getId(), machine.getCode().toString(), machine.getTitle(), machine.getUnit(),
				machine.getUnitCost());
	}

	private static List<MaterialDto> convertMaterial(List<Material> materials) {
		return materials.stream().map(OutlayController::convertMaterial).collect(Collectors.toList());
	}

	private static MaterialDto convertMaterial(Material material) {
		return new MaterialDto(material.getId(), material.getCode().toString(), material.getTitle(), material.getUnit(),
				material.getUnitCost(), material.getMeasureType());
	}
}
