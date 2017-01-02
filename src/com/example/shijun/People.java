package com.example.shijun;

import java.util.ArrayList;
import java.util.List;

public class People {
	public String name;
	public List<String> cards;

	public People(String name) {
		this.name = name;
		this.cards = new ArrayList<String>();
	}
}
