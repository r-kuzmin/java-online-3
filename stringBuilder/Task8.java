package by.epam.training.stringBuilder;

import java.util.Scanner;

/**
 * Вводится строка слов, разделенных пробелами. Найти самое длинное слово и
 * вывести его на экран. Случай, когда самых длинных слов может быть несколько,
 * не обрабатывать.
 * 
 * @author rkuzm
 *
 */
public class Task8 {

	public static void main(String[] args) {
		String str = readConsoleString();
		
		String[] words = str.split(" ");
		
		int maxLen = 0;
		int idx = 0;
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > maxLen) {
				maxLen = words[i].length();
				idx = i;
			}
		}
		
		System.out.println(words[idx]);
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
