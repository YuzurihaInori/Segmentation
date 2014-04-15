package com.jsdsm.dictionary;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * �ֵ�������{@link Dictionary}}
 * @author jisi
 *
 */
public class SearchDictionary extends Dictionary {
	
	public static int searchDictionary(String word){
		
		// ���������ļ��ĵط�
		String indexDir = "F:\\index2";
		try {
			IndexReader reader = IndexReader.open( FSDirectory.open(new File(indexDir)) , true );
			  IndexSearcher indexSearch = new IndexSearcher(reader);
//				Directory dir = new SimpleFSDirectory(new File(indexDir));
				// ���� IndexSearcher�������IndexWriter�������������Ҫ�ṩһ��������Ŀ¼������
//				IndexSearcher indexSearch = new IndexSearcher(dir);
				// ����QueryParser����,��һ��������ʾLucene�İ汾,�ڶ�����ʾ����Field���ֶ�,��������ʾ����ʹ�÷ִ���
				QueryParser queryParser = new QueryParser(Version.LUCENE_30,"content",new WhitespaceAnalyzer());
				// ����Query����
				Query query = queryParser.parse(word);
				// ������� TopDocs������scoreDocs[]���飬���汣��������ֵ
				TopDocs hits = indexSearch.search(query, 10);
				// hits.totalHits��ʾһ���ѵ����ٸ�
				System.out.println("�ҵ���" + hits.totalHits + "��");
				// ѭ��hits.scoreDocs���ݣ���ʹ��indexSearch.doc������Document��ԭ�����ó���Ӧ���ֶε�ֵ
//				for (int i = 0; i < hits.scoreDocs.length; i++) {
//					ScoreDoc sdoc = hits.scoreDocs[i];
//					Document doc = indexSearch.doc(sdoc.doc);
//					System.out.println(doc.get("filename"));
//				}
				indexSearch.close();
				return hits.totalHits;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
