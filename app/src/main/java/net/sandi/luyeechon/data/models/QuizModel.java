package net.sandi.luyeechon.data.models;

import com.google.gson.reflect.TypeToken;

import net.sandi.luyeechon.data.vos.QuizVO;
import net.sandi.luyeechon.utils.CommonInstances;
import net.sandi.luyeechon.utils.JsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaung Htet Lin on 9/24/2016.
 */
public class QuizModel {

    private static final String DUMMY_QUIZ_LIST = "quiz_list.json";

    private static QuizModel objInstance;

    private List<QuizVO> quizList;

    private QuizModel(){
        quizList = initializeQuizList();
    }

    public static QuizModel getInstance(){
        if(objInstance == null) {
            objInstance = new QuizModel();
        }

        return objInstance;
    }

    private List<QuizVO> initializeQuizList() {
        List<QuizVO> QuizList = new ArrayList<>();

        try {
            String dummyQuizList = JsonUtils.getInstance().loadDummyData(DUMMY_QUIZ_LIST);
            Type listType = new TypeToken<List<QuizVO>>() {
            }.getType();
            QuizList = CommonInstances.getGsonInstance().fromJson(dummyQuizList, listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return QuizList;
    }

    public List<QuizVO> getQuizList() {
        return quizList;
    }

   /* public EventVO getEventByTitle(String eventTitle) {
        for (EventVO event : eventList) {
            if (event.getEventTitle().equals(eventTitle)) {
                return event;
            }
        }
        return null;
    }
*/
}
