package by.epam.training.stringBuilder;

/**
 * Строка X состоит из нескольких предложений, каждое из которых кончается
 * точкой, восклицательным или вопросительным знаком. Определить количество
 * предложений в строке X.
 * 
 * @author rkuzm
 *
 */
public class Task10 {

	public static void main(String[] args) {
		String str = "TODO! Auto-generated method stub. Clear?";

		int cnt = 0;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '.' || ch == '!' || ch == '?') {
				cnt++;
			}
		}

		System.out.println("Количество предложений = " + cnt);
	}

}
