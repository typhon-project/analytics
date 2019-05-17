package com.alphabank.typhon;

public class UniCodeString {

	public static void main(String[] args) {
		StringBuffer b = new StringBuffer(  );
		String oaa = "OAA";
		 b.append('\u00A5') ;
		 b.append('\u0037') ;
		 b.append('\u0391') ;
		 b.append('\u039F') ;
		 
		 
		System.out.println(oaa);
		System.out.println(b);

		
		StringBuffer greekOAA = new StringBuffer(  );
		greekOAA.append('\u039F') ;
		greekOAA.append('\u0391') ;
		greekOAA.append('\u0391') ;
		

		System.out.println(greekOAA);
	}

}
