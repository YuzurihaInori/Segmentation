package com.jsdsm.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import com.jsdsm.dictionary.DictionaryTrie;

public class LoadResource {
    public static DictionaryTrie loadDic(String path, DictionaryTrie trie) throws IOException{
        File docDir = new File(path);   //字典位置
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("索引开始");
          Timer.start();
          // 文件内容，需要检索
          // 输入流
          System.out.println(fileSrc.toString()+"...正在被索引。。。");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "GBK"); // 指定以UTF-8编码读入
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...正在被索引。。。");
              trie.add(s);
          }
          // 使用索引器对Document文档建索引
          System.out.println("索引完毕"+Timer.end());
      }
      return trie;
    }
    
    public static List<String> loadPunctuation(String path, List<String> lines) throws IOException {
        File docDir = new File(path);   //字典位置
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("索引开始");
          Timer.start();
          // 文件内容，需要检索
          // 输入流
          System.out.println(fileSrc.toString()+"...正在被索引。。。");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // 指定以UTF-8编码读入
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...正在被索引。。。");
              lines.add(s);
          }
          // 使用索引器对Document文档建索引
          System.out.println("索引完毕"+Timer.end());
      }
      return lines;
    }
    public static List<String> loadQuantifier(String path, List<String> lines) throws IOException {
        File docDir = new File(path);   //字典位置
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("索引开始");
          Timer.start();
          // 文件内容，需要检索
          // 输入流
          System.out.println(fileSrc.toString()+"...正在被索引。。。");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // 指定以UTF-8编码读入
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...正在被索引。。。");
              lines.add(s);
          }
          // 使用索引器对Document文档建索引
          System.out.println("索引完毕"+Timer.end());
      }
      return lines;
    }
}
