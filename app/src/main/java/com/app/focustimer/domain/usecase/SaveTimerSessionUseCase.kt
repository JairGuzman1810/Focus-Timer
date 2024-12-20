package com.app.focustimer.domain.usecase

import com.app.focustimer.domain.model.Resource
import com.app.focustimer.domain.model.TimerSessionModel
import com.app.focustimer.domain.repository.LocalStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * SaveTimerSessionUseCase saves a timer session to local storage.
 *
 * This class encapsulates the logic for saving a timer session and handling the different states of the operation.
 * This class uses constructor injection for its dependencies, facilitated by Dagger Hilt.
 *
 * @constructor Injects the LocalStorageRepository dependency.
 * @param repository The LocalStorageRepository used to access the data.
 */
class SaveTimerSessionUseCase @Inject constructor(
    private val repository: LocalStorageRepository
) {

    /**
     * Executes the use case to save a timer session.
     *
     * @param timerSessionModel The TimerSessionModel to save.
     * @return A Flow of Resource<Boolean> representing the state of the operation.
     *         It emits:
     *         - Resource.Loading: When the operation starts.
     *         - Resource.Success: When the operation is successful, containing a Boolean indicating success.
     *         - Resource.Error: When an error occurs, containing the error message.
     */
    operator fun invoke(timerSessionModel: TimerSessionModel): Flow<Resource<Boolean>> = flow {
        try {
            // Emit a Loading state to indicate that the operation has started.
            emit(Resource.Loading())

            // Emit a Success state with the result of the save operation.
            emit(
                Resource.Success(
                    data = repository.saveTimerSession(timerSessionModel)
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