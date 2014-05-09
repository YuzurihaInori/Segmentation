/**
 * 
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2013, ���д�, yang-shangchuan@qq.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.jsdsm.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jsdsm.dictionary.Dictionary;;

/**
 * ǰ׺����Javaʵ��
 * Ϊǰ׺����һ���ڵ㽨���������ȶ��ֲ���Ҫ�죩
 * ���ڲ���һ��ָ�����ַ����Ƿ����ֵ���
 * @author ���д�
 */
public class DictionaryTrie implements Dictionary{
//    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryTrie.class);
    //�ʱ������ĸ������һ���ɿط�Χ�ڣ�Ĭ��ֵΪ24000
    private static final int INDEX_LENGTH = 24000;
    private static DictionaryTrie uniqueInstance;
    private final TrieNode[] ROOT_NODES_INDEX = new TrieNode[INDEX_LENGTH];
    private int maxLength;
    
    public static DictionaryTrie getInstance() {  
        if (uniqueInstance == null) {  
            uniqueInstance = new DictionaryTrie();  
        }  
        return uniqueInstance;  
     } 
    
    public void clear() {
        for(int i=0; i<INDEX_LENGTH; i++){
            ROOT_NODES_INDEX[i] = null;
        }
    }
    /**
     * ͳ�Ƹ��ڵ��ͻ�����Ԥ���������ռ��������
     */
    public void showConflict(){
        int emptySlot=0;
        //key:��ͻ���� value:��ͻ����
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(TrieNode node : ROOT_NODES_INDEX){
            if(node == null){
                emptySlot++;
            }else{
                int i=0;
                while((node = node.getSibling()) != null){
                    i++;
                }
                if(i > 0){
                    Integer count = map.get(i);
                    if(count == null){
                        count = 1;
                    }else{
                        count++;
                    }
                    map.put(i, count);
                }
            }
        }
        int count=0;
        for(int key : map.keySet()){
            int value = map.get(key);
            count += key*value;
//            LOGGER.info("��ͻ����Ϊ��"+key+" ��Ԫ�ظ�����"+value);
            System.out.println("��ͻ����Ϊ��"+key+" ��Ԫ�ظ�����"+value);
        }
//        LOGGER.info("��ͻ������"+count);
//        LOGGER.info("�ܲ�����"+INDEX_LENGTH);
//        LOGGER.info("�ò�����"+(INDEX_LENGTH-emptySlot));
//        LOGGER.info("ʹ���ʣ�"+(float)(INDEX_LENGTH-emptySlot)/INDEX_LENGTH*100+"%");
//        LOGGER.info("ʣ������"+emptySlot);
        System.out.println("��ͻ������"+count);
        System.out.println("�ܲ�����"+INDEX_LENGTH);
        System.out.println("�ò�����"+(INDEX_LENGTH-emptySlot));
        System.out.println("ʹ���ʣ�"+(float)(INDEX_LENGTH-emptySlot)/INDEX_LENGTH*100+"%");
        System.out.println("ʣ������"+emptySlot);
    }
    /**
     * ��ȡ�ַ���Ӧ�ĸ��ڵ�
     * ����ڵ㲻����
     * �����Ӹ��ڵ�󷵻������Ľڵ�
     * @param character �ַ�
     * @return �ַ���Ӧ�ĸ��ڵ�
     */
    private TrieNode getRootNodeIfNotExistThenCreate(char character){
        TrieNode trieNode = getRootNode(character);
        if(trieNode == null){
            trieNode = new TrieNode(character);
            addRootNode(trieNode);
        }
        return trieNode;
    }
    /**
     * ����һ�����ڵ�
     * @param rootNode ���ڵ�
     */
    private void addRootNode(TrieNode rootNode){
        //����ڵ�Ĵ洢����
        int index = rootNode.getCharacter()%INDEX_LENGTH;
        //��������Ƿ�������ڵ��ͻ
        TrieNode existTrieNode = ROOT_NODES_INDEX[index];
        if(existTrieNode != null){
            //�г�ͻ������ͻ�ڵ㸽�ӵ���ǰ�ڵ�֮��
            rootNode.setSibling(existTrieNode);
        }
        //�����Ľڵ���������ǰ
        ROOT_NODES_INDEX[index] = rootNode;
    }
    /**
     * ��ȡ�ַ���Ӧ�ĸ��ڵ�
     * ��������ڣ��򷵻�NULL
     * @param character �ַ�
     * @return �ַ���Ӧ�ĸ��ڵ�
     */
    private TrieNode getRootNode(char character){
        //����ڵ�Ĵ洢����
        int index = character%INDEX_LENGTH;
        TrieNode trieNode = ROOT_NODES_INDEX[index];
        while(trieNode != null && character != trieNode.getCharacter()){
            //����ڵ�������ڵ��ͻ������Ҫ��ʽ����
            trieNode = trieNode.getSibling();
        }
        return trieNode;
    }
    public List<String> prefix(String prefix){
        List<String> result = new ArrayList<String>();
        //ȥ����β�հ��ַ�
        prefix=prefix.trim();
        int len = prefix.length();    
        if(len < 1){
            return result;
        }          
        //�Ӹ��ڵ㿪ʼ����
        //��ȡ���ڵ�
        TrieNode node = getRootNode(prefix.charAt(0));
        if(node == null){
            //�����ڸ��ڵ㣬��������
            return result;
        }
        //���ڸ��ڵ㣬��������
        for(int i=1;i<len;i++){
            char character = prefix.charAt(i);
            TrieNode child = node.getChild(character);
            if(child == null){
                //δ�ҵ�ƥ��ڵ�
                return result;
            }else{
                //�ҵ��ڵ㣬����������
                node = child;
            }
        }
        for(TrieNode item : node.getChildren()){            
            result.add(prefix+item.getCharacter());
        }
        return result;
    }
    
