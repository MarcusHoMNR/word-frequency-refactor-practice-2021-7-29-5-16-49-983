import java.util.*;
import java.util.stream.Collectors;

//naming
//magic string
//temp var
// for loop
//long method
//comments
public class WordFrequencyGame {

    private static final String SPACE_PATERN = "\\s+";

    public String getFrequencyCountResult(String inputSentence){


        if (inputSentence.split(SPACE_PATERN).length==1) {
            return inputSentence + " 1";
        } else {
            try {
                List<WordInfo> inputWordInfos = calculateWordFrequency(inputSentence);

                inputWordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : inputWordInfos) {
                    String resultLine = wordInfo.getWordValue() + " " +wordInfo.getWordCount();
                    joiner.add(resultLine);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateWordFrequency(String inputSentence) {
        List<String> inputWords = Arrays.asList(inputSentence.split(SPACE_PATERN));
        List<String> distinctInputWords = inputWords.stream().distinct().collect(Collectors.toList());
        List<WordInfo> resultWordInfos = new ArrayList<>();

        distinctInputWords.forEach(distinctInputWord -> {
            int frequency = (int) inputWords.stream().filter(inputWord -> inputWord.equals(distinctInputWord)).count();
            WordInfo resultWordInfo = new WordInfo(distinctInputWord, frequency);
            resultWordInfos.add(resultWordInfo);
        });

        return resultWordInfos;
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
