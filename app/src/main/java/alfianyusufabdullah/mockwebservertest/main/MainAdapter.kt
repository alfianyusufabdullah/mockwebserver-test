package alfianyusufabdullah.mockwebservertest.main

import alfianyusufabdullah.mockwebservertest.R
import alfianyusufabdullah.mockwebservertest.entity.UserItem
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_user.view.*

class MainAdapter(private var users: MutableList<UserItem>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun submitList(fill: MutableList<UserItem>){
        users.clear()
        users.addAll(fill)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() = users.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(user: UserItem) {
            itemView.rowUsername.text = user.login
            itemView.rowGithubUrl.text = user.htmlUrl

            itemView.rowAvatar.load(user.avatar){
                crossfade(true)
                transformations(RoundedCornersTransformation(25f))
            }
        }
    }
}