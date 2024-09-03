package core.data.common.exception

data object NetworkException : Throwable() {
    private const val ANDROID_NETWORK_ERROR_MESSAGE = "java.net.ConnectException"
    private const val IOS_NETWORK_ERROR_MESSAGE = "No network route"

    fun isNetworkError(throwable: Throwable): Boolean {
        if (ANDROID_NETWORK_ERROR_MESSAGE in throwable.stackTraceToString()) return true
        if (IOS_NETWORK_ERROR_MESSAGE in throwable.stackTraceToString()) return true

        return false
    }
}
