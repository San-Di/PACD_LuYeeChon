package net.sandi.luyeechon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.models.QuizModel;
import net.sandi.luyeechon.data.vos.QuizVO;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 9/18/2016.
 */
public class QuizActivity extends AppCompatActivity {

    private static List<QuizVO> quizVOList;

    public static Intent newIntent() {
        quizVOList = QuizModel.getInstance().getQuizList();
        Intent intent = new Intent(LuYeeChonApp.getContext(), QuizActivity.class);
        return intent;
    }


    int randomNum = 0;
    int trueCount = 0;

    @BindView(R.id.txt_question)
    TextView txtQuestion;

    @BindView(R.id.et_answer)
    EditText etAnswer;

    @BindView(R.id.txt_answer)
    TextView txtAnswer;

    @BindView(R.id.txt_result)
    TextView txtResult;

    @BindView(R.id.btn_done)
    Button btnDone;

    @BindView(R.id.btn_show)
    Button btnShow;

    @Override
    protected void onStart() {
        super.onStart();
        setData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this, this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        randomNum = new Random().nextInt(quizVOList.size() - 0 + 1) + 0;

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans = etAnswer.getText().toString();

                if (btnDone.getText().toString().equalsIgnoreCase("next")) {
                    setData();
                } else {
                    if (check(ans)) {
                        btnShow.setVisibility(View.INVISIBLE);
                        txtResult.setVisibility(View.VISIBLE);
                        txtResult.setText(R.string.txt_true);
                        btnDone.setText(R.string.btn_next);

                    } else {

                        btnShow.setVisibility(View.VISIBLE);

                        etAnswer.setHint(R.string.txt_false);
                        etAnswer.setText("");
                        Animation shake = AnimationUtils.loadAnimation(QuizActivity.this, R.anim.shake);
                        etAnswer.startAnimation(shake);

                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnShow.setVisibility(View.INVISIBLE);
                etAnswer.setVisibility(View.INVISIBLE);
                txtAnswer.setVisibility(View.VISIBLE);

                txtAnswer.setText("Answer: " + quizVOList.get(randomNum).getAnswer());
                etAnswer.setHint(R.string.et_hint);
                btnDone.setText(R.string.btn_next);
            }
        });

    }

    public void setData() {
        txtAnswer.setVisibility(View.INVISIBLE);
        etAnswer.setVisibility(View.VISIBLE);

        etAnswer.setText("");
        etAnswer.setHint(R.string.et_hint);
        if (randomNum == quizVOList.size() - 1) {
            randomNum = 0;
        } else {
            randomNum++;
        }
        txtQuestion.setText(quizVOList.get(randomNum).getQuestion());
        btnDone.setText(R.string.btn_done);
        txtResult.setVisibility(View.INVISIBLE);
    }

    public boolean check(String ans) {

        String answer = quizVOList.get(randomNum).getAnswer();
        String contain = quizVOList.get(randomNum).getContain();


        if (ans.equalsIgnoreCase(answer) || ans.contains(contain)) {
            trueCount++;
            return true;
        }
        return false;
    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        new AlertDialog.Builder(this)
//                .setTitle("Scroe Point")
//                .setMessage("point")
//                .show();
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//        }
//    }
}
