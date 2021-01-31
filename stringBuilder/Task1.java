package by.epam.training.stringBuilder;

/**
 * Дан текст (строка). Найдите наибольшее количество подряд идущих пробелов в
 * нем.
 * 
 * @author rkuzm
 *
 */
public class Task1 {

	public static void main(String[] args) {
		String str = "  Длинная строка   с   большим      количеством  пробелов";

		int result = 0;
		int current = 0;

		int len = str.length();
		boolean space = false;

		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);

			if (ch == ' ') {
				// Начались пробелы.
				space = true;
				current++;

			} else if (space) {
				// Закончились пробелы.
				space = false;

				if (current > result) {
					result = current;
				}

				current = 0;
			}
		}

		if (current > result) {
			result = current; // Для правого края строки.
		}

		System.out.println("Максимальное количество пробелов = " + result);
	}

}
