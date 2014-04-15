package com.jsdsm.algorithm;

import java.util.LinkedList;

import com.jsdsm.dictionary.SearchDictionary;

/**
 *逆向匹配算法类 {@link Algorithm}}
 * @author jisi
 *
 */
public class ReverseMatch extends Algorithm {

	public static LinkedList<String> tempList;

	public LinkedList<String> startReverseMatch(String input) {
		tempList = new LinkedList<String>();
		new Split(input).start();
		return tempList;
	}

	class Split {
		private String input = null;

		public Split(String input) {
			this.input = input;
		}

		public void start() {
			String temp = null;
			for (int i = 0; i < this.input.length(); i++) {
				temp = this.input.substring(i); // 每次从字符串的首部截取一个字，并存到temp中
				// System.out.println("*****" + temp + "*********" +
				// this.input);
				// 如果该词在字典中， 则删除该词并在原始字符串中截取该词
				if (SearchDictionary.searchDictionary(temp) > 0) {
					System.out.println("suc=" + temp);
					this.input = this.input.replace(temp, "");
					i = -1; // i=-1是因为要重新查找， 而要先执行循环中的i++
					ReverseMatch.tempList.addFirst("/" + temp);
				} else {
					System.out.println("fal=" + temp);
				}
				if ((input.length() - i) == 1) {
					ReverseMatch.tempList.addFirst("/" + temp);
				}
			}

			// 当前循环完毕，词的末尾截去一个字，继续循环， 直到词变为空
			if (null != this.input && !"".equals(this.input)) {
				this.input = this.input.substring(0, this.input.length() - 1);
				this.start();
			}
		}
	}
}
