package alfianyusufabdullah.mockwebservertest.main

import alfianyusufabdullah.mockwebservertest.entity.UserItem
import alfianyusufabdullah.mockwebservertest.network.UserDataSourceImpl
import alfianyusufabdullah.mockwebservertest.network.UserDataSourceCallback
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val observeState = MutableLiveData<MainViewModelState>()

    fun doSearch(username: String) {

        if (username.isEmpty()) {
            observeState.postValue(Error("Username harus diisi terlebih dahulu"))
            return
        }

        observeState.postValue(Search)
        UserDataSourceImpl().search(username, object : UserDataSourceCallback {
            override fun onResponse(users: List<UserItem>) {
                if (users.isEmpty()) {
                    observeState.postValue(Empty(username))
                } else {
                    observeState.postValue(Result(users.toMutableList()))
                }
            }

            override fun onFailed(error: String) {
                observeState.postValue(Error(error))
            }
        })
    }
}