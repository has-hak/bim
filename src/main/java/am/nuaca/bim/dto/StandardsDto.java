package am.nuaca.bim.dto;

import java.util.Map;

import am.nuaca.bim.application.Standards;
import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.MeasureUnit;

/**
 * @author Tigran Sargsyan on 06-May-20.
 */
public class StandardsDto {

	private final Map<MeasureType, MeasureUnit> measureUnits = Standards.MEASURE_UNITS;

	public Map<MeasureType, MeasureUnit> getMeasureUnits() {
		return measureUnits;
	}
}
