package kr.ac.kumoh.lunchpick.contentsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.lunchpick.R

class ContentsRVAdapter(val items : ArrayList<ContentsModel>) :
    RecyclerView.Adapter<ContentsRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentsRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentsRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item : ContentsModel){
            val imageUrl = itemView.findViewById<ImageView>(R.id.imageArea)
            val Name = itemView.findViewById<TextView>(R.id.storeName)
            val Address = itemView.findViewById<TextView>(R.id.storeAddress)

            Name.text = item.storeName
            Address.text = item.storeAddress
        }
    }

}