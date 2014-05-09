package com.jsdsm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jsdsm.dictionary.Dictionary;
import com.jsdsm.dictionary.DictionaryTrie;
import com.jsdsm.tool.Punctuation;
/**
 * �ִ��㷨������
 */
public abstract class AbstractSegmentation  implements Segmentation{
    protected static final Dictionary DIC = (Dictionary)DictionaryTrie.getInstance();
    private static final boolean KEEP_WHITESPACE = false;
    public abstract List<Word> segImpl(String text);
    /**
     * �ִ�ʱ��ȡ���ַ�������󳤶�
     * @return 
     */
    public int getInterceptLength(){
         return DIC.getMaxLength();
    }
    /**
     * Ĭ�Ϸִ��㷨ʵ�֣�
     * 1����Ҫ�ִʵ��ı����ݱ����Ž��зָ�
     * 2���Էָ����ı����зִ�
     * 3����Ϸִʽ��
     * @param text �ı�
     * @return �ִʽ��
     */
    @Override
    public List<Word> seg(String text) {
        List<Word> result = new ArrayList<>();
        List<String> sentences = Punctuation.seg(text, false);
        for(String sentence : sentences){
            if(sentence.length() == 1){
                if(KEEP_WHITESPACE){  //�������ո�
                    result.add(new Word(sentence));
                }else{
                    if(!isWhiteSpace(sentence.charAt(0))){
                        result.add(new Word(sentence));
                    }
                }
            }
            if(sentence.length() > 1){
                List<Word> list = segImpl(sentence);
                if(list != null){
//                    if(PERSON_NAME_RECOGNIZE){
//                        list = PersonName.recognize(list);
//                    }
                    result.addAll(list);
                }else{
                    System.out.println("�ı� "+sentence+" û�л�÷ִʽ��");
                }
            }
        }
        sentences.clear();
        return result;
    }
    /**
     * ��ʶ����Ĵʷ������
     * @param result ����
     * @param text �ı�
     * @param start �ʿ�ʼ����
     * @param len �ʳ���
     */
    protected void addWord(List<Word> result, String text, int start, int len){
        Word word = getWord(text, start, len);
        if(word != null){
            result.add(word);
        }
    }
    /**
     * ��ʶ����Ĵ���ջ
     * @param result ջ
     * @param text �ı�
     * @param start �ʿ�ʼ����
     * @param len �ʳ���
     */
    protected void addWord(Stack<Word> result, String text, int start, int len){
        Word word = getWord(text, start, len);
        if(word != null){
            result.push(word);
        }
    }    
    /**
     * ��ȡһ���Ѿ�ʶ��Ĵ�
     * @param text �ı�
     * @param start �ʿ�ʼ����
     * @param len �ʳ���
     * @return �ʻ��
     */
    protected Word getWord(String text, int start, int len){
        Word word = new Word(text.substring(start, start+len).toLowerCase());
        //����������Ż�
        if(KEEP_WHITESPACE ){
            //�����հ��ַ�
            return word;
        }else{
            //���Կհ��ַ����������ո�ȫ�ǿո�\t��\n                
            if(len > 1){
                //���ȴ���1�������ǿհ��ַ�
                return word;
            }else{
                //����Ϊ1��ֻҪ�ǿհ��ַ�
                if(!(isWhiteSpace(text, start, len))){
                    //���ǿհ��ַ�������
                    return word;           
                }
            }
        }
        return null;
    }
    /**
     * �ж������±�Ϊstart���ַ��Ƿ�Ϊ�հ��ַ�
     * �������ֻ��������
     * Ϊ���ٶȣ�����������±��Ƿ�Խ��
     * @param text �ı�
     * @param start �����±�
     * @param len ����
     * @return �Ƿ�
     */
    protected boolean isWhiteSpace(String text, int start, int len){
        return isWhiteSpace(text.charAt(start));
    }
    /**
     * �ж�ָ�����ַ��Ƿ��ǿհ��ַ�
     * @param c �ַ�
     * @return �Ƿ��ǿհ��ַ�
     */
    protected boolean isWhiteSpace(char c){
        return c == ' ' || c == '��' || c == '\t' || c == '\n';
    }
    public static void main(String[] args){

    }
}
