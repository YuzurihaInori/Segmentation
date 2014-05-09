package com.jsdsm.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jsdsm.dictionary.IndexDictionary;
import com.jsdsm.tool.RecognitionTool;

/**
 * ���ڴʵ���������ƥ���㷨
 */
public class MaximumMatching extends AbstractSegmentation{
    @Override
    public List<Word> segImpl(String text) {
        List<Word> result = new ArrayList<>();
        //�ı�����
        final int textLen=text.length();
        //��δ�ִʵ��ı��н�ȡ�ĳ���
        int len=getInterceptLength();
        if (len == 0) {
            System.out.println("len===="+"null");
            return null;
        }
        //ʣ��δ�ִʵ��ı�������
        int start=0;
        //ֻҪ�д�δ�з����һֱ����
        while(start<textLen){
            if(len>textLen-start){
                //���δ�ִʵ��ı��ĳ���С�ڽ�ȡ�ĳ���
                //�����̽�ȡ�ĳ���
                len=textLen-start;
            }
            //�ó�Ϊlen���ַ�����ʵ䣬�����������ʶ��
            while(!DIC.contains(text, start, len) && !RecognitionTool.recog(text, start, len)){
                //�������Ϊһ���ڴʵ���δ�ҵ�ƥ��
                //�򰴳���Ϊһ�з�
                if(len==1){
                    break;
                }
                //����鲻�����򳤶ȼ�һ�����
                len--;
            }
            addWord(result, text, start, len);
            //�Ӵ��ִ��ı�������ƶ������������Ѿ��ִʵ��ı�
            start+=len;
            //ÿһ�γɹ��дʺ�Ҫ���ý�ȡ����
            len=getInterceptLength();
        }
        return result;
    }    
    public static void main(String[] args){
        try {
            IndexDictionary.createIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = "��ʮ�־��ȵ�˵��������ԭ�����������д����ܼ���������̫���ˣ����и�Nutch��������������أ���";
        if(args !=null && args.length == 1){
            text = args[0];
        }
        MaximumMatching m = new MaximumMatching();
        System.out.println(m.seg(text).toString());
    }
}