    public boolean contains(String item){
        return contains(item, 0, item.length());
    }
    
    public boolean contains(String item, int start, int length){
        if(start < 0 || length < 1){
            return false;
        }
        if(item == null || item.length() < length){
            return false;
        }
//        LOGGER.debug("��ʼ��ʵ䣺"+item.substring(start, start+length));
        System.out.println("��ʼ��ʵ䣺"+item.substring(start, start+length));
        //�Ӹ��ڵ㿪ʼ����
        //��ȡ���ڵ�
        TrieNode node = getRootNode(item.charAt(start));
        if(node == null){
            //�����ڸ��ڵ㣬��������
            return false;
        }
        //���ڸ��ڵ㣬��������
        for(int i=1;i<length;i++){
            char character = item.charAt(i+start);
            TrieNode child = node.getChild(character);
            if(child == null){
                //δ�ҵ�ƥ��ڵ�
                return false;
            }else{
                //�ҵ��ڵ㣬����������
                node = child;
            }
        }
        if(node.isTerminal()){
//            LOGGER.debug("�ڴʵ��в鵽�ʣ�"+item.substring(start, start+length));
            System.out.println("�ڴʵ��в鵽�ʣ�"+item.substring(start, start+length));
            return true;
        }
        return false;
    }
    
    public void addAll(List<String> items){
        for(String item : items){
            add(item);
        }
    }
    
    public void add(String item){
        //ȥ����β�հ��ַ�
        item=item.trim();
        int len = item.length();
        if(len < 1){
            //����С��1�����
            return;
        }
        if(len>maxLength){
            maxLength=len;
        }
        //�Ӹ��ڵ㿪ʼ���
        //��ȡ���ڵ�
        TrieNode node = getRootNodeIfNotExistThenCreate(item.charAt(0));
        for(int i=1;i<len;i++){
            char character = item.charAt(i);
            TrieNode child = node.getChildIfNotExistThenCreate(character);
            //�ı䶥���ڵ�
            node = child;
        }
        //�����ս��ַ�����ʾ�Ӹ��ڵ����������һ���Ϸ��Ĵ�
        node.setTerminal(true);
    }
    
    public int getMaxLength() {
        return maxLength;
    }
    private static class TrieNode implements Comparable<Object>{
        
