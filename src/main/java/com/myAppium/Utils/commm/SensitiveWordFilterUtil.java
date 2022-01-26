package com.myAppium.Utils.commm;

import com.myAppium.Utils.excel.ExcelReader;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.regexp.RE;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.*;

public class SensitiveWordFilterUtil {
    /**
     * https://www.cnblogs.com/softidea/p/5510443.html
     *
     * 或者直接放在 非关系型数据库中，直接查询即可
     *
     * 不考虑只有一个字的情况，例如：将“操”，设置为敏感字， 那么 “操场" 这个词会被判断为包含敏感词,但是它敏感吗？
     */

    private static Map<Object, Object> sensitiveWordMap;
    private static List<List<String>> sensitiveWordList;

    private static void initSensitiveWordFromExcel() {
//        List<List<String>> lists = null;
        try {
            sensitiveWordList = new ExcelReader(new ClassPathResource("static/sensitiveWord/敏感词库表统计.xlsx").getFile().getPath()).readAll();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("未找到文件，敏感词库生成异常");
        }

        HashSet<String> set = new HashSet<>();
        // 第一行 为 标题 跳过
        for (int i = 1; i < sensitiveWordList.size(); i++) {
            if (!ObjectUtils.isEmpty(sensitiveWordList.get(i).get(3))) {
                set.add(sensitiveWordList.get(i).get(3));
            }
        }
        initSensitiveWordMap(set);
//        System.out.println(sensitiveWordMap);
    }

    /**
     * 初始化敏感词库,构建 DFA算法模型
     *  假设 敏感词库中存在如下几个敏感词：日本人、日本鬼子、日本男人
     *  则生成的hashmap结构为
     *  日->本->人
     *        ->鬼->子
     *        ->男->人
     * 即：query 日 —> {本}、query 本 —>{人、鬼子、男人}、query 人 —>{null}、query 鬼 —> {子} 、query 子 —> {}
     * @param sensitiveWordSet 敏感词库
     */
    private static void initSensitiveWordMap(Set<String> sensitiveWordSet) {
        //初始化敏感词容器,减少扩容操作
        sensitiveWordMap = new HashMap<>(sensitiveWordSet.size());
        Map<Object, Object> currentNode;
        Map<Object, Object> newNode;
        Map<Object, Object> nextNode;
        //遍历sensitiveWordSet，建立敏感词hashmap:将set中的元素依次加入hashmap中
        for (String key : sensitiveWordSet) {
            currentNode = sensitiveWordMap;
            for (char keyChar : key.toCharArray()) {
                //库中获取关键字
                nextNode = (Map) currentNode.get(keyChar);
                if (nextNode == null) {
                    newNode = new HashedMap<>();
                    currentNode.put(keyChar, newNode);
                    currentNode = newNode;
                    continue;
                }
                currentNode = nextNode;
            }
        }
    }

