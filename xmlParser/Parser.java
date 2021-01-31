package by.epam.training.xmlParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Парсер XML документов.
 * 
 * @author R. Kuzmin
 *
 */
public class Parser {

	private String text;
	private Element element;
	private int point;

	public Parser() {
		this("");
	}

	public Parser(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Возвращает текущий разобранный элемент xml.
	 *  
	 * @return
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * Возвращает парсер в начало текста.
	 */
	public void reset() {
		point = 0;
	}
	
	/**
	 * Считывает очередной элемент из текста, разбирает его и сохраняет в element.
	 * 
	 * @return
	 */
	public boolean getNext() {

		element = new Element();

		Pattern pattern = Pattern.compile("(?<tag></?(?<name>\\w+)\\s?(?<attr>[^>]*?)>)");
		Matcher matcher = pattern.matcher(text);

		matcher.region(point, text.length());

		if (matcher.find()) {

			// Сдвигаем указатель на позицию после закрывающего ">" найденного тега.
			point = matcher.end("tag");

			String tag = matcher.group("tag");
			String attr = matcher.group("attr");
			String name = matcher.group("name");

			String type = "";
			if (tag.charAt(tag.length() - 2) == '/') {
				type = "empty";
			} else if (tag.charAt(1) == '/') {
				type = "covering";
			} else {
				type = "opening";
			}

			element.setType(type);
			element.setName(name);

			if (type == "opening") {

				// Атрибуты могут быть только у открывающего элемента.
				element.setAttributes(attr);
				
				// Граница контента текущего узла.
				int end = text.indexOf("</" + name, point);
				
				if (point < end) {
					String content = text.substring(point, end);
					element.setContent(content);
				}
			}

		} else {
			return false;
		}

		return true;
	}

}
