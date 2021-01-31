package by.epam.training.stringAsArray;

/**
 * Удалить в строке все лишние пробелы, то есть серии подряд идущих пробелов
 * заменить на одиночные пробелы. Крайние пробелы в строке удалить.
 * 
 * @author rkuzm
 *
 */
public class Task5 {

	public static void main(String[] args) {
		String str = "  Длинная    строка    с   большим    количеством    пробелов    .";

		// Сначала удалим лишнее в начале и в конце строки.
		str = str.trim();
		
		// Заменяем двойные пробелы на одинарные.
		do {
			str = str.replaceAll("  ", " ");
		} while (str.indexOf("  ") != -1);
		
		System.out.println(str);
	}

}
