package kr.ac.kumoh.lunchpick.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.contentsList.ContentsDetailActivity

class DislikeRVAdapter (
    val context: Context,
    private val dislike: ItemViewModel,
) :
    RecyclerView.Adapter<DislikeRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DislikeRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.taste_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: DislikeRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(dislike.getDislike(position))
    }

    override fun getItemCount(): Int {
        return dislike.getDislikeSize()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val url = "https://csproject-qejmc.run.goorm.io/"

        fun bindItems(item: ItemModel) {
            val imageViewArea = itemView.findViewById<NetworkImageView>(R.id.imageArea)
            val name = itemView.findViewById<TextView>(R.id.tvItemName)

            imageViewArea.setImageUrl(
                "${url}images/${item.image}", dislike.mImageLoader
            )
            name.text = item.name

            itemView.setOnClickListener {
                Intent(context, ContentsDetailActivity::class.java).apply{
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this)}
            }
        }
    }
}