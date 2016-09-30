package net.sandi.luyeechon.data.agents;

import net.sandi.luyeechon.data.responses.HealthListResponse;
import net.sandi.luyeechon.data.responses.JokeListResponse;
import net.sandi.luyeechon.data.responses.MotivatorListResponse;
import net.sandi.luyeechon.utils.CommonConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by UNiQUE on 9/26/2016.
 */
public interface LuYeeChonApi {

    @FormUrlEncoded
    @POST(CommonConstants.API_GET_HEALTH_LIST)
    Call<HealthListResponse> loadHealthList(
            @Field(CommonConstants.PARAM_ACCESS_TOKEN) String param1);

    @FormUrlEncoded
    @POST(CommonConstants.API_GET_JOKE_LIST)
    Call<JokeListResponse> loadJokeList(
            @Field(CommonConstants.PARAM_ACCESS_TOKEN) String param2);

    @FormUrlEncoded
    @POST(CommonConstants.API_GET_MOTIVATOR_LIST)  //@POST type of call method
    Call<MotivatorListResponse> loadMotivator(  //if parsed json will return AttratinListResponse coz of api
                                                @Field(CommonConstants.PARAM_ACCESS_TOKEN) String param);  //request parameter
}
