package am.nuaca.bim.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ani Khachatryan on 15-Sep-21.
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
