package com.jsdsm.algorithm;

import java.util.List;


/**
 * 分词算法管理类
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
//	 * 段落去标点符号
//	 * @param stageText
//	 * @return	段落文本
//	 */
//	private String stageProcessing(String stageText){		//替换段落内的标点符号为|
////		String s = "哈哈!,@#W。“”";	//TEST
//	    stageText = stageText.replaceAll(" ", "");//去空格
//		stageText = stageText.replaceAll("\\pP", "|");
//		return stageText;
//		}
    public List<Word> seg(String text);
}
