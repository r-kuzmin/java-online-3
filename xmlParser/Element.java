package by.epam.training.xmlParser;

public class Element {

	private String attributes;
	private String content;
	private String name;
	private String type;

	public Element() {}

	public String getAttributes() {
		return attributes;
	}

	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

}
