package by.epam.training.textEditor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cоздать приложение, разбирающее текст (текст хранится в строке) и позволяющее
 * выполнять с текстом три различных операции: отсортировать абзацы по
 * количеству предложений; в каждом предложении отсортировать слова по длине;
 * отсортировать лексемы в предложении по убыванию количества вхождений
 * заданного символа, а в случае равенства – по алфавиту.
 * 
 * @author R. Kuzmin
 *
 */
public class Main {

	// Текущий текст, разбитый на абзацы, предложения, слова + знаки препинания.
	// Было бы удобнее для хранения этих данных использовать объектную модель,
	// но раз уж строки рассматриваются до объектов...
	private static String[][][] text;

	public static void main(String[] args) {

		System.out.println("Сегодня в меню:");
		System.out.println("1: Загрузить исходный текст.");
		System.out.println("2: Вывести текущий текст.");
		System.out.println("3: Отсортировать абзацы по количеству предложений.");
		System.out.println("4: Отсортировать слова в предложениях по длине.");
		System.out.println("5: Отсортировать лексемы по кол-ву вхождений символа.");
		System.out.println("0: Выход.");

		int act = -1;

		while (act != 0) {
			act = getNumber();

			switch (act) {
			case 1:
				loadSourceText();

			case 2:
				printCurrentText();
				break;

			case 3:
				if (sortParagraphs()) {
					printCurrentText();
				}
				break;

			case 4:
				if (sortWordsByLength()) {
					printCurrentText();
				}
				break;

			case 5:
				if (sortLexems()) {
					printCurrentText();
				}
				break;

			default:
				break;
			}
		}

		System.out.println("Работа завершена.");
	}

	/**
	 * Возвращает введённое пользователем число.
	 * 
	 * @return
	 */
	private static int getNumber() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Номер операции >>");

		while (!sc.hasNextInt()) {
			sc.next();
			System.out.print("Номер операции >>");
		}

