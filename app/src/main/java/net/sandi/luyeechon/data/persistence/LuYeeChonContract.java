package net.sandi.luyeechon.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import net.sandi.luyeechon.LuYeeChonApp;

public class LuYeeChonContract {


    public static final String CONTENT_AUTHORITY = LuYeeChonApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_HEALTHS = "healths";
    public static final String PATH_JOKES = "jokes";
    public static final String PATH_MOTIVATOR = "motivator";

    public static final class HealthEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HEALTHS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTHS;

        public static final String TABLE_NAME = "healths";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PHOTO = "photo";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_TYPE = "type";



        public static Uri buildHealthUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHealthUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, attractionTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }

    }

    public static final class JokeEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_JOKES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JOKES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_JOKES;

        public static final String TABLE_NAME = "jokes";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_PHOTO = "photo";
        public static final String COLUMN_DESC = "desc";

        public static Uri buildJokeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildJokeUriWithTitle(String attractionTitle) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions?title="Yangon"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, attractionTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

    public static final class MotivatorEntry implements BaseColumns {  // Entry object for each path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOTIVATOR).build();

        public static final String DIR_TYPE =  //dir -> directory .. if return is more than one, return with DIR_TYPE
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOTIVATOR;

        public static final String ITEM_TYPE =  //if return is more than one, return with ITEM_TYPE
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOTIVATOR;

        public static final String TABLE_NAME = "motivator";

        public static final String COLUMN_TITLE = "image_url";

        public static Uri buildMotivatorUri(long id) {    // create it in every entry

            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getTitleFromParam(Uri uri) {   //retrieve title form uri

            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }

}


