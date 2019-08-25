package services;

import MVC_package.UserService;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@Data
public class WordProcessor {

    //   private User user;
    private Double progress;
    private Double avgPoint;
    private Integer totalPoints;
    private Integer countOfWords;

    private List<WordTranslate> wordList;

    @Autowired
    private UserService userService;

    private Random random = new Random(47);

    public WordTranslate nextWord(User user) {

        wordList = userService.getDictionary(user);

        if (wordList != null) {
            totalPoints = this.wordList.stream().mapToInt(WordTranslate::getPoints).reduce(0, (i1, i2) -> i1 + i2);
            countOfWords = this.wordList.size();
            avgPoint = (double) totalPoints / (double) countOfWords;
            progress = avgPoint / 30 * 100;

            while (true) {
                WordTranslate wordTranslate = wordList.stream().skip(this.random.nextInt(wordList.size())).findAny().get();
                if (wordTranslate.getPoints() <= (this.avgPoint.intValue())) return wordTranslate;
            }
        }
        return null;
    }


}
