package net.sandi.luyeechon.data.agents;

import net.sandi.luyeechon.data.models.HealthModel;
import net.sandi.luyeechon.data.models.JokeModel;
import net.sandi.luyeechon.data.models.MotivatorModel;
import net.sandi.luyeechon.data.responses.HealthListResponse;
import net.sandi.luyeechon.data.responses.JokeListResponse;
import net.sandi.luyeechon.data.responses.MotivatorListResponse;
import net.sandi.luyeechon.utils.CommonConstants;
import net.sandi.luyeechon.utils.CommonInstances;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by UNiQUE on 9/25/2016.
 */
public class RetrofitDataAgent implements DataAgent{


    private static RetrofitDataAgent objInstance;

    private final LuYeeChonApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonConstants.APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(LuYeeChonApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }


    @Override
    public void loadHealthList() {
        Call<HealthListResponse> loadHealthCall = theApi.loadHealthList(CommonConstants.ACCESS_TOKEN);
        loadHealthCall.enqueue(new Callback<HealthListResponse>() {
            @Override
            public void onResponse(Call<HealthListResponse> call, Response<HealthListResponse> response) {
                HealthListResponse healthListResponse = response.body();
                if (healthListResponse == null) {
                    HealthModel.getInstance().notifyErrorInLoadingHealths(response.message());
                } else {
                    HealthModel.getInstance().notifyHealthListLoaded(healthListResponse.getHealthList());

                }
            }


        @Override
        public void onFailure(Call<HealthListResponse> call, Throwable throwable) {
            HealthModel.getInstance().notifyErrorInLoadingHealths(throwable.getMessage());
        }
    });

    }
    @Override
    public void loadJokeList() {
        Call<JokeListResponse> loadJokeCall = theApi.loadJokeList(CommonConstants.ACCESS_TOKEN);
        loadJokeCall.enqueue(new Callback<JokeListResponse>() {
            @Override
            public void onResponse(Call<JokeListResponse> call, Response<JokeListResponse> response) {
                JokeListResponse jokeListResponse = response.body();
                if (jokeListResponse == null) {
                    JokeModel.getInstance().notifyErrorInLoadingJokes(response.message());
                } else {
                    JokeModel.getInstance().notifyJokeListLoaded(jokeListResponse.getJokeList());
                }
            }


            @Override
            public void onFailure(Call<JokeListResponse> call, Throwable throwable) {
                JokeModel.getInstance().notifyErrorInLoadingJokes(throwable.getMessage());
            }
        });

    }

    @Override
    public void loadMotivator() {

        Call<MotivatorListResponse> loadMotivatorCall = theApi.loadMotivator(CommonConstants.ACCESS_TOKEN);
        loadMotivatorCall.enqueue(new Callback<MotivatorListResponse>() {  //preparatin state  before request
            @Override
            public void onResponse(Call<MotivatorListResponse> call, Response<MotivatorListResponse> response) {
                MotivatorListResponse motivatorListResponse = response.body();
                if (motivatorListResponse == null) {
                    MotivatorModel.getInstance().notifyErrorInLoadingMotivator(response.message());
                } else {
                    MotivatorModel.getInstance().notifyMotivatorLoaded(motivatorListResponse.getMotivatorList());
                }
            }

            @Override
            public void onFailure(Call<MotivatorListResponse> call, Throwable throwable) {
                MotivatorModel.getInstance().notifyErrorInLoadingMotivator(throwable.getMessage());
            }
        });
    }
}
