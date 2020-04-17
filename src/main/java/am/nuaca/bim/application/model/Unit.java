package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public enum Unit {
	MM {
		@Override
		public double from(Unit unit, double value) {
			switch (unit) {
				case MM:
					return value;
				case SM:
					return value * 10;
				case M:
					return value * 1000;
				default:
					throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, unit));
			}
		}
	},
	SM {
		@Override
		public double from(Unit unit, double value) {
			switch (unit) {
				case MM:
					return value / 10;
				case SM:
					return value;
				case M:
					return value * 100;
				default:
					throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, unit));
			}
		}
	},
	M {
		@Override
		public double from(Unit unit, double value) {
			switch (unit) {
				case MM:
					return value / 1000;
				case SM:
					return value / 100;
				case M:
					return value;
				default:
					throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, unit));
			}
		}
	},
	M2 {
		@Override
		public double from(Unit unit, double value) {
			if (unit == Unit.M2) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, unit));
		}
	},
	M3 {
		@Override
		public double from(Unit unit, double value) {
			if (unit == Unit.M3) {
				return value;
			}
			throw new UnsupportedOperationException(String.format("Cannot convert %s to %s", this, unit));
		}
	};

	public static Unit fromString(String value) {
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
			default:
				throw new IllegalArgumentException(value);
		}
	}

	public abstract double from(Unit unit, double value);
}
