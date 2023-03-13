package com.example.jetareader.screens.login

data class LoadingState(val status: Status, val message: String? = null) {
    companion object {
        val FAILED = LoadingState(Status.FAILED)
        val IDLE = LoadingState(Status.IDLE)
        val LOADING = LoadingState(Status.LOADING)
        val SUCCESS = LoadingState(Status.SUCCESS)
    }

    enum class Status {
        FAILED,
        IDLE,
        LOADING,
        SUCCESS
    }
}
