package by.epam.training.stringAsArray;

/**
 * В строке найти количество цифр.
 * 
 * @author rkuzm
 *
 */
public class Task3 {

	public static void main(String[] args) {
		String str = "15 человек на сундук мертвеца и 1 бутылка рома";
		
		int cnt = 0;
		
		for (int i = 0; i < str.length(); i++) {
			int code = (int) str.charAt(i);
			if (code >= 48 && code <= 57) {
				cnt++;
			}
		}
		
		System.out.println("Количество цифр = " + cnt);
	}

}
