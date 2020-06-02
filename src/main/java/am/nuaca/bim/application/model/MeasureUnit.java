package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public enum MeasureUnit {
	MM("մմ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			switch (measureUnit) {
				case MM:
					return value;
				case SM:
					return value * 10;
				case M:
					return value * 1000;
				default:
					throw new UnsupportedOperationException(
							String.format("Cannot convert %s to %s", this, measureUnit));
			}
		}
	},
	SM("սմ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			switch (measureUnit) {
				case MM:
					return value / 10;
				case SM:
					return value;
				case M:
					return value * 100;
				default:
					throw new UnsupportedOperationException(
							String.format("Cannot convert %s to %s", this, measureUnit));
			}
		}
	},
	M("մ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			switch (measureUnit) {
				case MM:
					return value / 1000;
				case SM:
					return value / 100;
				case M:
					return value;
				default:
					throw new UnsupportedOperationException(
							String.format("Cannot convert %s to %s", this, measureUnit));
			}
		}
	},
	M2("մ²") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			if (measureUnit == MeasureUnit.M2) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, measureUnit));
		}
	},
	M3("մ³") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			if (measureUnit == MeasureUnit.M3) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, measureUnit));
		}
	},
	KG("կգ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			switch (measureUnit) {
				case TON:
					return value * 1000;
				case KG:
					return value;
				default:
					throw new UnsupportedOperationException(
							String.format("Cannot convert %s to %s", this, measureUnit));
			}
		}
	},
	TON("տ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			switch (measureUnit) {
				case TON:
					return value;
				case KG:
					return value / 1000;
				default:
					throw new UnsupportedOperationException(
							String.format("Cannot convert %s to %s", this, measureUnit));
			}
		}
	},
	HUMAN_HOUR("մարդ-ժամ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			if (measureUnit == MeasureUnit.HUMAN_HOUR) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, measureUnit));
		}
	},
	MACHINE_HOUR("մեքենա-ժամ") {
		@Override
		public double from(MeasureUnit measureUnit, double value) {
			if (measureUnit == MeasureUnit.MACHINE_HOUR) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, measureUnit));
		}
	};

	private final String localizedName;

	MeasureUnit(String localizedName) {
		this.localizedName = localizedName;
	}

	public static MeasureUnit fromString(String value) {
		switch (value) {
			case "mm":
			case "մմ":
				return MM;
			case "sm":
			case "սմ":
				return SM;

			case "m":
			case "մ":
				return M;
			case "m2":
			case "m²":
			case "մ2":
			case "մ²":
				return M2;

			case "m3":
			case "m³":
			case "մ3":
			case "մ³":
				return M3;
			case "կգ":
			case "kg":
				return KG;
			case "տ":
			case "ton":
				return TON;
			case "մարդ-ժամ":
				return HUMAN_HOUR;
			case "մեքենա-ժամ":
				return MACHINE_HOUR;
			default:
				throw new IllegalArgumentException(value);
		}
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public abstract double from(MeasureUnit measureUnit, double value);

}
