package br.com.pessoa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/carrosEmGSON");
	private static final Pattern regexById = Pattern.compile("/carrosEmGSON/([0-9]*)");
		
	/*
	 * Verifica se a URL segue o padrão "/carros/id".	
	 */
	public static Long matchId(String requestUri) throws Exception {
		Matcher matcher = regexById.matcher(requestUri);
		if(matcher.find() && matcher.groupCount() > 0) {
			String result = matcher.group(1);
			if(result != null && result.trim().length() > 0) {
				Long id = Long.parseLong(result);
				return id;
			}
		}
		throw new Exception("Houve um erro ao identificar o id"); 
	}
	
	/*
	 * Verifica se a URL segue o padrão "/xarros/id".	
	 */
	public boolean matchAll(String requestUri) {
		return regexAll.matcher(requestUri).find();
	}

}
