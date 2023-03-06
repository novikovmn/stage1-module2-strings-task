package com.epam.mjc;

public class Main {

	public static void main(String[] args) {

		String signatureString = "public Vector3 distort(int x, int y, int z, float magnitude)";
		
		MethodParser methodParser = new MethodParser();
		
		MethodSignature methodSignature = methodParser.parseFunction(signatureString);
		
		System.out.println(methodSignature);


	}


}
