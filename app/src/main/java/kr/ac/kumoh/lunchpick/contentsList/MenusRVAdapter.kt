package kr.ac.kumoh.lunchpick.contentsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import kr.ac.kumoh.lunchpick.R

class MenusRVAdapter(
    val context: Context,
    private val store: StoreViewModel,
) :
    RecyclerView.Adapter<MenusRVAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenusRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MenusRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(store.getMenu(position))
    }

    override fun getItemCount(): Int {
        return store.getMenuSize()
    }
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val SERVER_URL = "https://csproject-qejmc.run.goorm.io/"

        fun bindItems(item : MenusModel){
            val imageViewArea = itemView.findViewById<NetworkImageView>(R.id.iv_FoodImg)
            val Name = itemView.findViewById<TextView>(R.id.tv_foodName)
            /*context란 이 액티비티에서 사용하는 맥락(?)이다
            * load(item.imageUrl을 imageViewArea에 집어 넣겠다*/

            /*Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)*/
            imageViewArea.setImageUrl("${SERVER_URL}images/${item.menuImage}"
                ,store.mImageLoader)
            Name.text = item.menuName

        }
    }
}