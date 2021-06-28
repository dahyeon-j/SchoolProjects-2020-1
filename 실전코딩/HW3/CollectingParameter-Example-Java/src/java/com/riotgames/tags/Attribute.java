package com.riotgames.tags;

public class Attribute {
	

	private String name;
	private String value;

	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	void toString(String name, String value, TagNode tagNode) {
		tagNode.attributes += (" " + name + "='" + value + "'");
	}

}
