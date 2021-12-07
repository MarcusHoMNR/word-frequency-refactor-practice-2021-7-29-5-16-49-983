import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() {
        //Given
        String inputSentence = "the";
        String expectResult = "the 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words() {
        //Given
        String inputSentence = "the is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() {
        //Given
        String inputSentence = "the      is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() {
        //Given
        String inputSentence = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() {
        //Given
        String inputSentence = "the the is";
        String expectResult = "the 2\nis 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() {
        //Given
        String inputSentence = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    @Test
    public void should_return_Calculation_Error_message_with_null_input() {
        //Given
        String inputSentence = null;
        String expectResult = "Calculation Error";
        validate_Input_words_process_to_expected_word(inputSentence, expectResult);
    }

    private void validate_Input_words_process_to_expected_word(String inputSentence, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getFrequencyCountResult(inputSentence);
        //Then
        assertThat(result).isEqualTo(expectResult);
    }
}
