package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.mjc.MethodSignature.Argument;

public class MethodParser {

	/**
	 * Parses string that represents a method signature and stores all it's members
	 * into a {@link MethodSignature} object. signatureString is a java-like method
	 * signature with following parts: 1. access modifier - optional, followed by
	 * space: ' ' 2. return type - followed by space: ' ' 3. method name 4.
	 * arguments - surrounded with braces: '()' and separated by commas: ',' Each
	 * argument consists of argument type and argument name, separated by space: '
	 * '. Examples: accessModifier returnType methodName(argumentType1
	 * argumentName1, argumentType2 argumentName2) private void log(String value)
	 * Vector3 distort(int x, int y, int z, float magnitude) public DateTime
	 * getCurrentDateTime()
	 *
	 * @param signatureString source string to parse
	 * @return {@link MethodSignature} object filled with parsed values from source
	 *         string
	 */

	private List<String> regexBeforeOpenBracket = Arrays.asList("\\(.+");
	private List<String> regexAfterOpenBracket = Arrays.asList(".+\\(|, |\\)");
	private String WHITESPACE_REGEX = " ";
	private final int ELEMENTS_BEFORE_OPEN_BRACKET = 3;

	private StringSplitter stringSplitter = new StringSplitter();

	public MethodSignature parseFunction(String signatureString) {
		// ["public Vector3 distort"]
		List<String> dataBeforeOpenBracket = stringSplitter.splitByDelimiters(signatureString, regexBeforeOpenBracket);
		// ["int x", "int y", "int z", "float magnitude"]
		List<String> dataAfterOpenBracket = stringSplitter.splitByDelimiters(signatureString, regexAfterOpenBracket);
		
		String[] dataBefore = getDataBeforeOpenBracket(dataBeforeOpenBracket);
		List<Argument> dataAfter = getArgumentsList(dataAfterOpenBracket);
		
		return initMethodSignatureObject(dataBefore, dataAfter);

	}
	
	private MethodSignature initMethodSignatureObject(String[] dataBefore, List<Argument> dataAfter) {
		if (dataBefore.length == ELEMENTS_BEFORE_OPEN_BRACKET) {
			return new MethodSignature(dataBefore[0], dataBefore[1], dataBefore[2], dataAfter);
		}else {
			return new MethodSignature(dataBefore[0], dataBefore[1], dataAfter);
		}
	}

	private List<Argument> getArgumentsList(List<String> dataAfterOpenBracket) {
		List<Argument> arguments = new ArrayList<>();
		if (dataAfterOpenBracket.size() != 0) {
			for (int i = 0; i < dataAfterOpenBracket.size(); i++) {
				String[] splittedData = dataAfterOpenBracket.get(i).split(WHITESPACE_REGEX);
				arguments.add(new Argument(splittedData[0], splittedData[1]));
			}
		}
		return arguments;
	}

	private String[] getDataBeforeOpenBracket(List<String> dataBeforeOpenBracket) {
		return dataBeforeOpenBracket.get(0).split(WHITESPACE_REGEX);
	}

}
