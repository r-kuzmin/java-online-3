package by.epam.training.stringBuilder;

/**
 * Из заданной строки получить новую, повторив каждый символ дважды.
 * 
 * @author rkuzm
 *
 */
public class Task6 {

	public static void main(String[] args) {
		String str = "Auto-generated method stub";
 
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			sb.append(tmp).append(tmp);
		}

		System.out.println(sb.toString());
	}

}
