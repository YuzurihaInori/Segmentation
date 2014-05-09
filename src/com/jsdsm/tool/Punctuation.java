package com.jsdsm.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * �ж�һ���ַ��Ƿ��Ǳ�����
 * @author ���д�
 */
public class Punctuation {
    private static char[] chars = null;
    static{
        try {
            reload();//���ر�������Դ
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    public static void reload() throws IOException{
        chars = null;
        String path = "F:\\s2";
        System.out.println("��ʼ��������");
        List<String> lines = new ArrayList<String>();
        LoadResource.loadPunctuation(path, lines);
        Set<Character> set = new HashSet<>();
        for(String line : lines){
            if(line.length() == 1){
                set.add(line.charAt(0));
            }else{
                System.out.println("���Ȳ�Ϊһ�ı����ţ�"+line);
            }
        }
        //���ӿհ��ַ�
        set.add(' ');
        set.add('��');
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
        System.out.println("�����ų�ʼ����ϣ������Ÿ�����"+chars.length);
        
    }
    /**
     * �ж��ı����Ƿ����������
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
     * ��һ���ı����ݱ����ŷָ�Ϊ��������������ŵ��ı�
     * ��ָ��Ҫ������Щ������
     * @param text �ı�
     * @param withPunctuation �Ƿ���������
     * @param reserve �����ı������б�
     * @return �ı��б�
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
                    //�����ı�����
                    continue outer;
                }
            }
            if(Punctuation.is(c)){
                if(i > start){
                    list.add(text.substring(start, i));
                    //��һ�俪ʼ����
                    start = i+1;
                }else{
                    //����������
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
     * �ж�һ���ַ��Ƿ��Ǳ�����
     * @param _char �ַ�
     * @return �Ƿ��Ǳ�����
     */
    public static boolean is(char _char){
        int index = Arrays.binarySearch(chars, _char);
        return index >= 0;
    }
    public static void main(String[] args){
        System.out.println("��������Դ");
        System.out.println(", : "+is(','));
        System.out.println("  : "+is(' '));
        System.out.println("�� : "+is('��'));
        System.out.println("\t : "+is('\t'));
        System.out.println("\n : "+is('\n'));
        String text= "APDPlat�ĳ��ο���׷�ݵ�2008�꣬����4���2012��4��9���ڡ�GITHUB����Դ ��APDPlat���ݻ��Ĺ����У�����ס���ڶ���Ŀ�Ŀ��飬һֱ׷�������ţ�һֱ�Լܹ�����ƺʹ�������ع��Ż��� ";
        for(String s : Punctuation.seg(text, false)){
            System.out.println("text===   "+s);
        }
    }
}