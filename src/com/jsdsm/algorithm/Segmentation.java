package com.jsdsm.algorithm;

import java.util.List;


/**
 * �ִ��㷨������
 * @author jisi
 *
 */
public interface Segmentation {
	
//	public String textProcessing(String stageText){
//		String text= stageProcessing(stageText);
//		System.out.println(text);
//		return text;
//	}
//	
//	/**
//	 * ����ȥ������
//	 * @param stageText
//	 * @return	�����ı�
//	 */
//	private String stageProcessing(String stageText){		//�滻�����ڵı�����Ϊ|
////		String s = "����!,@#W������";	//TEST
//	    stageText = stageText.replaceAll(" ", "");//ȥ�ո�
//		stageText = stageText.replaceAll("\\pP", "|");
//		return stageText;
//		}
    public List<Word> seg(String text);
}
