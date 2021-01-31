package by.epam.training.xmlParser;

/**
 * Дана строка, содержащая следующий текст (xml-документ). Напишите анализатор,
 * позволяющий последовательно возвращать содержимое узлов xml-документа и его
 * тип (открывающий тег, закрывающий тег, содержимое тега, тег без тела).
 * Пользоваться готовыми парсерами XML для решения данной задачи нельзя.
 * 
 * @author R. Kuzmin
 *
 */
public class Main {

	public static void main(String[] args) {
		
		String src = loadSourceText();
		
		Parser parser = new Parser(src);
		
		while (parser.getNext()) {
			Element element = parser.getElement();
			
			System.out.println("Tag: " + element.getName() + " (" + element.getType() + ")");
			
			String attr = element.getAttributes();
			if (attr != null && !attr.isBlank()) {
				System.out.println("\tAttributes: " + attr);
			}
			
			String content = element.getContent();
			if (content != null && !content.isBlank()) {
				System.out.println("\tContent:\n " + content + "\n");
			}
		}
		
		parser.reset();
		
	}
	
	/**
	 * Загрузка исходного xml-текста.
	 * 
	 * @return
	 */
	private static String loadSourceText() {
		
		String source = String.join("\n",
				"<notes bk = \"32\" ow = \"8\">",
				"  <note id = \"1\">",
				"    <to>Вася</to>",
				"    <from>Света</from>",
				"    <heading>Напоминание</heading>",
				"    <body>Позвони мне завтра!</body>",
				"  </note>",
				"  <note id = \"2\">",
				"    <to>Петя</to>",
				"    <from>Маша</from>",
				"    <heading>Важное напоминание</heading>",
				"    <body/>",
				"  </note>",
				"</notes>");
		
		return source;
	}
	
}