        private char character;
        private boolean terminal;
        private TrieNode sibling;
        private TrieNode[] children = new TrieNode[0];
        public TrieNode(char character){
            this.character = character;
        }
        public boolean isTerminal() {
            return terminal;
        }
        public void setTerminal(boolean terminal) {
            this.terminal = terminal;
        }        
        public char getCharacter() {
            return character;
        }
        public void setCharacter(char character) {
            this.character = character;
        }
        public TrieNode getSibling() {
            return sibling;
        }
        public void setSibling(TrieNode sibling) {
            this.sibling = sibling;
        }
        public Collection<TrieNode> getChildren() {
            return Arrays.asList(children);            
        }
        /**
         * ���ö��������㷨�������������ҵ��ض��Ľڵ�
         * @param character �����ҽڵ�
         * @return NULL OR �ڵ�����
         */
        public TrieNode getChild(char character) {
            int index = Arrays.binarySearch(children, character);
            if(index >= 0){
                return children[index];
            }
            return null;
        }        
        public TrieNode getChildIfNotExistThenCreate(char character) {
            TrieNode child = getChild(character);
            if(child == null){
                child = new TrieNode(character);
                addChild(child);
            }
            return child;
        }
        public void addChild(TrieNode child) {
            children = insert(children, child);
        }
        /**
         * ��һ���ַ�׷�ӵ���������
         * @param array ��������
         * @param element �ַ�
         * @return �µ���������
         */
        private TrieNode[] insert(TrieNode[] array, TrieNode element){
            int length = array.length;
            if(length == 0){
                array = new TrieNode[1];
                array[0] = element;
                return array;
            }
            TrieNode[] newArray = new TrieNode[length+1];
            boolean insert=false;
            for(int i=0; i<length; i++){
                if(element.getCharacter() <= array[i].getCharacter()){
                    //��Ԫ���ҵ����ʵĲ���λ��
                    newArray[i]=element;
                    //��array��ʣ�µ�Ԫ�����μ���newArray�����˳��Ƚϲ���
                    System.arraycopy(array, i, newArray, i+1, length-i);
                    insert=true;
                    break;
                }else{
                    newArray[i]=array[i];
                }
            }
            if(!insert){
                //����Ԫ��׷�ӵ�β��
                newArray[length]=element;
            }
            return newArray;
        }
        /**
         * ע������ıȽ϶�����char
         * @param o char
         * @return 
         */
        public int compareTo(Object o) {
            return this.getCharacter()-(Character)o;
        }
    }    
    public void show(char character){
        show(getRootNode(character), "");
    }
    public void show(){
        for(TrieNode node : ROOT_NODES_INDEX){
            if(node != null){
                show(node, "");
            }
        }
    }
    private void show(TrieNode node, String indent){
        if(node.isTerminal()){
//            LOGGER.info(indent+node.getCharacter()+"(T)");
            System.out.println(indent+node.getCharacter()+"(T)");
        }else{
//            LOGGER.info(indent+node.getCharacter());
            System.out.println(indent+node.getCharacter());
        }        
        for(TrieNode item : node.getChildren()){
            show(item,indent+"\t");
        }
    }
//    public static void main(String[] args){
//        DictionaryTrie trie = getInstance();
//        trie.add("APDPlat");
//        trie.add("APP");
//        trie.add("APD");
//        trie.add("���д�");
//        trie.add("������");
//        trie.add("����ϲ");
//        trie.add("�л����񹲺͹�");
//        trie.add("�л������̫��");
//        trie.add("�л�");
//        trie.add("����˼��");
//        trie.add("��ҽ�");        
//        trie.show();
////        LOGGER.info(trie.prefix("��").toString());
////        LOGGER.info(trie.prefix("�л�").toString());
////        LOGGER.info(trie.prefix("��").toString());
////        LOGGER.info(trie.prefix("����").toString());
//        System.out.println(trie.prefix("��").toString());
//        System.out.println(trie.prefix("�л�").toString());
//        System.out.println(trie.prefix("��").toString());
//        System.out.println(trie.prefix("����").toString());
//        
//    }
}
