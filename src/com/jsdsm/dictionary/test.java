package com.jsdsm.dictionary;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException{
		System.out.println("-----------��������----------------");
		 boolean status = IndexDictionary.createIndex();
		if (status) {
			System.out.println("-----------��������----------------");
		}else {
			System.out.println("-----------����ʧ��----------------");
			return;
		}
		DictionaryTrie trie = DictionaryTrie.getInstance();
		System.out.println(trie.prefix("A").toString());
        System.out.println(trie.prefix("�л�").toString());
        System.out.println(trie.prefix("��").toString());
        System.out.println(trie.prefix("����").toString());
//		String word = "�������İ� ����java";
//		LinkedList<String> data = new ReverseMatch().startReverseMatch(word);
//		System.out.println(data.toString());
////		int count = SearchDictionary.searchDictionary(word);
////		System.out.println("count =="+count);
	}
}
