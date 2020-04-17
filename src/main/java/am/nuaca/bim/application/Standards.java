package am.nuaca.bim.application;

import java.util.Map;

import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.Unit;

/**
 * @author Tigran Sargsyan on 18-Apr-20.
 */
public class Standards {

	public static final Map<MeasureType, Unit> MEASURE_UNITS = Map.of(MeasureType.AREA, Unit.M2, MeasureType.HEIGHT,
			Unit.M, MeasureType.THICKNESS, Unit.M, MeasureType.VOLUME, Unit.M3);
}
