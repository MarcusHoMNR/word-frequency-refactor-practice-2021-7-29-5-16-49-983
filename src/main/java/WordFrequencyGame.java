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
                List<WordInfo> resultWordInfos = calculateWordFrequency(inputSentence);

                resultWordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return getResultSentence(resultWordInfos);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private String getResultSentence(List<WordInfo> resultWordInfos) {
        StringJoiner resultJoiner = new StringJoiner("\n");
        for (WordInfo wordInfo : resultWordInfos) {
            String resultLine = wordInfo.getWordValue() + " " +wordInfo.getWordCount();
            resultJoiner.add(resultLine);
        }
        return resultJoiner.toString();
    }

    private List<WordInfo> calculateWordFrequency(String inputSentence) {
        List<String> inputWords = Arrays.asList(inputSentence.split(SPACE_PATERN));
        List<String> distinctInputWords = inputWords.stream().distinct().collect(Collectors.toList());
        List<WordInfo> resultWordInfos = new ArrayList<>();

        distinctInputWords.forEach(distinctInputWord -> {
            int frequency = (int) inputWords.stream().filter(inputWord -> inputWord.equals(distinctInputWord)).count();
            resultWordInfos.add(new WordInfo(distinctInputWord, frequency));
        });

        return resultWordInfos;
    }
}
