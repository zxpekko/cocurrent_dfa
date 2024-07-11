package dfa;

import java.util.*;

/**
 * @Author:zxp
 * @Description:
 * @Date:16:01 2024/7/6
 */

public class DFAFilter {

    private final Map<Character, Map> wordMap = new HashMap<>();

    public static void main(String[] args) {
        DFAFilter dfaFilter = new DFAFilter();
        dfaFilter.addWord("abcd");
        dfaFilter.addWord("abce");
        dfaFilter.addWord("abc");
//
//        System.out.println("Before removing:");
//        dfaFilter.printDFA(dfaFilter.wordMap, "");
//
//        dfaFilter.removeWord(Arrays.asList("abce"));
//
//        System.out.println("\nAfter removing 'abce':");
//        dfaFilter.printDFA(dfaFilter.wordMap, "");
        System.out.println(dfaFilter.containsSensitiveWord("this abce is abcd a str contains abc"));
    }

    /**
     * 添加敏感词
     */
    public void addWord(String word) {
        Map<Character, Map> nowMap = wordMap;
        for (int i = 0; i < word.length(); i++) {
            char keyChar = word.charAt(i);
            Map<Character, Map> newWordMap = nowMap.get(keyChar);
            if (newWordMap == null) {
                newWordMap = new HashMap<>();
                nowMap.put(keyChar, newWordMap);
            }
            nowMap = newWordMap;
        }
        nowMap.put(' ', new HashMap<>()); // 使用空格字符表示 isEnd 标志
    }

    /**
     * 在线删除敏感词
     *
     * @param wordList 敏感词列表
     */
    public void removeWord(Collection<String> wordList) {
        if (wordList == null || wordList.isEmpty()) {
            return;
        }

        for (String word : wordList) {
            Map<Character, Map> nowMap = wordMap;
            Stack<Map<Character, Map>> stack = new Stack<>();
            Stack<Character> charStack = new Stack<>();

            for (int i = 0; i < word.length(); i++) {
                char keyChar = word.charAt(i);
                Map<Character, Map> nextMap = nowMap.get(keyChar);
                if (nextMap == null) {
                    // 敏感词不存在
                    return;
                }
                stack.push(nowMap);
                charStack.push(keyChar);
                nowMap = nextMap;
            }

            if (nowMap.containsKey(' ')) {
                nowMap.remove(' ');
            }

            // 回溯清理无用节点
            for (int i = word.length() - 1; i >= 0; i--) {
                Map<Character, Map> currentMap = stack.pop();
                char currentChar = charStack.pop();

                // 如果当前字符对应的 Map 为空，则删除该字符节点
                if (nowMap.isEmpty()) {
                    currentMap.remove(currentChar);
                }

                nowMap = currentMap;
            }
        }
    }
    public String containsSensitiveWord(String text){
        StringBuilder stringBuilder = new StringBuilder(text);

        List<String> result=new ArrayList<>();
        for(int i=0;i<stringBuilder.length();i++){
            Map<Character, Map> cur = wordMap;
            int startIndex=i;
            for(int j=i;j<stringBuilder.length();j++){
                char c = stringBuilder.charAt(j);
                cur=cur.get(c);
                if(cur==null)
                    break;
                if(cur.containsKey(' ')){
                    String substring = stringBuilder.substring(startIndex, j + 1);
                    if(!result.contains(substring))
                        result.add(substring);
                    i=j;
                    for(int k=startIndex;k<=j;k++){
                        stringBuilder.replace(k,k+1,"*");
                    }
                }

            }
        }
//        System.out.println(stringBuilder);
//        return result.size()>0?true:false;
        return stringBuilder.toString();
    }
//    public boolean containsSensitiveWord(String text) {
//        for (int i = 0; i < text.length(); i++) {
//            DFASensitiveWordsFilter.State currentState = initialState;
//            for (int j = i; j < text.length(); j++) {
//                char c = text.charAt(j);
//                currentState = currentState.transitions.get(c);
//
//                if (currentState == null) {
//                    break;
//                }
//
//                if (currentState.isTerminal) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 打印 DFA 结构
     */
    private void printDFA(Map<Character, Map> map, String prefix) {
        for (Map.Entry<Character, Map> entry : map.entrySet()) {
            if (entry.getKey() == ' ') {
                System.out.println(prefix + " (end)");
            } else {
                printDFA(entry.getValue(), prefix + entry.getKey());
            }
        }
    }
}

enum WordType {
    BLACK,
    WHITE
}
