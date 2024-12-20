package com.app.focustimer.domain.model

/**
 * Resource is a sealed class that represents the state of a network or data operation.
 *
 * It is used to encapsulate the different states of an operation: Loading, Success, or Error.
 * This is a common pattern for handling asynchronous operations in Android.
 *
 * @param T The type of data that the Resource may contain.
 * @property data The data associated with the operation, if any.
 * @property message An error message, if the operation failed.
 */
sealed class Resource<T>(val data: T? = null, private val message: String? = null) {

    /**
     * Loading state: Indicates that the operation is in progress.
     *
     * @param T The type of data that the Resource may contain.
     * @param data Optional data that may be available during the loading state.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)

    /**
     * Success state: Indicates that the operation completed successfully.
     *
     * @param T The type of data that the Resource contains.
     * @param data The data returned by the successful operation.
     */
    class Success<T>(data: T?) : Resource<T>(data)

    /**
     * Error state: Indicates that the operation failed.
     *
     * @param T The type of data that the Resource may contain.
     * @param message The error message.
     * @param data Optional data that may be available even in the error state.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}