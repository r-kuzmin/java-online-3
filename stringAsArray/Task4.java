package by.epam.training.stringAsArray;

/**
 * В строке найти количество чисел.
 * 
 * @author rkuzm
 *
 */
public class Task4 {

	public static void main(String[] args) {
		String str = "15 человек на сундук мертвеца и 1 бутылка рома = 0-вая удовлетворённость 77";
		
		int cnt = 0;
		int len = str.length();
		boolean num = false;
		
		for (int i = 0; i < len; i++) {
			int code = (int) str.charAt(i);
			
			if (code >= 48 && code <= 57) {
				// Началось число.
				num = true;
				
				if (i == len - 1) {
					cnt++; // Для символов в конце строки.
				}
				
			} else if (num) {
				// Закончилось число.
				cnt++;
				num = false;
			}
		}
		
		System.out.println("Количество чисел = " + cnt);
	}

}
