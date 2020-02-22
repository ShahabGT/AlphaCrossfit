package ir.shahabazimi.alphacrossfit.data;

import ir.shahabazimi.alphacrossfit.models.DetailsResponse;
import ir.shahabazimi.alphacrossfit.models.GeneralResponse;
import ir.shahabazimi.alphacrossfit.models.PointsResponse;
import ir.shahabazimi.alphacrossfit.models.StatResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("register.php")
    Call<GeneralResponse> register(
            @Field("name") String name,
            @Field("number") String number,
            @Field("bday") String birthday,
            @Field("code") String code);


    @FormUrlEncoded
    @POST("search.php")
    Call<GeneralResponse> search(
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("buy.php")
    Call<GeneralResponse> buy(
            @Field("user_id") String userId,
            @Field("amount") String total,
            @Field("wallet") String wallet,
            @Field("pay") String pay,
            @Field("type") String type,
            @Field("length") String length,
            @Field("length_type") String lengthType);

    @FormUrlEncoded
    @POST("sendsms.php")
    Call<GeneralResponse> sendsms(
            @Field("text") String text
    );

    @GET("getpoints.php")
    Call<PointsResponse> getpoints();


    @FormUrlEncoded
    @POST("setpoints.php")
    Call<GeneralResponse> setpoints(
            @Field("point") String point,
            @Field("wallet") String wallet,
            @Field("point2") String point2

    );

    @FormUrlEncoded
    @POST("getuserpoints.php")
    Call<GeneralResponse> getuserpoints(
            @Field("code") String code
    );

    @FormUrlEncoded
    @POST("setuserpoints.php")
    Call<GeneralResponse> setuserpoints(
            @Field("user_id") String userId,
            @Field("points") String points

    );

    @FormUrlEncoded
    @POST("getstats.php")
    Call<StatResponse> getstats(
            @Field("year") String year
    );

    @FormUrlEncoded
    @POST("getmonth.php")
    Call<StatResponse> getmonth(
            @Field("year") String year,
            @Field("month") String month
    );

    @FormUrlEncoded
    @POST("getdetails.php")
    Call<DetailsResponse> getdetails(
            @Field("year") String year,
            @Field("month") String month,
            @Field("name") String name
    );

}