    /**
     * 查找字符串中的敏感字
     * query 日 —> {本}、query 本 —>{人、鬼子、男人}、query 人 —>{null}、query 鬼 —> {子} 、query 子 —> {}
     * 不考虑只有一个字的情况，例如：将“操”，设置为敏感字， 那么 “操场" 这个词会被判断为包含敏感词,但是它敏感吗？
     * 若包含返回true,否则返回false
     */
    private static synchronized List<IndexNode> getSensitiveWordList(String txt) {

        init();

        Map<Object, Object>   nextNode = null;
        Map<Object, Object>   currentNode = sensitiveWordMap;
        IndexNode indexNode = null;
        List<IndexNode> sensitiveWordList = new ArrayList<>();
        int index = -1;
        for(char key: txt.toCharArray()){
            index ++;
            nextNode = (Map<Object, Object>) currentNode.get(key);
            // 当前字 在当前敏感节点 没有敏感字
            if(nextNode == null) {

                // 保存敏感字 不考虑只有一个字的情况
                if (indexNode != null && indexNode.getStartIndex() != indexNode.getEndIndex()){
                    sensitiveWordList.add(indexNode);
                }
                indexNode = null;
                // 当前字在全局map中有敏感词组
                if(sensitiveWordMap.get(key) != null) {
                    indexNode = IndexNode.create(index);
                    currentNode = (Map<Object, Object>)sensitiveWordMap.get(key);
                }else {
                    // 进行下个敏感词的查找
                    currentNode = sensitiveWordMap;
                }
                continue;
            }

            // 找完了
            if(nextNode.isEmpty()){
                if(indexNode == null) {
                    indexNode = IndexNode.create(index);
                }
                else {
                    indexNode.setEndIndex(index);
                }

                // 保存敏感字
                if(indexNode.getStartIndex() != indexNode.getEndIndex()){
                    sensitiveWordList.add(indexNode);
                }
                indexNode = null;
                // 当完成当前敏感词查找后， 进行下个敏感词的查找
                currentNode = sensitiveWordMap;
                continue;
            }
//
            // 继续找
            if(indexNode == null){
                indexNode = IndexNode.create(index);
            }else {
                indexNode.setEndIndex(index);
            }
            currentNode = nextNode;
        }
        return sensitiveWordList;
    }
    /**
     * 查找字符串中的敏感字
     * query 日 —> {本}、query 本 —>{人、鬼子、男人}、query 人 —>{null}、query 鬼 —> {子} 、query 子 —> {}
     * 不考虑只有一个字的情况，例如：将“操”，设置为敏感字， 那么 “操场" 这个词会被判断为包含敏感词,但是它敏感吗？
     * 若包含返回true,否则返回false
     */
    public static synchronized List<String> findSensitiveWordList(String text) {
        List<IndexNode> sensitiveWordList = getSensitiveWordList(text);
        List<String> list = new ArrayList<>();
        for (IndexNode indexNode: sensitiveWordList){
            list.add(text.substring(indexNode.getStartIndex(), indexNode.getEndIndex() + 1));
        }
        return list;
    }

    /**
     * 是否包含敏感字
     * @param text 需要查找的字符串
     * @return Boolean
     */
   public static Boolean containsSensitiveWord(String text){
       List<IndexNode> sensitiveWordList = getSensitiveWordList(text);
       return !ObjectUtils.isEmpty(sensitiveWordList);
   }

    /**
     * 替换敏感词，
     * 例如：
     * @param text 输入文本
     * @param replacement 需要替换成的字符串
     * @return
     */
   public static String replaceSensitiveWord(String text, String replacement){
       StringBuilder stringBuilder = new StringBuilder(text);
       List<IndexNode> sensitiveWordList = getSensitiveWordList(text);
       for (IndexNode indexNode: sensitiveWordList){
           stringBuilder.delete(indexNode.getStartIndex(), indexNode.getEndIndex() + 1);
           for(int i = 0; i< indexNode.getEndIndex() - indexNode.startIndex; i++){
               replacement +=replacement;
           }
           stringBuilder.insert(indexNode.getStartIndex(), replacement);
       }
       return stringBuilder.toString();
   }

   public static String getRandomSensitiveWord(){
       init();
       int size = sensitiveWordList.size();

       return sensitiveWordList.get(new Random().nextInt(size)).get(3);
   }

    private static void init(){
        if (ObjectUtils.isEmpty(sensitiveWordMap)){
            initSensitiveWordFromExcel();
        }
    }

    public static Map<Object, Object> getSensitiveWordMap() {
       if(null == sensitiveWordMap){
           init();
       }
        return sensitiveWordMap;
    }

    public static class IndexNode{
        private int startIndex;
        private int endIndex;

        private IndexNode(int startIndex){
            this.startIndex = startIndex;
            this.endIndex = startIndex;
        }

        public static IndexNode create(int startIndex){
            return new IndexNode(startIndex);
        }
        public void addEndIndex(){
            this.endIndex += 1;
        }
        public int getStartIndex() {
            return startIndex;
        }

        public IndexNode setStartIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public IndexNode setEndIndex(int endIndex) {
            this.endIndex = endIndex;
            return this;
        }
    }
}
