package kr.ac.kumoh.lunchpick.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kr.ac.kumoh.lunchpick.VolleySingleton
import kr.ac.kumoh.lunchpick.databinding.ActivityMyListBinding
import kr.ac.kumoh.lunchpick.sharedPreference.LocalUser
import org.json.JSONObject

class MyListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMyListBinding
    private val winner: ItemViewModel by viewModels()
    private val like: ItemViewModel by viewModels()
    private val dislike: ItemViewModel by viewModels()

    private lateinit var winnerAdapter: WinnerRVAdapter
    private lateinit var likeAdapter: LikeRVAdapter
    private lateinit var dislikeAdapter: DislikeRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        winnerAdapter = WinnerRVAdapter(baseContext, winner)
        likeAdapter = LikeRVAdapter(baseContext, like)
        dislikeAdapter = DislikeRVAdapter(baseContext, dislike)

        binding.rvWinner.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MyListActivity.winnerAdapter
        }

        binding.rvLikes.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MyListActivity.likeAdapter
        }

        binding.rvDislikes.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MyListActivity.dislikeAdapter
        }

        winner.winnerList.observe(this, { winnerAdapter.notifyDataSetChanged() })
        like.likeList.observe(this, { likeAdapter.notifyDataSetChanged() })
        dislike.dislikeList.observe(this, { dislikeAdapter.notifyDataSetChanged() })

        winner.requestItemList("Winner")
        like.requestItemList("Like")
        dislike.requestItemList("Dislike")
    }
}