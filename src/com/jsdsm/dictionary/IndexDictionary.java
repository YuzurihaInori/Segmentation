package com.jsdsm.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.jsdsm.tool.LoadResource;
import com.jsdsm.tool.Timer;

/**
 * 字典索引建立类{@link Dictionary}
 * 
 * @author jisi
 * 
 */
public class IndexDictionary {
    
//    public static boolean createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
//
//        File indexDir = new File("F:\\index2"); //目录位置
//        if (!indexDir.exists()) {
//            indexDir.mkdir();
//        }
//        // 需要建立索引的文档集合的位置
//        File docDir = new File("F:\\s2");       //字典源
//        // 创建索引器(核心)
//        IndexWriter standardWriter = new IndexWriter(FSDirectory.open(indexDir),
//                new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.LIMITED);
//        // 不建立复合式索引文件，默认的情况下是复合式的索引文件
//        standardWriter.setUseCompoundFile(false);
//        // 为原文档集合中的每个文档的相关信息建立索引
//        for (File fileSrc : docDir.listFiles()) {
//            System.out.println("索引开始");
//            Timer.start();
//            // 文件内容，需要检索
//            // 输入流
//            FileInputStream fis = new FileInputStream(fileSrc);
//            InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); // 指定以UTF-8编码读入
//            BufferedReader br = new BufferedReader(isr);
//            String s = null;
//            while ((s = br.readLine()) != null) {
//
//                // Lucene的文档结构
//                Document doc = new Document();
//                // 文件名称，可查询，不分词
//                String fileName = fileSrc.getName().substring(0, fileSrc.getName().indexOf("."));
//                doc.add(new Field("name", fileName, Field.Store.YES, Field.Index.NOT_ANALYZED));
//                // 文件路径，可查询，不分词
//                String filePath = fileSrc.getPath();
//                doc.add(new Field("path", filePath, Field.Store.YES, Field.Index.NOT_ANALYZED));
//                System.out.println(s.toString() + "   " + "正在被索引.....");
//                doc.add(new Field("content", s, Field.Store.YES, Field.Index.NOT_ANALYZED));
//                standardWriter.addDocument(doc);
//            }
//            // 使用索引器对Document文档建索引
//            System.out.println("索引完毕"+Timer.end());
//        }
//        // 关闭索引器，并写入磁盘索引文件
//        standardWriter.optimize();
//        standardWriter.close();
//        return true;
//    }
    
    public static boolean createIndex() throws IOException{
        DictionaryTrie trie = DictionaryTrie.getInstance();
        String path = "F:\\s";
        LoadResource.loadDic(path,trie);
        return true;
    }
}
