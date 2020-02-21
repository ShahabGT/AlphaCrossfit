package ir.shahabazimi.alphacrossfit.models;

import com.google.gson.annotations.SerializedName;

public class DetailsModel {

//     "user_id": "1",
//             "user_name": "شهاب",
//             "user_code": "452358",
//             "user_number": "09355452358",
//             "user_bday": "1370/2/8",
//             "user_reg": "2020-02-17 00:13:04"
//     "buy_id": "1",
//             "price": "60000",
//             "wallet": "0",
//             "pay": "60000",
//             "name": "کاراته",
//             "length": "2",
//             "lenght_type": "ماه",
//             "date": "2020-02-17 17:02:21"

    @SerializedName("user_id")
    private String userId;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("user_code")
    private String userCode;

    @SerializedName("user_number")
    private String userNumber;

    @SerializedName("userBday")
    private String user_bday;

    @SerializedName("user_reg")
    private String userReg;

    @SerializedName("buy_id")
    private String buyId;

    @SerializedName("price")
    private String price;

    @SerializedName("wallet")
    private String wallet;

    @SerializedName("pay")
    private String pay;

    @SerializedName("name")
    private String name;

    @SerializedName("length")
    private String length;

    @SerializedName("lenght_type")
    private String lenghtType;

    @SerializedName("date")
    private String date;


}
