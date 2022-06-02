package com.example.tmdb.data


import androidx.room.Room
import com.example.tmdb.database.AppDatabase
import com.example.tmdb.database.MovieDao
import com.example.tmdb.network.KtorClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val httpClientModule = module {
    single { KtorClient.httpClient }
}

val FavoritesModule = module {
    viewModel { params ->
        FavoritesViewModel(movieRespository = get())
    }
}
val homeModule = module {
    viewModel {
        HomeViewModel(movieRepository = get())
    }
}

val DetailsModule = module {
    viewModel { params ->
        DetailsViewModel(get(), movieId = params.get())
    }
}

val favoriteDatabaseModule = module {
    single<FavoriteMoviesDatabase> {
        FavoriteMoviesDatabase()
    }
}

val repositoryModule = module {
    single { MovieRepositoryImpl(get(), get(), get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
}

val apiModule = module {
    single<MovieApi> {
        MovieApiImpl(get())
    }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "Movie-database"
        ).build()
    }
    single<MovieDao> {
        val database = get<AppDatabase>()
        database.movieDao()
    }
}