		return sc.nextInt();
	}

	/**
	 * Возвращает введённый пользователем символ.
	 * 
	 * @return
	 */
	private static char getChar() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Символ >>");

		while (!sc.hasNext()) {
			sc.next();
			System.out.print("Символ >>");
		}

		return sc.next().charAt(0);
	}

	/**
	 * Загружает исходный текст и готовит его к обработке: разбивает на абзацы,
	 * предложения, слова.
	 */
	private static void loadSourceText() {
		String source = String.join("\n",
				"При   работе со строками важно понимать, что объект String является неизменяемым! То есть при любых операциях над строкой, которые изменяют эту строку, фактически будет создаваться новая строка.",
				"Строка представляет собой последовательность символов. Для работы со строками в Java определен класс String, который предоставляет ряд методов для манипуляции строками... Физически объект String представляет собой ссылку на область в памяти, в которой размещены символы.",
				"", "Эти классы похожи.");

		// Делим текст на абзацы. Разделители абзацев:
		// CR - MacOS, CR+LF - Windows, LF - Unix.

		String[] paragraphs = source.split("\\r?\\n|\\r");
		text = new String[paragraphs.length][][];

		for (int x = 0; x < paragraphs.length; x++) {

			// Делим абзац на предложения. В данном случае разделитель - один или больше
			// пробелов, перед которыми есть точка, восклицательный или вопросительных знак.
			// Возможны проблемы, когда в тексте встречаются сокращения.
			// При этом даже проверка букв перед/после точки может не работать.
			// Например: "Служил Вл. Михайлов в 14-м полку." - одно предложение, которое
			// будет ошибочно поделено на 2. Нейросеть здесь писать не будем. :)

			String[] sentences = paragraphs[x].split("(?<=[.!?])\\s+");
			text[x] = new String[sentences.length][];

			for (int y = 0; y < sentences.length; y++) {

				// Делим предложение на слова. В качестве разделителя используются пробелы,
				// граница слова после знака препинания, граница слова до знака препинания.

				String[] words = sentences[y].split("(\\s+)|((?=\\p{Punct})\\b)|((?<=\\p{Punct})\\b)");
				text[x][y] = new String[words.length];

				for (int z = 0; z < words.length; z++) {
					text[x][y][z] = words[z];
				}
			}
		}
	}

	/**
	 * Выводит на консоль текущий вариант текста, собирая его из массива лексем.
	 */
	private static void printCurrentText() {

		if (text == null || text.length == 0) {
			System.out.println("Текст ещё не загружен. Используйте функцию {1}");
			return;
		}

		System.out.println("=====");

		for (String[][] paragraph : text) {
			for (int i = 0; i < paragraph.length; i++) {

				String[] sentence = paragraph[i];

				// Перед каждым предложением в абзаце, кроме первого, нужно добавить пробел.
				if (i != 0) {
					System.out.print(" ");
				}

				for (int j = 0; j < sentence.length; j++) {
					System.out.print(sentence[j]);

					// После каждого элемента нужно добавить пробел, кроме
					// последнего значения и случаев со знаками препинания.
					if (j + 1 < sentence.length && !isPunct(sentence[j + 1])) {
						System.out.print(" ");
					}
				}
			}

			// Каждый абзац завершается символом конца строки.
			System.out.print("\n");
		}

		// Отделяем весь текст от дальнейшего вывода.
		System.out.println("=====");
	}

	/**
	 * Сортировка абзацев по количеству предложений.
	 */
	private static boolean sortParagraphs() {

		if (text == null || text.length == 0) {
			System.out.println("Текст ещё не загружен. Используйте функцию {1}");
			return false;
		}

		String[][] tmpParagraph;

		for (int i = 1; i < text.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (text[j + 1].length < text[j].length) {
					tmpParagraph = text[j];
					text[j] = text[j + 1];
					text[j + 1] = tmpParagraph;
				}
			}
		}

		return true;
	}

	/**
	 * Сортирует слова по длине в каждом предложении.
	 * 
	 * @return
	 */
	private static boolean sortWordsByLength() {

		if (text == null || text.length == 0) {
			System.out.println("Текст ещё не загружен. Используйте функцию {1}");
			return false;
		}

		String tmpWord;

		// Цикл по абзацам и предложениям.
		for (String[][] paragraph : text) {
			for (String[] sentence : paragraph) {

				for (int i = 0; i < sentence.length; i++) {
					if (isPunct(sentence[i])) {
						continue; // Пропускаем знаки препинания.
					}

					int x = i;
					for (int j = i - 1; j >= 0; j--) {
						if (isPunct(sentence[j])) {
							continue; // Пропускаем знаки препинания.
						}

						if (sentence[x].length() < sentence[j].length()) {
							tmpWord = sentence[j];
							sentence[j] = sentence[x];
							sentence[x] = tmpWord;
							x = j;
						}
					}
				}

			}
		}

		return true;
	}

	/**
	 * Сортирует лексемы (слова и знаки препинания) в предложениях по убыванию
	 * количества вхождений заданного символа, а в случае равенства – по алфавиту.
	 * 
	 * @return
	 */
	private static boolean sortLexems() {
		if (text == null || text.length == 0) {
			System.out.println("Текст ещё не загружен. Используйте функцию {1}");
			return false;
		}

		char ch = getChar();

		// Цикл по абзацам и предложениям.
		for (String[][] paragraph : text) {
			for (String[] sentence : paragraph) {

				String tmp;

				// Сортировка по Шеллу.
				for (int i = 0; i < sentence.length; i++) {
					for (int j = i - 1; j >= 0; j--) {

						int cntA = cntSymbol(sentence[j], ch);
						int cntB = cntSymbol(sentence[j + 1], ch);

						if (cntA < cntB) {
							// Сортировка по убыванию количества вхождений.
							tmp = sentence[j];
							sentence[j] = sentence[j + 1];
							sentence[j + 1] = tmp;

						} else if (cntA == cntB) {
							// Сортировка по алфавиту.
							if (sentence[j].compareTo(sentence[j + 1]) > 0) {
								tmp = sentence[j];
								sentence[j] = sentence[j + 1];
								sentence[j + 1] = tmp;
							}
						}
					}
				}

			}
		}

		return true;
	}

	/**
	 * Проверяет строку на содержание в ней знаков препинания.
	 * 
	 * @param str - исходная проверяемая строка.
	 * @return
	 */
	private static boolean isPunct(String str) {
		Pattern pattern = Pattern.compile("\\p{Punct}");
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	/**
	 * Подсчитывает количество указанных символов в исходной строке.
	 * 
	 * @param str - строка, в которой ищем вхождения.
	 * @param ch  - символ, который ищем.
	 * @return
	 */
	private static int cntSymbol(String str, char ch) {
		int cnt = 0;

		if (str == null || str.length() == 0) {
			return cnt;
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				cnt++;
			}
		}

		return cnt;
	}
}
