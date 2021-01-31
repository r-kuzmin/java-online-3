package by.epam.training.stringBuilder;

/**
 * С помощью функции копирования и операции конкатенации составить из частей
 * слова “информатика” слово “торт”.
 * 
 * @author rkuzm
 *
 */
public class Task4 {

	public static void main(String[] args) {
		String str = "информатика";
		String res = "";

		char[] ch = str.toCharArray();
		
		res = String.copyValueOf(ch, 7, 1);
		res = res.concat(String.copyValueOf(ch, 3, 2));
		res = res.concat(String.copyValueOf(ch, 7, 1));
		
		System.out.println(res);
	}

}
