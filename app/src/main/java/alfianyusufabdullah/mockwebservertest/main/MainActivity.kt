package alfianyusufabdullah.mockwebservertest.main

import alfianyusufabdullah.mockwebservertest.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Observer<MainViewModelState> {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.observeState.observe(this, this)

        mainAdapter = MainAdapter(mutableListOf())

        user_list.hasFixedSize()
        user_list.layoutManager = LinearLayoutManager(this)
        user_list.adapter = mainAdapter

        btn_search.setOnClickListener {
            mainViewModel.doSearch(et_search.text.toString())
        }
    }

    override fun onChanged(state: MainViewModelState?) {
        when (state) {
            is Search -> {
                pb_loading.visible()
                tv_error.gone()
                mainAdapter.submitList(mutableListOf())
            }

            is Empty -> {
                pb_loading.gone()
                tv_error.visible()
                tv_error.text = (String.format(getString(R.string.empty_query), state.username)).makeUsernameBold()
            }

            is Result -> {
                pb_loading.gone()
                mainAdapter.submitList(state.users)
            }

            is Error -> {
                pb_loading.gone()
                tv_error.visible()
                tv_error.text = state.message
            }
        }
    }
}