package com.jsdsm.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ������ʶ��
 */
public class Quantifier {
    private static final Set<Character> quantifiers=new HashSet<>();
    static{
        try {
            reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reload() throws IOException{
        
        quantifiers.clear();
        
        String path = "F:\\s3";
        System.out.println("��ʼ��������");
        List<String> lines = new ArrayList<String>();
        LoadResource.loadQuantifier(path, lines);
        for(String line : lines){
            if(line.length() == 1){
                char _char = line.charAt(0);
                if(quantifiers.contains(_char)){
                    System.out.println("�����ļ����ظ��"+line);
                }else{
                    quantifiers.add(_char);
                }
            }else{
                System.out.println("���Բ��Ϸ������ʣ�"+line);
            }
        }
        System.out.println("�����ʳ�ʼ����ϣ������ʸ�����"+quantifiers.size());
    }
    public static boolean is(char _char){
        return quantifiers.contains(_char);
    }
    public static void main(String[] args){
        int i=1;
        for(char quantifier : quantifiers){
            System.out.println((i++)+" : "+quantifier);
        }
    }
}
