package net.sandi.luyeechon.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kaung Htet Lin on 9/24/2016.
 */
public class QuizVO {

    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    @SerializedName("contain")
    private String contain;

    public QuizVO(String question, String answer, String contain) {
        this.question = question;
        this.answer = answer;
        this.contain = contain;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getContain() {
        return contain;
    }
}
