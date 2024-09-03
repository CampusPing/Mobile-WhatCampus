package core.model

sealed class Response<out T> {

    data class Success<T>(val body: T) : Response<T>()

    sealed class Failure : Response<Nothing>() {

        data object ClientError : Failure()

        data object ServerError : Failure()

        data object NetworkError : Failure()

        data class OtherError<E : Throwable>(val error: E) : Failure()
    }

    fun <R> map(transform: (T) -> R): Response<R> = when (this) {
        is Success -> Success(transform(body))
        is Failure -> this
    }
}
