package am.nuaca.bim.application;

import java.util.Map;

import am.nuaca.bim.application.model.MeasureType;
import am.nuaca.bim.application.model.MeasureUnit;

/**
 * @author Ani Khachatryan on 18-Apr-20.
 */
public class Standards {

	public static final Map<MeasureType, MeasureUnit> MEASURE_UNITS = Map.of(MeasureType.AREA, MeasureUnit.M2,
			MeasureType.HEIGHT, MeasureUnit.M, MeasureType.THICKNESS, MeasureUnit.M, MeasureType.VOLUME, MeasureUnit.M3,
			MeasureType.WEIGHT, MeasureUnit.KG, MeasureType.PERIMETER, MeasureUnit.M);

	public static final double OVERHEAD_COST_PERCENTAGE = 13.3;

	public static final double PROFIT_PERCENTAGE = 10;
}
