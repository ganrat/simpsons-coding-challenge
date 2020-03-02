package com.citi.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class PhrasesVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String characterId;
	
	
	
	//This is the internal List which stored the phrases
	private		ArrayList<String> phrasesList = new ArrayList<String>();

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getPhrasesList() {
		return phrasesList;
	}

	public void setPhrasesList(ArrayList<String> phrasesList) {
		this.phrasesList = phrasesList;
	}

	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}

	
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	}
