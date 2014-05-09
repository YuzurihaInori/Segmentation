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
        File docDir = new File(path);   //�ֵ�λ��
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("������ʼ");
          Timer.start();
          // �ļ����ݣ���Ҫ����
          // ������
          System.out.println(fileSrc.toString()+"...���ڱ�����������");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "GBK"); // ָ����UTF-8�������
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...���ڱ�����������");
              trie.add(s);
          }
          // ʹ����������Document�ĵ�������
          System.out.println("�������"+Timer.end());
      }
      return trie;
    }
    
    public static List<String> loadPunctuation(String path, List<String> lines) throws IOException {
        File docDir = new File(path);   //�ֵ�λ��
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("������ʼ");
          Timer.start();
          // �ļ����ݣ���Ҫ����
          // ������
          System.out.println(fileSrc.toString()+"...���ڱ�����������");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // ָ����UTF-8�������
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...���ڱ�����������");
              lines.add(s);
          }
          // ʹ����������Document�ĵ�������
          System.out.println("�������"+Timer.end());
      }
      return lines;
    }
    public static List<String> loadQuantifier(String path, List<String> lines) throws IOException {
        File docDir = new File(path);   //�ֵ�λ��
        for (File fileSrc : docDir.listFiles()) {
          System.out.println("������ʼ");
          Timer.start();
          // �ļ����ݣ���Ҫ����
          // ������
          System.out.println(fileSrc.toString()+"...���ڱ�����������");
          FileInputStream fis = new FileInputStream(fileSrc);
          InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // ָ����UTF-8�������
          BufferedReader br = new BufferedReader(isr);
          String s = null;
          while ((s = br.readLine()) != null) {
              System.out.println(s.toString()+"...���ڱ�����������");
              lines.add(s);
          }
          // ʹ����������Document�ĵ�������
          System.out.println("�������"+Timer.end());
      }
      return lines;
    }
}
