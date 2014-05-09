package com.jsdsm.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.jsdsm.tool.RecognitionTool;

/**
 * ���ڴʵ��������Сƥ���㷨
 * Dictionary-based minimum matching algorithm
 * @author ���д�
 */
public class MinimumMatching extends AbstractSegmentation{
    @Override
    public List<Word> segImpl(String text) {
        List<Word> result = new ArrayList<>();
        //�ı�����
        final int textLen=text.length();
        //��δ�ִʵ��ı��н�ȡ�ĳ���
        int len=1;
        //ʣ��δ�ִʵ��ı�������
        int start=0;
        //ֻҪ�д�δ�з����һֱ����
        while(start<textLen){
            //�ó�Ϊlen���ַ�����ʵ䣬�����������ʶ��
            while(!DIC.contains(text, start, len) && !RecognitionTool.recog(text, start, len)){
                //�������Ϊ�ʵ���󳤶����ڴʵ���δ�ҵ�ƥ��
                //���Ѿ�������ʣ�µ��ı����ڴʵ���δ�ҵ�ƥ��
                //�򰴳���Ϊһ�з�
                if(len==getInterceptLength() || len==textLen-start){
                    //���ý�ȡ����Ϊһ
                    len=1;
                    break;
                }
                //����鲻�����򳤶ȼ�һ�����
                len++;
            }
            addWord(result, text, start, len);
            //�Ӵ��ִ��ı�������ƶ������������Ѿ��ִʵ��ı�
            start+=len;
            //ÿһ�γɹ��дʺ�Ҫ���ý�ȡ����
            len=1;
        }
        return result;
    }
    public static void main(String[] args){
        String text = "����¥�Ρ��������ǲ�ѩ�ۡ���������һƪ³Ѹ�ġ��Ӱٲ�԰����ζ���ݡ������������ڡ������ձ����Ϸ����ˡ����Ϸ���һ�����й����ġ��������й����ˡ������ʡ�������1940��2��7�ա����д���APDPlatӦ�ü���Ʒ����ƽ̨������";
        if(args !=null && args.length == 1){
            text = args[0];
        }
        MinimumMatching m = new MinimumMatching();
        System.out.println(m.seg(text).toString());
    }
}
