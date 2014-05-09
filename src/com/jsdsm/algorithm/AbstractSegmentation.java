package com.jsdsm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jsdsm.dictionary.Dictionary;
import com.jsdsm.dictionary.DictionaryTrie;
import com.jsdsm.tool.Punctuation;
/**
 * 分词算法抽象类
 */
public abstract class AbstractSegmentation  implements Segmentation{
    protected static final Dictionary DIC = (Dictionary)DictionaryTrie.getInstance();
    private static final boolean KEEP_WHITESPACE = false;
    public abstract List<Word> segImpl(String text);
    /**
     * 分词时截取的字符串的最大长度
     * @return 
     */
    public int getInterceptLength(){
         return DIC.getMaxLength();
    }
    /**
     * 默认分词算法实现：
     * 1、把要分词的文本根据标点符号进行分割
     * 2、对分割后的文本进行分词
     * 3、组合分词结果
     * @param text 文本
     * @return 分词结果
     */
    @Override
    public List<Word> seg(String text) {
        List<Word> result = new ArrayList<>();
        List<String> sentences = Punctuation.seg(text, false);
        for(String sentence : sentences){
            if(sentence.length() == 1){
                if(KEEP_WHITESPACE){  //不保留空格
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
                    System.out.println("文本 "+sentence+" 没有获得分词结果");
                }
            }
        }
        sentences.clear();
        return result;
    }
    /**
     * 将识别出的词放入队列
     * @param result 队列
     * @param text 文本
     * @param start 词开始索引
     * @param len 词长度
     */
    protected void addWord(List<Word> result, String text, int start, int len){
        Word word = getWord(text, start, len);
        if(word != null){
            result.add(word);
        }
    }
    /**
     * 将识别出的词入栈
     * @param result 栈
     * @param text 文本
     * @param start 词开始索引
     * @param len 词长度
     */
    protected void addWord(Stack<Word> result, String text, int start, int len){
        Word word = getWord(text, start, len);
        if(word != null){
            result.push(word);
        }
    }    
    /**
     * 获取一个已经识别的词
     * @param text 文本
     * @param start 词开始索引
     * @param len 词长度
     * @return 词或空
     */
    protected Word getWord(String text, int start, int len){
        Word word = new Word(text.substring(start, start+len).toLowerCase());
        //方便编译器优化
        if(KEEP_WHITESPACE ){
            //保留空白字符
            return word;
        }else{
            //忽略空白字符，包括：空格、全角空格、\t、\n                
            if(len > 1){
                //长度大于1，不会是空白字符
                return word;
            }else{
                //长度为1，只要非空白字符
                if(!(isWhiteSpace(text, start, len))){
                    //不是空白字符，保留
                    return word;           
                }
            }
        }
        return null;
    }
    /**
     * 判断索引下标为start的字符是否为空白字符
     * 这个方法只用在这里
     * 为了速度，不检查索引下标是否越界
     * @param text 文本
     * @param start 索引下标
     * @param len 长度
     * @return 是否
     */
    protected boolean isWhiteSpace(String text, int start, int len){
        return isWhiteSpace(text.charAt(start));
    }
    /**
     * 判断指定的字符是否是空白字符
     * @param c 字符
     * @return 是否是空白字符
     */
    protected boolean isWhiteSpace(char c){
        return c == ' ' || c == '　' || c == '\t' || c == '\n';
    }
    public static void main(String[] args){

    }
}
