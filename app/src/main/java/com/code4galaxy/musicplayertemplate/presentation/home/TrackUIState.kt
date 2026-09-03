package com.code4galaxy.musicplayertemplate.presentation.home


/**
 * Represents the possible states of an asynchronous UI operation.
 *
 * @param T Type of data returned when the operation succeeds.
 */
sealed interface UiState<out T> {


    /**
     * Indicates initial stage of an application.
     */
    data object Idle : UiState<Nothing>


    /**
     * Indicates that an operation is currently in progress.
     */
    data object Loading : UiState<Nothing>


    /**
     * Indicates that an operation completed successfully.
     *
     * @property data Data produced by the operation.
     */
    data class Success<T>(
        val data: T
    ) : UiState<T>



    /**
     * Indicates that an operation failed.
     *
     * @property message description of the error.
     */
    data class Error(
        val message: String
    ) : UiState<Nothing>
}