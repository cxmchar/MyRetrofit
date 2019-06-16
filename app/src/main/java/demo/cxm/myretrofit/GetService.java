package demo.cxm.myretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    @GET("福利/20")
    Call<ResponseBody> getData();
    @GET("福利/20")
    Call<Bean> getGirl();

}
