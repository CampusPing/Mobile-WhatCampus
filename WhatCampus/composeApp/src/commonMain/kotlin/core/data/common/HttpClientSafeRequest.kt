package core.data.common

import core.data.common.exception.NetworkException
import core.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post

suspend inline fun <reified T> HttpClient.safeGet(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): Response<T> = runCatching {
    get(urlString = urlString, block = block).body<T>()
}.handleFailure()

suspend inline fun <reified T> HttpClient.safePost(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): Response<T> = runCatching {
    post(urlString = urlString, block = block).body<T>()
}.handleFailure()

suspend inline fun <reified T> HttpClient.safePatch(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {},
): Response<T> = runCatching {
    patch(urlString = urlString, block = block).body<T>()
}.handleFailure()

inline fun <T> Result<T>.handleFailure(): Response<T> {
    onFailure { error ->
        return when (error) {
            is ClientRequestException -> Response.Failure.ClientError
            is ServerResponseException -> Response.Failure.ServerError
            is NetworkException -> Response.Failure.NetworkError
            else -> Response.Failure.OtherError(error)
        }
    }

    return Response.Success(getOrThrow())
}
