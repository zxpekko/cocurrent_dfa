package dfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:36 2024/7/5
 */
public class DFASensitiveWordsFilter {
    private static class State {
        Map<Character, State> transitions = new HashMap<>();
        boolean isTerminal = false;
    }

    private final State initialState;

    public DFASensitiveWordsFilter(String[] sensitiveWords) {
        initialState = new State();
//        System.out.println(initialState);
        buildDFA(sensitiveWords);
    }

    private void buildDFA(String[] sensitiveWords) {
        for (String word : sensitiveWords) {
            State currentState = initialState;
            for (char c : word.toCharArray()) {
                currentState = currentState.transitions.computeIfAbsent(c, k -> new State());
            }
            currentState.isTerminal = true;
//            System.out.println(currentState);
        }
    }

    //    public boolean containsSensitiveWord(String text) {
//        State currentState = initialState;
//        for (int i = 0; i < text.length(); i++) {
//            char c = text.charAt(i);
//            currentState = currentState.transitions.get(c);
//
//            if (currentState == null) {
//                // 回溯到初始状态，重新开始匹配
//                currentState = initialState;
//                // 再次检查当前字符，防止漏匹配
//                if (initialState.transitions.containsKey(c)) {
//                    currentState = initialState.transitions.get(c);
//                }
//            }
//
//            if (currentState != null && currentState.isTerminal) {
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean containsSensitiveWord(String text) {
        for (int i = 0; i < text.length(); i++) {
            State currentState = initialState;
            for (int j = i; j < text.length(); j++) {
                char c = text.charAt(j);
                currentState = currentState.transitions.get(c);

                if (currentState == null) {
                    break;
                }

                if (currentState.isTerminal) {
                    return true;
                }
            }
        }
        return false;
    }
}