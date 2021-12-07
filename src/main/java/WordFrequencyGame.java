import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String LINE_BREAK_DELIMITER = "\n";
    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATION_ERROR = "Calculation Error";

    public String getWordCount(String sentence) {
        try {
            List<WordInfo> wordInfos = countWordFrequency(sentence);

            wordInfos.sort((wordInfo1, wordInfo2) -> wordInfo2.getWordCount() - wordInfo1.getWordCount());

            return generateWordWithCount(wordInfos);
        } catch (Exception e) {
            return CALCULATION_ERROR;
        }
    }

    private List<WordInfo> countWordFrequency(String inputSentence) {
        List<String> words = Arrays.asList(inputSentence.split(SPACE_PATTERN));
        List<String> distinctWords = words.stream().distinct().collect(Collectors.toList());
        List<WordInfo> wordInfos = new ArrayList<>();

        distinctWords.forEach(distinctInputWord -> {
            int frequency = (int) words.stream().filter(inputWord -> inputWord.equals(distinctInputWord)).count();
            wordInfos.add(new WordInfo(distinctInputWord, frequency));
        });

        return wordInfos;
    }

    private String generateWordWithCount(List<WordInfo> wordInfos) {
        StringJoiner resultJoiner = new StringJoiner(LINE_BREAK_DELIMITER);

        wordInfos.forEach(wordInfo -> {
            resultJoiner.add(String.format("%s %s", wordInfo.getWordValue(), wordInfo.getWordCount()));
        });

        return resultJoiner.toString();
    }
}
