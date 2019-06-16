package demo.cxm.myretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static String Base_Url = "http://gank.io/api/random/data/";
    private static GetService api;

    public static GetService getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())  //默认解析 关于解析顺序 先设置则先解析
                    .build();
            api = (GetService) retrofit.create(GetService.class);
        }
        return  api;
    }
}
