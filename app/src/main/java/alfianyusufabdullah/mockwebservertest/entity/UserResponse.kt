package alfianyusufabdullah.mockwebservertest.entity

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("items")
    val users: List<UserItem> = listOf()
)