package com.lzp.retrofitrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView textView;

    private Subscriber<HttpResult<MovieBeen>> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textView.setText("Bye Human!");
    }

    @OnClick(R.id.button)
    public void getMovie(){
        subscriber = new Subscriber<HttpResult<MovieBeen>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                textView.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpResult<MovieBeen> movieBeen) {
//                textView.setText(movieBeen.getTitle());
                try{
                    textView.setText(movieBeen.getData().getTitle());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };
        HttpMethods.getInstance().getTopMovie(subscriber, 0, 10);

    }

//    @OnClick(R.id.button)
//    public void getMovie(){
//        String baseUrl = "https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        MovieModel movieModel = retrofit.create(MovieModel.class);
//
//        Observable<MovieBeen> observable = movieModel.getTopMovie(0,10);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<MovieBeen>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        textView.setText(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(MovieBeen movieBeen) {
//                        textView.setText(movieBeen.getTitle());
//                    }
//                });
//    }



//    @OnClick(R.id.button)
//    public void getMovie(){
//        String baseUrl = "https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        MovieModel movieModel = retrofit.create(MovieModel.class);
//        Call<MovieBeen> call = movieModel.getTopMovie(0,10);
//        call.enqueue(new Callback<MovieBeen>() {
//            @Override
//            public void onResponse(Call<MovieBeen> call, Response<MovieBeen> response) {
//                textView.setText(response.body().getTitle);
//            }
//
//            @Override
//            public void onFailure(Call<MovieBeen> call, Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }


}
