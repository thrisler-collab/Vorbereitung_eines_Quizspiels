package model;

public class QuizException extends IllegalArgumentException{


    public QuizException(String s) {
        throw new IllegalArgumentException(s);
    }


}
