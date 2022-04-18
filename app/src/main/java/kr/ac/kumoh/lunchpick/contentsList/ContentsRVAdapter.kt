package kr.ac.kumoh.lunchpick.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.R

class ContentsRVAdapter(val context: Context, val items : ArrayList<ContentsModel>) :
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
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val Name = itemView.findViewById<TextView>(R.id.storeName)
            val Address = itemView.findViewById<TextView>(R.id.storeAddress)
            /*context란 이 액티비티에서 사용하는 맥락(?)이다
            * load(item.imageUrl을 imageViewArea에 집어 넣겠다*/

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)
            Name.text = item.storeName
            Address.text = item.storeAddress

            //각 리스트 클릭시 이벤트
            itemView.setOnClickListener {
                //putExtra와 Parcelize로 item clss를 넘겨준다.
                Intent(context, ContentsDetailActivity::class.java).apply{
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this)}
            }

        }
    }
}