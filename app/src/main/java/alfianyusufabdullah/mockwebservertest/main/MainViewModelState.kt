package alfianyusufabdullah.mockwebservertest.main

import alfianyusufabdullah.mockwebservertest.entity.UserItem

sealed class MainViewModelState

object Search : MainViewModelState()
data class Empty(val username: String) : MainViewModelState()
data class Result(val users: MutableList<UserItem>) : MainViewModelState()
data class Error(val message: String) : MainViewModelState()