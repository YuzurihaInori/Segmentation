package com.jsdsm.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 判断一个字符是否是标点符号
 * @author 杨尚川
 */
public class Punctuation {
    private static char[] chars = null;
    static{
        try {
            reload();//加载标点符号资源
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    public static void reload() throws IOException{
        chars = null;
        String path = "F:\\s2";
        System.out.println("初始化标点符号");
        List<String> lines = new ArrayList<String>();
        LoadResource.loadPunctuation(path, lines);
        Set<Character> set = new HashSet<>();
        for(String line : lines){
            if(line.length() == 1){
                set.add(line.charAt(0));
            }else{
                System.out.println("长度不为一的标点符号："+line);
            }
        }
        //增加空白字符
        set.add(' ');
        set.add('　');
        set.add('\t');
        set.add('\n');
        List<Character> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);
        int len = list.size();
        chars = new char[len];
        for(int i=0; i<len; i++){
            chars[i] = list.get(i);
        }
        set.clear();
        list.clear();
        System.out.println("标点符号初始化完毕，标点符号个数："+chars.length);
        
    }
    /**
     * 判断文本中是否包含标点符号
     * @param text
     * @return 
     */
    public static boolean has(String text){
        for(char c : text.toCharArray()){
            if(is(c)){
                return true;
            }
        }
        return false;
    }
    /**
     * 将一段文本根据标点符号分割为多个不包含标点符号的文本
     * 可指定要保留那些标点符号
     * @param text 文本
     * @param withPunctuation 是否保留标点符号
     * @param reserve 保留的标点符号列表
     * @return 文本列表
     */
    public static List<String> seg(String text, boolean withPunctuation, char... reserve){
        List<String> list = new ArrayList<>();
        int start = 0;
        char[] array = text.toCharArray();
        int len = array.length;
        outer:for(int i=0; i<len; i++){
            char c = array[i];
            for(char t : reserve){
                if(c == t){
                    //保留的标点符号
                    continue outer;
                }
            }
            if(Punctuation.is(c)){
                if(i > start){
                    list.add(text.substring(start, i));
                    //下一句开始索引
                    start = i+1;
                }else{
                    //跳过标点符号
                    start++;
                }
                if(withPunctuation){
                    list.add(Character.toString(c));
                }
            }
        }
        if(len - start > 0){
            list.add(text.substring(start, len));
        }
        return list;
    }
    /**
     * 判断一个字符是否是标点符号
     * @param _char 字符
     * @return 是否是标点符号
     */
    public static boolean is(char _char){
        int index = Arrays.binarySearch(chars, _char);
        return index >= 0;
    }
    public static void main(String[] args){
        System.out.println("标点符号资源");
        System.out.println(", : "+is(','));
        System.out.println("  : "+is(' '));
        System.out.println("　 : "+is('　'));
        System.out.println("\t : "+is('\t'));
        System.out.println("\n : "+is('\n'));
        String text= "APDPlat的雏形可以追溯到2008年，并于4年后即2012年4月9日在“GITHUB”开源 。APDPlat在演化的过程中，经受住了众多项目的考验，一直追求简洁优雅，一直对架构、设计和代码进行重构优化。 ";
        for(String s : Punctuation.seg(text, false)){
            System.out.println("text===   "+s);
        }
    }
}