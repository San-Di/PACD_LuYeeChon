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
 * Created by UNiQUE on 9/18/2016.
 */
public class HealthVO {

    @SerializedName("type")
    private String type;

    @SerializedName("health_title")
    private String healthTitle;

    @SerializedName("health_des")
    private String healthDes;

    @SerializedName("health_photo")
    private String image;

    public HealthVO(String healthTitle, String healthDes, String image) {
        this.healthTitle = healthTitle;
        this.healthDes = healthDes;
        this.image = image;
    }

    public HealthVO(String healthTitle, String healthDes) {
        this.healthTitle = healthTitle;
        this.healthDes = healthDes;
    }

    public HealthVO(){}


    public String getHealthTitle() {
        return healthTitle;
    }

    public String getHealthDes() {
        return healthDes;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }


    public static void saveHealths(List<HealthVO> healthList) {
        Context context = LuYeeChonApp.getContext();
        ContentValues[] healthCVs = new ContentValues[healthList.size()];
        for (int index = 0; index < healthList.size(); index++) {
            HealthVO health = healthList.get(index);
            healthCVs[index] = health.parseToContentValues();

            //Bulk insert into attraction_images.
        }

        //Bulk insert into attractions.
        int insertedCount = context.getContentResolver().bulkInsert(LuYeeChonContract.HealthEntry.CONTENT_URI, healthCVs);

        Log.d(LuYeeChonApp.TAG, "Bulk inserted into health table : " + insertedCount);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_TYPE, type);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_TITLE, healthTitle);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_DESC, healthDes);
        cv.put(LuYeeChonContract.HealthEntry.COLUMN_PHOTO,image);
        return cv;
    }

    public static HealthVO parseFromCursor(Cursor data) {
        HealthVO health = new HealthVO();
        health.type = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_TYPE));
        health.healthTitle = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_TITLE));
        health.healthDes = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_DESC));
        health.image = data.getString(data.getColumnIndex(LuYeeChonContract.HealthEntry.COLUMN_PHOTO));
        return health;
    }
}
