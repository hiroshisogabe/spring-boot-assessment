package br.com.sogabe.assessment.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BracketService {

	public void check(String brackets) {
		Map<String, String> bracketsMap = new HashMap<>();
		bracketsMap.put("]", "[");
		bracketsMap.put(")", "(");
		bracketsMap.put("}", "{");
		
		List<String> lifo = new LinkedList<String>();
		
		for (int i = 0; i < brackets.length(); i++){
		    String c = Character.toString(brackets.charAt(i));
		    
		    if (bracketsMap.containsValue(c)) {
		    	lifo.add(c);
		    }
		    else if (bracketsMap.containsKey(c)) {

		    	Optional<String> result = bracketsMap.entrySet()
	    		    .stream()
	    		    .filter(map -> c.equals(map.getKey()))
	    		    .map(map -> map.getValue())
	    		    .findFirst();

	    		if (result.isPresent()) {

	    			if (lifo.get(lifo.size() - 1).equals(result.get())) {
			    		lifo.remove(lifo.size() - 1);
			    	}
	    			else {
	    				throw new RuntimeException("Expected bracket "+result.get()+" but got "+lifo.get(lifo.size() - 1));
		    		}
	    		}
		    }
		    else {
		    	throw new RuntimeException("Undefined bracket "+c);
    		}
		}
	}
	
}
