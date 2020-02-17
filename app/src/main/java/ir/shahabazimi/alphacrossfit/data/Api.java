package ir.shahabazimi.alphacrossfit.data;

import ir.shahabazimi.alphacrossfit.models.GeneralResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
