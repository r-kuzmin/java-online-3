package by.epam.training.stringAsArray;

import java.util.Arrays;

/**
 * Дан массив названий переменных в camelCase. Преобразовать названия в
 * snake_case.
 * 
 * @author rkuzm
 *
 */
public class Task1 {

	public static void main(String[] args) {
		String[] array = new String[] { "myValue", "newVariable", "oldVariable", "trash", "", "Not-Correct-Name" };

		String[] result = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			result[i] = camelToSnake(array[i]);
		}

		System.out.println(Arrays.toString(result));
	}

	/**
	 * Преобразует строку из формата camelCase в snake_case.
	 * 
	 * @param camel - исходная строка.
	 * @return
	 */
	private static String camelToSnake(String camel) {

		if (camel == null) {
			return null;
		} else if (camel.isEmpty()) {
			return "";
		}

		// Преобразуем строку к нижнему регистру и сравним посимвольно с исходной.
		// Там, где символы отличаются (кроме первого), нужно добавить "_".
		// Таким образом, сначала будет известна длина результата, а потом можно быстро
		// его заполнить.

		int len = camel.length();

		String low = camel.toLowerCase();
		if (low.equals(camel)) {
			return low;
		}

		int add = 0;
		for (int i = 1; i < len; i++) {
			if (camel.charAt(i) != low.charAt(i)) {
				add++;
			}
		}

		// Формируем результат.
		char[] result = new char[len + add];

		for (int i = 0, j = 0; i < len; i++) {
			if (camel.charAt(i) != low.charAt(i) && i != 0) {
				result[j] = '_';
				j++;
			}
			result[j] = low.charAt(i);
			j++;
		}

		String snake = new String(result);

		return snake;
	}
}
