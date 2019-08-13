package alfianyusufabdullah.mockwebservertest.network

import alfianyusufabdullah.mockwebservertest.entity.UserResponse
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class UserDataSourceImpl {

    fun search(username: String, userDataSourceCallback: UserDataSourceCallback) {

        AndroidNetworking.get("${UserDataSource.baseUrl}search/users?q=$username")
            .setTag(UserDataSourceImpl::class.java)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(UserResponse::class.java, object : ParsedRequestListener<UserResponse> {
                override fun onResponse(response: UserResponse) {
                    userDataSourceCallback.onResponse(response.users)
                }

                override fun onError(anError: ANError) {
                    Log.d("ERROR", "onError: ${anError.errorBody}", anError)
                    userDataSourceCallback.onFailed("Something error dude")
                }
            })
    }
}