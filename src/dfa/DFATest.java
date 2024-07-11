package dfa;

/**
 * @Author:zxp
 * @Description:
 * @Date:14:36 2024/7/5
 */
public class DFATest {
    public static void main(String[] args) {
        String[] sensitiveWords = {"abc", "abd", "bce","bab"};
        DFASensitiveWordsFilter filter = new DFASensitiveWordsFilter(sensitiveWords);

        String text = "This is a test string containing abab.";
//        String text1 = "This is a test string containing abc.";
        boolean contains = filter.containsSensitiveWord(text);
//        boolean contains1 = filter.containsSensitiveWord(text1);
        System.out.println("Contains sensitive word: " + contains);
//        System.out.println("Contains sensitive word: " + contains1);
    }
}
