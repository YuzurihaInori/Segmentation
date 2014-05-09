
package com.jsdsm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jsdsm.tool.RecognitionTool;

/**
 * ���ڴʵ���������ƥ���㷨
 * Dictionary-based reverse maximum matching algorithm
 */
public class ReverseMaximumMatching extends AbstractSegmentation{
    @Override
    public List<Word> segImpl(String text) {
        Stack<Word> result = new Stack<>();
        //�ı�����
        final int textLen=text.length();
        //��δ�ִʵ��ı��н�ȡ�ĳ���
        int len=getInterceptLength();
        //ʣ��δ�ִʵ��ı�������
        int start=textLen-len;
        //�����ı�����С�����ʳ������
        if(start<0){
            start=0;
        }
        if(len>textLen-start){
            //���δ�ִʵ��ı��ĳ���С�ڽ�ȡ�ĳ���
            //�����̽�ȡ�ĳ���
            len=textLen-start;
        }
        //ֻҪ�д�δ�з����һֱ����
        while(start>=0 && len>0){
            //�ó�Ϊlen���ַ�����ʵ䣬�����������ʶ��
            while(!DIC.contains(text, start, len) && !RecognitionTool.recog(text, start, len)){
                //�������Ϊһ���ڴʵ���δ�ҵ�ƥ��
                //�򰴳���Ϊһ�з�
                if(len==1){
                    break;
                }
                //����鲻�����򳤶ȼ�һ
                //��������ƶ�һ���֣�Ȼ�����
                len--;
                start++;
            }
            addWord(result, text, start, len);
            //ÿһ�γɹ��дʺ�Ҫ���ý�ȡ����
            len=getInterceptLength();            
            if(len>start){
                //���δ�ִʵ��ı��ĳ���С�ڽ�ȡ�ĳ���
                //�����̽�ȡ�ĳ���
                len=start;
            }
            //ÿһ�γɹ��дʺ�Ҫ���ÿ�ʼ����λ��
            //�Ӵ��ִ��ı�����ǰ�ƶ����ʳ�������
            //��δ�ִʵ��ı������´ηִʵķ�Χ
            start-=len;
        }
        len=result.size();
        List<Word> list = new ArrayList<>(len);
        for(int i=0;i<len;i++){
            list.add(result.pop());
        }
        return list;        
    }        
    public static void main(String[] args){
        String text = "'��������Ĵ�ʥ��'�������ģʽ��������ģʽ�������ع��������������ޱ�̡������С����ģʽ���͡��ع����ų�'��˫��'��";
        if(args !=null && args.length == 1){
            text = args[0];
        }
        ReverseMaximumMatching m = new ReverseMaximumMatching();
        System.out.println(m.seg(text).toString());
    }
}
