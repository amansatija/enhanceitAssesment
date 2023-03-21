package com.example.enhanceittechincaltest.di

import android.content.Context
import com.example.enhanceittechincaltest.di.providers.NavigationProvider
import com.example.enhanceittechincaltest.RepoTvShows
import com.example.enhanceittechincaltest.base.app.AppConfig
import com.example.enhanceittechincaltest.base.app.AppID
import com.example.enhanceittechincaltest.data.domain.tvshow.SearchTvShowsUseCase
import com.example.enhanceittechincaltest.data.remote.TvShowRemoteDataSource
import com.example.enhanceittechincaltest.di.providers.NavigationProviderImpl
import com.example.enhanceittechincaltest.di.providers.ResourceProvider
import com.example.enhanceittechincaltest.di.providers.ResourceProviderImpl
import com.example.enhanceittechincaltest.utils.api.ApiClientProvider
import com.example.enhanceittechincaltest.utils.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationProviderImpl(@ApplicationContext context: Context): NavigationProvider {
        return NavigationProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideResourceProviderImpl(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideApplication(): AppID {
        return AppID.instance
    }

    @Provides
    @Singleton
    fun provideAppConfig(app: AppID): AppConfig {
        return app.appConfig()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = ApiClientProvider.getInstance().retrofit

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = ApiClientProvider.getApiClient()

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(apiService: ApiService) = TvShowRemoteDataSource(apiService)

//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)
//
//    @Singleton
//    @Provides
//    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideTvShowRepository(remoteDataSource: TvShowRemoteDataSource,
//                          localDataSource: CharacterDao
    ) =
        RepoTvShows(remoteDataSource,
//            localDataSource
            )

    @Singleton
    @Provides
    fun provideSearchTvShow(repository: RepoTvShows) = SearchTvShowsUseCase(repository)
}