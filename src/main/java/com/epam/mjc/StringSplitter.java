package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
    	
    	List<String> strings = new ArrayList<>();
		List<String> delims = new ArrayList<>(delimiters);

		String[] splitted = source.split(delims.get(0));
		for (int i = 0; i < splitted.length; i++) {
			strings.add(splitted[i]);
		}

		if (strings.contains("")) {
			strings.remove("");
		}
		
		return strings;
    }
}
