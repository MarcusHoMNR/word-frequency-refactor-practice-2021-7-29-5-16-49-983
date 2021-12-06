import java.util.*;
import java.util.stream.Collectors;

//naming
//magic string
//temp var
// for loop
//long method
//comments
public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getFrequencyCountResult(String inputSentence){
        try {
            List<WordInfo> resultWordInfos = countWordFrequency(inputSentence);

            resultWordInfos.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());

            return generateResultSentence(resultWordInfos);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> countWordFrequency(String inputSentence) {
        List<String> inputWords = Arrays.asList(inputSentence.split(SPACE_PATTERN));
        List<String> distinctInputWords = inputWords.stream().distinct().collect(Collectors.toList());
        List<WordInfo> resultWordInfos = new ArrayList<>();

        distinctInputWords.forEach(distinctInputWord -> {
            int frequency = (int) inputWords.stream().filter(inputWord -> inputWord.equals(distinctInputWord)).count();
            resultWordInfos.add(new WordInfo(distinctInputWord, frequency));
        });

        return resultWordInfos;
    }


    private String generateResultSentence(List<WordInfo> resultWordInfos) {
        StringJoiner resultJoiner = new StringJoiner("\n");

        resultWordInfos.forEach(resultWordInfo -> {
            resultJoiner.add(String.format("%s %s", resultWordInfo.getWordValue(), resultWordInfo.getWordCount()));
        });

        return resultJoiner.toString();
    }
}
