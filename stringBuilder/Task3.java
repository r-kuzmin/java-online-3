package by.epam.training.stringBuilder;

/**
 * Проверить, является ли заданное слово палиндромом.
 * 
 * @author rkuzm
 *
 */
public class Task3 {

	public static void main(String[] args) {
		String str = "ололо";
		StringBuilder sb = new StringBuilder(str);

		if (str.equals(sb.reverse().toString())) {
			System.out.println(str + " - палиндром.");
		} else {
			System.out.println(str + " - не палиндром.");
		}
	}

}
