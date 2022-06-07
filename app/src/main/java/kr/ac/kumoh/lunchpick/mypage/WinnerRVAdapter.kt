package kr.ac.kumoh.lunchpick.mypage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.NetworkImageView
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.VolleySingleton
import kr.ac.kumoh.lunchpick.contentsList.ContentsDetailActivity
import kr.ac.kumoh.lunchpick.sharedPreference.LocalUser
import org.json.JSONObject

class WinnerRVAdapter (
    val context: Context,
    private val winner: ItemViewModel,
) :
    RecyclerView.Adapter<WinnerRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WinnerRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.taste_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: WinnerRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(winner.getWinner(position))
    }

    override fun getItemCount(): Int {
        return winner.getWinnerSize()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val url = "https://csproject-qejmc.run.goorm.io/"

        fun bindItems(item: ItemModel) {
            val imageViewArea = itemView.findViewById<NetworkImageView>(R.id.imageArea)
            val name = itemView.findViewById<TextView>(R.id.tvItemName)
            val btnDel = itemView.findViewById<Button>(R.id.btnDeleteItem)

            imageViewArea.setImageUrl(
                "${url}images/${item.image}", winner.mImageLoader
            )
            name.text = item.name

            btnDel.setOnClickListener {
                winner.requestDeleteItem("Winner", item.itemID)
            }
        }
    }
}