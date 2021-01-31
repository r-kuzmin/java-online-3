package by.epam.training.stringBuilder;

import java.util.Scanner;

/**
 * Вводится строка. Требуется удалить из нее повторяющиеся символы и все
 * пробелы. Например, если было введено "abc cde def", то должно быть выведено
 * "abcdef".
 * 
 * @author rkuzm
 *
 */
public class Task7 {

	public static void main(String[] args) {
		String str = readConsoleString();

		str = deleteSpaces(str);
		str = deleteRepeatSymbols(str);

		System.out.println(str);
	}

	/**
	 * Читает строку из консоли.
	 * 
	 * @return
	 */
	private static String readConsoleString() {
		String str = "";

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter string: ");
		str = sc.nextLine();

		return str;
	}

	/**
	 * Удаляет из строки повторяющиеся символы.
	 * 
	 * @param str
	 * @return
	 */
	private static String deleteRepeatSymbols(String str) {
		StringBuilder sb = new StringBuilder(str);

		for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			for (int j = i + 1; j < sb.length(); j++) {
				if (sb.charAt(j) == ch) {
					sb.deleteCharAt(j);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Удаляет из строки пробелы.
	 * 
	 * @param str
	 * @return
	 */
	private static String deleteSpaces(String str) {
		StringBuilder sb = new StringBuilder(str);

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == ' ') {
				sb.deleteCharAt(i);
			}
		}

		return sb.toString();
	}
}
