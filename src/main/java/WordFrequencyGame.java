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
                for (String inputWord : inputWords) {
                    WordInfo wordInfo = new WordInfo(inputWord, 1);
                    inputWordInfos.add(wordInfo);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> wordValueAndWordInfosMap =getListMap(inputWordInfos);

                List<WordInfo> countedWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> wordValueAndWordInfosEntryMap : wordValueAndWordInfosMap.entrySet()){
                    WordInfo wordInfo = new WordInfo(wordValueAndWordInfosEntryMap.getKey(), wordValueAndWordInfosEntryMap.getValue().size());
                    countedWordInfos.add(wordInfo);
                }
                inputWordInfos = countedWordInfos;

                inputWordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : inputWordInfos) {
                    String s = wordInfo.getWordValue() + " " +wordInfo.getWordCount();
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
