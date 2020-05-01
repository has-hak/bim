package am.nuaca.bim.entity;

/**
 * @author Tigran Sargsyan on 01-May-20.
 */
public class Code {

	private final String part1;
	private final String part2;
	private final String part3;

	public Code(String part1, String part2, String part3) {
		this.part1 = part1;
		this.part2 = part2;
		this.part3 = part3;
	}

	public String getPart1() {
		return part1;
	}

	public String getPart2() {
		return part2;
	}

	public String getPart3() {
		return part3;
	}
}
