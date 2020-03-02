package com.citi.service;
 

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.citi.vo.CharactersVO;
import com.citi.vo.PhrasesVO;

import net.openhft.chronicle.map.ChronicleMap;

@RestController
@RequestMapping("/")
//@EnableWebMvc 
//@EnableAutoConfiguration
public class CharacterService {
	
	private static Logger logger = Logger.getLogger(CharacterService.class);
	
	ChronicleMap<String, CharactersVO> charsMap = ChronicleMap
			  .of(String.class, CharactersVO.class)
			  .name("chacters-map1")
			  .entries(50)  
			  .averageKey("568efd448eefb265421")
			  .averageValueSize(10)
			  .create();
	
	//This is the internal map which will store the phrases id and phrases
		//private		ChronicleMap<String, String> phrasesInternalMap;
	
	//Phrases List to store the phrases 
	
	ArrayList<String> phrasesList;
	
	
	ChronicleMap<String, PhrasesVO> phrasesMap = ChronicleMap
			  .of(String.class, PhrasesVO.class)
			  .name("phrases-map")
			  .entries(50)  
			  .averageKey("59edff6492d619b4a933a56b")
			  .averageValueSize(10)
			  .create();
	
	
	
			
	
	public CharacterService() {
		JSONParser jsonParser = new JSONParser();		
		
		try (FileReader reader = new FileReader("characters.json"))
		{
			//Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONObject inJSONObj =  (JSONObject) obj;
            //JSONArray employeeList = (JSONArray) obj;
            JSONArray jsArray = (JSONArray)inJSONObj.get("data");
            for(int i=0;i<jsArray.size();i++) {
            	JSONObject jsObj = (JSONObject) jsArray.get(i);
            	
            	String fName = (String)jsObj.get("firstName");            	
            	CharactersVO charsVO = new CharactersVO();            	
            	charsVO.setFirstName(fName);            	
            	String id = (String)jsObj.get("_id");            	
            	charsVO.setId(id);
            	String picture = (String)jsObj.get("picture");
            	charsVO.setPicture(picture);
            	
            	int age = Integer.parseInt(String.valueOf(jsObj.get("age")));
            	logger.info("---Age---"+age);
            	charsVO.setAge(age);
           
            	charsMap.put(fName, charsVO);
            }
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		 
		logger.info("---Data inserted into Character Chronicle Map successfully----");
		
		try(FileReader reader = new FileReader("Phrases.json")){
			Object obj = jsonParser.parse(reader);
			
			JSONObject inJSONObj = (JSONObject) obj;
			JSONArray jsArray = (JSONArray) inJSONObj.get("data");
			
			for(int j=0;j<jsArray.size();j++) {
				JSONObject jsonObj = (JSONObject) jsArray.get(j);
				
				PhrasesVO phrasesVO;
				
				String id = (String)jsonObj.get("_id");
				
				//phrasesVO.setId(id);
				
				String characterId = (String) jsonObj.get("character");
				
				//phrasesVO.setCharacterId(characterId);
				
				String phrase = new String(jsonObj.get("phrase").toString().getBytes("ISO-8859-1"),"UTF-8");
				
				
				
				if(phrasesMap.containsKey(characterId)) {
					phrasesVO = phrasesMap.get(characterId);
					phrasesList = phrasesVO.getPhrasesList();
					phrasesList.add(phrase);	
					phrasesVO.setPhrasesList(phrasesList);
				}else {
					phrasesList = new ArrayList<String>();
					phrasesList.add(phrase.concat("<br><br>"));
					
					phrasesVO = new PhrasesVO();
					phrasesVO.setId(id);
					phrasesVO.setCharacterId(characterId);
					phrasesVO.setPhrasesList(phrasesList);					
				}				
				
				phrasesMap.put(characterId, phrasesVO);				
        	}
			
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		logger.info("---Data inserted into Phrases Chronicle Map successfully----");
		//charsMap.close();
	} 
	
	
	@CrossOrigin
	@RequestMapping("/getphrase")
	//public String welcome(ModelMap map) {
	public ArrayList<String> getphrase(@RequestParam(value = "chardetail", defaultValue = "User") String name) {
		
		
		logger.info("Executing getPhrase method");
		logger.info("Character name :"+name);	
		
		String picture;
		ArrayList<String> phrasesList = null;
		CharactersVO charsVO = null;
		if(charsMap!=null) {
			charsVO = charsMap.get(name);		
		}
		
		if(charsVO!=null) {			
			picture = charsVO.getPicture();
			String characterId = charsVO.getId(); // This is id in Character.json 
			
			PhrasesVO phrasesVO = phrasesMap.get(characterId);
			if(phrasesVO!=null) {
				phrasesList = phrasesVO.getPhrasesList();
				phrasesList.stream().forEach(e->logger.info(e));
			}
		}
		else {
			logger.info("No Data Found for the Character"+name);
		}
		return phrasesList;
	}

	
	@CrossOrigin
	@RequestMapping("/getallcharacters")
	public List<CharactersVO> getAllCharacters(){
		
		List<CharactersVO> charsVOList = new ArrayList<CharactersVO>();
		if(charsMap!=null) {
			charsMap.forEach((k,v)->charsVOList.add(v));
			
		}
		return charsVOList;
	}
	
	
	@CrossOrigin
	@RequestMapping("/deletecharacters")
	public String deleteCharacter(@RequestParam(value = "chardetail", defaultValue = "User") String name){
		String deletionStatus="Failure";
		logger.info("---Executing welcome method in Welcome Controller-----");
		logger.info("----Character name-----"+name);	
		if(charsMap!=null) {
			charsMap.remove(name);
			deletionStatus = "Success";
		}
		logger.info("deletionStatus:"+deletionStatus);
		
		return deletionStatus;						
	}
	
	
		
	
	
	
	
}