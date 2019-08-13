package alfianyusufabdullah.mockwebservertest.entity

import com.google.gson.annotations.SerializedName

data class UserItem(

	@field:SerializedName("avatar_url")
	val avatar: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null
)