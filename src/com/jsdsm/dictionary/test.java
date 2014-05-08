package com.jsdsm.dictionary;

import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException{
		System.out.println("-----------建立索引----------------");
		 boolean status = IndexDictionary.createIndex();
		if (status) {
			System.out.println("-----------索引结束----------------");
		}else {
			System.out.println("-----------索引失败----------------");
			return;
		}
		DictionaryTrie trie = DictionaryTrie.getInstance();
		System.out.println(trie.prefix("A").toString());
        System.out.println(trie.prefix("中华").toString());
        System.out.println(trie.prefix("杨").toString());
        System.out.println(trie.prefix("杨尚").toString());
//		String word = "我们聊聊吧 好吗，java";
//		LinkedList<String> data = new ReverseMatch().startReverseMatch(word);
//		System.out.println(data.toString());
////		int count = SearchDictionary.searchDictionary(word);
////		System.out.println("count =="+count);
	}
}
