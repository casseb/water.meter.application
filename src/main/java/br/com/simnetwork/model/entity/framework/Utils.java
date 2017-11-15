package br.com.simnetwork.model.entity.framework;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

public class Utils {

	public static boolean someNullObject(Object... objects) {
		for (Object object : objects) {
			if(object == null) {
				return true;
			}
		}
		return false;
	}
	
	
	public static int biggerString(List<String> strings) {
		int result = 0;
		for (String string : strings) {
			if (string.length() > result) {
				result = string.length();
			}
		}
		return result;
	}

	public static String firstUpper(String string) {
		string = string.toLowerCase();
		string = StringUtils.capitalize(string);
		return string;
	}
	
	public static List<String> extractLetterFor(String target, String condition) {
		
		if(condition.equals("|")) {
			condition = "\\|";
		}
		
		String[] resultArray = target.split(condition);
		List<String> result = new LinkedList<>();
		
		for (String string : resultArray) {
			result.add(string);
		}
		
		return result;
		
	}
	
}	
