package by.epam.training.stringBuilder;

/**
 * Подсчитать, сколько раз среди символов заданной строки встречается буква “а”.
 * 
 * @author rkuzm
 *
 */
public class Task5 {

	public static void main(String[] args) {
		String str = "auto-generated method stub --a";

		int cnt = 0;

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				cnt++;
			}
		}

		System.out.println("Буква 'a' встречается " + cnt + " раз.");
	}

}
