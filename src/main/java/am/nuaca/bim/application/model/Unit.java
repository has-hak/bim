package am.nuaca.bim.application.model;

/**
 * @author Tigran Sargsyan on 16-Apr-20.
 */
public enum Unit {
	MM,
	SM,
	M,
	M2,
	M3;

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
}
