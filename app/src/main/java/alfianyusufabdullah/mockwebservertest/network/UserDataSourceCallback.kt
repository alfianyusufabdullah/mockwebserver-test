package alfianyusufabdullah.mockwebservertest.network

import alfianyusufabdullah.mockwebservertest.entity.UserItem

interface UserDataSourceCallback {
    fun onResponse(users: List<UserItem>)
    fun onFailed(error: String)
}