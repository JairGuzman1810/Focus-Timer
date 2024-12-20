package com.app.focustimer.domain.usecase

import com.app.focustimer.domain.model.Resource
import com.app.focustimer.domain.model.TimerSessionModel
import com.app.focustimer.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * GetTimerSessionByDateUseCase retrieves a timer session from local storage based on the provided date.
 *
 * This class encapsulates the logic for retrieving a timer session and handling the different states of the operation.
 * This class uses constructor injection for its dependencies, facilitated by Dagger Hilt.
 *
 * @constructor Injects the LocalStorageRepository dependency.
 * @param repository The LocalStorageRepository used to access the data.
 */
class GetTimerSessionByDateUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {

    /**
     * Executes the use case to retrieve a timer session by date.
     *
     * @param date The date of the timer session to retrieve.
     * @return A Flow of Resource<TimerSessionModel> representing the state of the operation.
     *         It emits:
     *         - Resource.Loading: When the operation starts.
     *         - Resource.Success: When the operation is successful, containing the TimerSessionModel.
     *         - Resource.Error: When an error occurs, containing the error message.
     */
    operator fun invoke(date: String): Flow<Resource<TimerSessionModel>> = flow {
        try {
            // Emit a Loading state to indicate that the operation has started.
            emit(Resource.Loading())

            // Emit a Success state with the data retrieved from the repository.
            emit(
                Resource.Success(
                    data = repository.getTimerSessionByDate(date)
                )
            )
        } catch (e: Exception) {
            // Emit an Error state with the error message.
            emit(
                Resource.Error(e.message ?: "Unknown Error")
            )
        }
    }
}