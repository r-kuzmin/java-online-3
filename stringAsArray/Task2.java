package by.epam.training.stringAsArray;

/**
 * Замените в строке все вхождения 'word' на 'letter'.
 * 
 * @author rkuzm
 *
 */
public class Task2 {
	
	public static void main(String[] args) {
		String myString = "Microsoft word (WinWord или просто word) — текстовый процессор, предназначенный для редактирования текстов.";
		
		myString = myString.replaceAll("word", "letter");
		
		System.out.println(myString);
	}
}
