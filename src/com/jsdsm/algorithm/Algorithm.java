package com.jsdsm.algorithm;

import java.util.ArrayList;

/**
 * �ִ��㷨������
 * @author jisi
 *
 */
public class Algorithm {
	
	public String textProcessing(String stageText){
		String text= stageProcessing(stageText);
		System.out.println(text);
		return text;
	}
	
	/**
	 * ����ȥ������
	 * @param stageText
	 * @return	�����ı�
	 */
	private String stageProcessing(String stageText){		//�滻�����ڵı�����Ϊ|
//		String s = "����!,@#W������";	//TEST
		stageText = stageText.replaceAll("\\pP", "|");
		return stageText;
		}
}
