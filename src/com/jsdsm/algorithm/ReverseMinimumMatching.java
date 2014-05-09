package com.jsdsm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jsdsm.tool.RecognitionTool;

/**
 * ���ڴʵ��������Сƥ���㷨
 * Dictionary-based reverse minimum matching algorithm
 */
public class ReverseMinimumMatching extends AbstractSegmentation{
    @Override
    public List<Word> segImpl(String text) {
        Stack<Word> result = new Stack<>();
        //�ı�����
        final int textLen=text.length();
        //��δ�ִʵ��ı��н�ȡ�ĳ���
        int len=1;
        //ʣ��δ�ִʵ��ı�������
        int start=textLen-len;
        //ֻҪ�д�δ�з����һֱ����
        while(start>=0){
            //�ó�Ϊlen���ַ�����ʵ�
            while(!DIC.contains(text, start, len) && !RecognitionTool.recog(text, start, len)){
                //����鲻�����򳤶ȼ�һ�����
                //������ǰ�ƶ�һ���֣�Ȼ�����
                len++;
                start--;
                //�������Ϊ�ʵ���󳤶����ڴʵ���δ�ҵ�ƥ��
                //���Ѿ�������ʣ�µ��ı����ڴʵ���δ�ҵ�ƥ��
                //�򰴳���Ϊһ�з�
                if(len>getInterceptLength() || start<0){
                    //���ý�ȡ����Ϊһ
                    //����ƶ�start����
                    start+=len-1;
                    len=1;
                    break;
                }
            }
            addWord(result, text, start, len);
            //ÿһ�γɹ��дʺ�Ҫ���ÿ�ʼ����λ��
            start--;
            //ÿһ�γɹ��дʺ�Ҫ���ý�ȡ����
            len=1;
        }
        len=result.size();
        List<Word> list = new ArrayList<>(len);
        for(int i=0;i<len;i++){
            list.add(result.pop());
        }
        return list;        
    }
    public static void main(String[] args){
        String text = "���������߶�ʮһ�������Ƕ����壬�Ҿ������ˣ����Ǹ��������������ң�������ʦ������������ʲô��˼����";
        if(args !=null && args.length == 1){
            text = args[0];
        }
        ReverseMinimumMatching m = new ReverseMinimumMatching();
        System.out.println(m.seg(text).toString());
    }
}
