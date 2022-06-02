package com.example.tmdb.data



import com.example.tmdb.network.KtorClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val httpClientModule = module {
    single { KtorClient.httpClient }
}

val FavoritesModule= module{
    viewModel { params ->
        FavoritesViewModel(movieRespository = get())
    }
}
val homeModule = module{
    viewModel {
        HomeViewModel(movieRepository = get())
    }
}

val DetailsModule= module{
    viewModel{ params ->
        DetailsViewModel(get(), movieId = params.get() )
    }
}

val favoriteDatabaseModule= module{
    single<FavoriteMoviesDatabase>{
        FavoriteMoviesDatabase()
    }
}

val repositoryModule= module{
    single<MovieRepository>{
        MovieRepositoryImpl(
            movieApi = get<MovieApi>(),
            favoriteDatabase = get<FavoriteMoviesDatabase>()
        )

    }
}

val apiModule= module {
    single<MovieApi>{
        MovieApiImpl(get())
    }
}