package net.sandi.luyeechon.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;

import java.util.List;

/**
 * Created by UNiQUE on 9/19/2016.
 */
public class JokeVO {

    @SerializedName("joke_title")
    private String jokeTitle;

    @SerializedName("joke_desc")
    private String jokeDes;

    @SerializedName("joke_photo")
    private String imageJoke;

    public JokeVO(String jokeTitle, String jokeDes, String imageJoke) {
        this.jokeTitle = jokeTitle;
        this.jokeDes = jokeDes;
        this.imageJoke = imageJoke;
    }

    public JokeVO(String jokeTitle, String jokeDes) {
        this.jokeTitle = jokeTitle;
        this.jokeDes = jokeDes;
    }

    public JokeVO(){}



    public String getJokeTitle() {
        return jokeTitle;
    }

    public String getJokeDes() {
        return jokeDes;
    }

    public String getImageJoke() {
        return imageJoke;
    }

    public static void saveJokes(List<JokeVO> jokeList) {
        Context context = LuYeeChonApp.getContext();
        ContentValues[] jokeCVs = new ContentValues[jokeList.size()];
        for (int index = 0; index < jokeList.size(); index++) {
            JokeVO joke = jokeList.get(index);
            jokeCVs[index] = joke.parseToContentValues();


            //Bulk insert into attraction_images.
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.JokeEntry.CONTENT_URI, jokeCVs);

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into joke table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_TITLE, jokeTitle);
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_DESC, jokeDes);
        cv.put(LuYeeChonContract.JokeEntry.COLUMN_PHOTO,imageJoke);
        return cv;
    }

    public static JokeVO parseFromCursor(Cursor data) {
        JokeVO joke = new JokeVO();
        joke.jokeTitle = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_TITLE));
        joke.jokeDes = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_DESC));
        joke.imageJoke = data.getString(data.getColumnIndex(LuYeeChonContract.JokeEntry.COLUMN_PHOTO));
        return joke;
    }

}
