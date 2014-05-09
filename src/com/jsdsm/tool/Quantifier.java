package com.jsdsm.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数量词识别
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
        System.out.println("初始化数量词");
        List<String> lines = new ArrayList<String>();
        LoadResource.loadQuantifier(path, lines);
        for(String line : lines){
            if(line.length() == 1){
                char _char = line.charAt(0);
                if(quantifiers.contains(_char)){
                    System.out.println("配置文件有重复项："+line);
                }else{
                    quantifiers.add(_char);
                }
            }else{
                System.out.println("忽略不合法数量词："+line);
            }
        }
        System.out.println("数量词初始化完毕，数量词个数："+quantifiers.size());
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
