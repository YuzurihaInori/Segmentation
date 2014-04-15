package com.jsdsm.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

/**
 * �ֵ�����������{@link Dictionary}}
 * @author jisi
 *
 */
public class IndexDictionary extends Dictionary {
	
	public static boolean createIndex() throws CorruptIndexException, LockObtainFailedException, IOException{

		File indexDir=new File("F:\\index2"); 
		if (!indexDir.exists()) {
			indexDir.mkdir();
		}
		//��Ҫ�����������ĵ����ϵ�λ��  
		 File docDir = new File("F:\\s2");   
		//����������(����)  
		IndexWriter standardWriter = new IndexWriter(FSDirectory.open(indexDir),new StandardAnalyzer(Version.LUCENE_30), true, IndexWriter.MaxFieldLength.LIMITED);           
		//����������ʽ�����ļ���Ĭ�ϵ�������Ǹ���ʽ�������ļ�  
		standardWriter.setUseCompoundFile(false);  
		//Ϊԭ�ĵ������е�ÿ���ĵ��������Ϣ��������  
		for (File fileSrc : docDir.listFiles()) {     
	        //�ļ����ݣ���Ҫ����  
			//������
			FileInputStream fis = new FileInputStream(fileSrc);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8"); //ָ����UTF-8�������
	        BufferedReader br = new BufferedReader(isr);
	        String s = null;
	        while ((s = br.readLine())!=null) {
	        	
	        	 //Lucene���ĵ��ṹ  
	            Document doc = new Document();                       
	            //�ļ����ƣ��ɲ�ѯ�����ִ�  
	            String fileName=fileSrc.getName().substring(0,fileSrc.getName().indexOf("."));  
	            doc.add(new Field("name",fileName, Field.Store.YES, Field.Index.NOT_ANALYZED));    
	             //�ļ�·�����ɲ�ѯ�����ִ�  
	            String filePath=fileSrc.getPath();  
	            doc.add(new Field("path", filePath, Field.Store.YES, Field.Index.NOT_ANALYZED));  
	        	System.out.println(s.toString()+"   "+"���ڱ�����.....");
	        	doc.add(new Field("content", s, Field.Store.YES, Field.Index.NOT_ANALYZED)); 
	        	standardWriter.addDocument(doc); 
			}
	        //ʹ����������Document�ĵ�������  
	}    
		System.out.println("�������");
		//�ر�����������д����������ļ�  
		standardWriter.optimize();    
		standardWriter.close();   
		return true;
	}

}
