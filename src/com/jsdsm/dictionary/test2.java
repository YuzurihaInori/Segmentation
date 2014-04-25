package com.jsdsm.dictionary;

public class test2 {
	public static void main(String[] args) {
		String s = "¹þ¹þ!,@#W¡£¡°¡±";
		s = s.replaceAll("\\pP", "|");
		System.out.print(s);
		}
}
