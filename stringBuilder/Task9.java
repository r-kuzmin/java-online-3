package by.epam.training.stringBuilder;

import java.util.Scanner;

/**
 * Посчитать количество строчных (маленьких) и прописных (больших) букв в
 * введенной строке. Учитывать только английские буквы.
 * 
 * @author rkuzm
 *
 */
public class Task9 {

	public static void main(String[] args) {
		String str = readConsoleString();

		int low = 0;
		int high = 0;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 97 && ch <= 122) {
				low++;
			} else if (ch >= 65 && ch <= 90) {
				high++;
			}
		}

		System.out.println("Строчных: " + low);
		System.out.println("Прописных: " + high);
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
}
