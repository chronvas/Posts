package com.chronvas.btest.utils.network;

import android.content.Context;

import com.chronvas.btest.BuildConfig;
import com.crhonvas.data.remote.api.comments.ICommentsApi;
import com.crhonvas.data.remote.api.posts.IPostsApi;
import com.crhonvas.data.remote.api.users.IUserApi;
import com.crhonvas.domain.DomainSchedulerProvider;
import com.crhonvas.domain.IDomainSchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private static final long CONNECTION_TIMEOUT_IN_SEC = 30;
    private static final long CACHE_SIZE_BYTES = 1024 * 1024 * 2; // 2 MB

    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                             GsonConverterFactory gsonConverterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message));
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Context context) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(CONNECTION_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        builder.readTimeout(CONNECTION_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        Cache cache = new Cache(context.getFilesDir(), CACHE_SIZE_BYTES);
        builder.cache(cache);
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    @Provides
    public IDomainSchedulerProvider provideDomainSchedulerProvider() {
        return new DomainSchedulerProvider();
    }

    @Provides
    public IPostsApi providePostsApi(Retrofit retrofit) {
        return retrofit.create(IPostsApi.class);
    }

    @Provides
    public ICommentsApi provideCommentsApi(Retrofit retrofit) {
        return retrofit.create(ICommentsApi.class);
    }

    @Provides
    public IUserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(IUserApi.class);
    }
}
