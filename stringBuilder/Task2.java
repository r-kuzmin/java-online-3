package by.epam.training.stringBuilder;

/**
 * В строке вставить после каждого символа 'a' символ 'b'.
 * 
 * @author rkuzm
 *
 */
public class Task2 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("auto-generated method stub --a");

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == 'a') {
				sb.insert(i + 1, 'b');
			}
		}

		System.out.println(sb);
	}

}
