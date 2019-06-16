package demo.cxm.myretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /*
     * 简单的利用retrofit + rxjava
     * 使用gankio api
     * by cxm
     */

    private RecyclerView recyclerView;
    private ArrayList<Bean.ResultsBean> beanArrayList = new ArrayList<>();
    private MyAdapter myAdapter;
    private String TAG = "MAIN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置adapter
        recyclerView = findViewById(R.id.recycle_rv);
        recyclerView.setHasFixedSize(true);
        //设置双行的瀑布流
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置recycleviewmanager
        myAdapter = new MyAdapter(this, beanArrayList);
        recyclerView.setAdapter(myAdapter);
        //接下来进行数据处理
        //使用rx

        //Call<Bean> beanCall = RetrofitManager.getApi().getGirl();

        // 这里一开始没有使用rxjava
        final Gson gson = new Gson();
        Call<ResponseBody> call = RetrofitManager.getApi().getData();
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: " + "SUCCESS");
                try {
                    String date = response.body().string();
                    //retrofit本身就可以完成解析json的操作，这里是冗余的
                    Bean bean = gson.fromJson(date,Bean.class);
                    beanArrayList = (ArrayList<Bean.ResultsBean>) bean.getResults();
                    //刷新数据
                    myAdapter.addListChapter(beanArrayList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure: " + "FAILED");
            }
        });

    }

}
