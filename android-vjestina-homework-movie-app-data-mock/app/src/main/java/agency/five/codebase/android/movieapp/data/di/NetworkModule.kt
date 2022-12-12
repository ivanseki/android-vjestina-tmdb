package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.network.MovieServiceImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val networkModule = module {
    single<MovieService> { MovieServiceImpl(client = get()) }

    single {
        HttpClient(Android) {
            expectSuccess = true

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }

            install(ContentNegotiation) {
                json()
            }
        }
    }
}