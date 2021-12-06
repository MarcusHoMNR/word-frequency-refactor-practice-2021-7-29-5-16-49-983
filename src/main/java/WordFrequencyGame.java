import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_PATERN = "\\s+";

    public String getFrequencyCountResult(String inputSentence){


        if (inputSentence.split(SPACE_PATERN).length==1) {
            return inputSentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] inputWords = inputSentence.split(SPACE_PATERN);

                List<WordInfo> inputWordInfos = new ArrayList<>();
                for (String word : inputWords) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    inputWordInfos.add(wordInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(inputWordInfos);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    wordInfos.add(wordInfo);
                }
                inputWordInfos = wordInfos;

                inputWordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : inputWordInfos) {
                    String s = w.getWordValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWordValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWordValue(), arr);
            }

            else {
                map.get(wordInfo.getWordValue()).add(wordInfo);
            }
        }


        return map;
    }


}
