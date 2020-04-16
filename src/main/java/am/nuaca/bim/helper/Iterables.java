package am.nuaca.bim.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tigran Sargsyan on 15-Apr-20.
 */
public class Iterables {

	public static <T> List<T> iterableToList(Iterable<T> iterable) {
		List<T> list = new ArrayList<>();
		for (T item : iterable) {
			list.add(item);
		}
		return list;
	}
}
