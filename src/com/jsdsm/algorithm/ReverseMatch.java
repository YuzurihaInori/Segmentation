package com.jsdsm.algorithm;

import java.util.LinkedList;

import com.jsdsm.dictionary.SearchDictionary;

/**
 *����ƥ���㷨�� {@link Algorithm}}
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
				temp = this.input.substring(i); // ÿ�δ��ַ������ײ���ȡһ���֣����浽temp��
				// System.out.println("*****" + temp + "*********" +
				// this.input);
				// ����ô����ֵ��У� ��ɾ���ôʲ���ԭʼ�ַ����н�ȡ�ô�
				if (SearchDictionary.searchDictionary(temp) > 0) {
					System.out.println("suc=" + temp);
					this.input = this.input.replace(temp, "");
					i = -1; // i=-1����ΪҪ���²��ң� ��Ҫ��ִ��ѭ���е�i++
					ReverseMatch.tempList.addFirst("/" + temp);
				} else {
					System.out.println("fal=" + temp);
				}
				if ((input.length() - i) == 1) {
					ReverseMatch.tempList.addFirst("/" + temp);
				}
			}

			// ��ǰѭ����ϣ��ʵ�ĩβ��ȥһ���֣�����ѭ���� ֱ���ʱ�Ϊ��
			if (null != this.input && !"".equals(this.input)) {
				this.input = this.input.substring(0, this.input.length() - 1);
				this.start();
			}
		}
	}
}
