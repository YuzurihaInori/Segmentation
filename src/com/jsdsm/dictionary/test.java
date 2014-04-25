package com.jsdsm.dictionary;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.store.LockObtainFailedException;

import com.jsdsm.algorithm.ReverseMatch;

public class test {

	public static void main(String[] args) throws CorruptIndexException, LockObtainFailedException, IOException, ParseException {
		System.out.println("-----------建立索引----------------");
		boolean status = IndexDictionary.createIndex();
		if (status) {
			System.out.println("-----------索引结束----------------");
		}else {
			System.out.println("-----------索引失败----------------");
			return;
		}
		String word = "我们。聊聊吧，好吗";
		LinkedList<String> data = new ReverseMatch().startReverseMatch(word);
		System.out.println(data.toString());
//		int count = SearchDictionary.searchDictionary(word);
//		System.out.println("count =="+count);
	}
}
