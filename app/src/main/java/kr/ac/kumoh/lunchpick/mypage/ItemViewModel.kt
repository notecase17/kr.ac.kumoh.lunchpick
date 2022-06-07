package kr.ac.kumoh.lunchpick.mypage

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import kr.ac.kumoh.lunchpick.VolleySingleton
import kr.ac.kumoh.lunchpick.contentsList.ContentsModel
import kr.ac.kumoh.lunchpick.contentsList.StoreViewModel
import kr.ac.kumoh.lunchpick.contentsList.VolleyRequest
import kr.ac.kumoh.lunchpick.sharedPreference.LocalUser
import org.json.JSONArray
import org.json.JSONObject

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val QUEUE_TAG = "VolleyRequest"
    }

    private lateinit var mQueue: RequestQueue

    //Volley image
    lateinit var mImageLoader: ImageLoader

    val winnerList = MutableLiveData<ArrayList<ItemModel>>()
    private val winner = ArrayList<ItemModel>()

    val likeList = MutableLiveData<ArrayList<ItemModel>>()
    private val like = ArrayList<ItemModel>()

    val dislikeList = MutableLiveData<ArrayList<ItemModel>>()
    private val dislike = ArrayList<ItemModel>()

    init {
        winnerList.value = winner
        likeList.value = like
        dislikeList.value = dislike
        mQueue = VolleyRequest.getInstance(application).requestQueue
        mImageLoader = VolleyRequest.getInstance(application).imageLoader
    }

    override fun onCleared() {
        super.onCleared()
        mQueue.cancelAll(StoreViewModel.QUEUE_TAG)
    }

    fun getWinner(i: Int) = winner[i]
    fun getWinnerSize() = winner.size

    fun getLike(i: Int) = like[i]
    fun getLikeSize() = like.size

    fun getDislike(i: Int) = dislike[i]
    fun getDislikeSize() = dislike.size

    fun requestItemList(category: String) {
        val url = "https://csproject-qejmc.run.goorm.io/tasteList"

        val param = JSONObject()
        param.put("user_ID", LocalUser.prefs.getString("id", ""))
        param.put("category", category)

        val params = JSONArray()
        params.put(param)

        val request = JsonArrayRequest(
            Request.Method.POST,
            url,
            params,
            {
                if (category == "Winner") {
                    winner.clear()
                    parseItemJson(it, category)
                    winnerList.value = winner
                }
                if (category == "Like") {
                    like.clear()
                    parseItemJson(it, category)
                    likeList.value = like
                }
                if (category == "Dislike") {
                    dislike.clear()
                    parseItemJson(it, category)
                    dislikeList.value = dislike
                }

            },
            {
                Log.e("volley", "failed")
            }
        )
        request.tag = QUEUE_TAG
        mQueue.add(request)
    }

    private fun parseItemJson(items: JSONArray, type: String) {
        for (i in 0 until items.length()) {
            val item: JSONObject = items.getJSONObject(i)

            val itemName = item.getString("item_name")
            val isStore = item.getBoolean("isStore")
            val itemID = item.getInt("item_id")
            val itemImage = item.getString("item_image")

            if (type == "Winner") winner.add(ItemModel(itemName, isStore, itemID, itemImage))
            if (type == "Like") like.add(ItemModel(itemName, isStore, itemID, itemImage))
            if (type == "Dislike") dislike.add(ItemModel(itemName, isStore, itemID, itemImage))
        }
    }

    fun requestDeleteItem(category: String, item_id: Int) {
        val url = "https://csproject-qejmc.run.goorm.io/deleteTasteItem"

        val param = JSONObject()
        param.put("category", category)
        param.put("user_id", LocalUser.prefs.getString("id", ""))
        param.put("item_id", item_id.toString())

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            param,
            {
                val response = it.getString("result")
                Log.e("volley", response)
            },
            {
                Log.e("volley", "failed")
            }
        )
        request.tag = QUEUE_TAG
        mQueue.add(request)
    }
}