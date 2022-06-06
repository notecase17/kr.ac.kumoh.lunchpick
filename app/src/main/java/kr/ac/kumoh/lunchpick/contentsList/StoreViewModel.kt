package kr.ac.kumoh.lunchpick.contentsList

import android.app.Application
import android.graphics.Bitmap
import android.media.Image
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONObject

class StoreViewModel(application: Application) : AndroidViewModel(application) {
    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }

    private lateinit var mQueue: RequestQueue
    //Volley image
    lateinit var mImageLoader: ImageLoader

    val storeList = MutableLiveData<ArrayList<ContentsModel>>()
    private val store = ArrayList<ContentsModel>()
    //추가
    val storeMenuList = MutableLiveData<ArrayList<MenusModel>>()
    private val menu = ArrayList<MenusModel>()

    init {
        storeList.value = store
        //추가
        storeMenuList.value = menu
        //서버 요청 queue
        mQueue = VolleyRequest.getInstance(application).requestQueue
        //Volley 이미지 로더
        mImageLoader = VolleyRequest.getInstance(application).imageLoader
    }
    override fun onCleared() {
        super.onCleared()
        mQueue.cancelAll(QUEUE_TAG)
    }

    fun getStore(i: Int) = store[i]
    fun getSize() = store.size
    //추가
    fun getMenu(i: Int) = menu[i]
    fun getMenuSize() = menu.size

    fun requestStoreList() {

        val url = "https://csproject-qejmc.run.goorm.io/Restaurant"

        val request = JsonArrayRequest(
            Request.Method.GET,url,null,
            {
                //Toast.makeText(getApplication(),it.toString(), Toast.LENGTH_LONG).show()
                store.clear()
                parseStoreJSON(it)
                storeList.value = store
            },
            {
                Toast.makeText(getApplication(),it.toString(), Toast.LENGTH_LONG).show()
                //binding.result.text = it.toString()
            }
        )
        request.tag = QUEUE_TAG
        mQueue.add(request)

    }
    //추가
    fun requestStoreMenuList(flag: Int) {

        val url = "https://csproject-qejmc.run.goorm.io/StoreMenu"

        val request = JsonArrayRequest(
            Request.Method.GET,url,null,
            {
                //Toast.makeText(getApplication(),it.toString(), Toast.LENGTH_LONG).show()
                store.clear()
                parseMenuJSON(it,flag)
                storeMenuList.value = menu
            },
            {
                Toast.makeText(getApplication(),it.toString(), Toast.LENGTH_LONG).show()
                //binding.result.text = it.toString()
            }
        )
        request.tag = QUEUE_TAG
        mQueue.add(request)

    }
    private fun parseStoreJSON(items: JSONArray) {
        for (i in 0 until items.length()) {
            val item: JSONObject = items.getJSONObject(i)
            val o_id = item.getString("owner_ID")
            val s_id = item.getInt("store_ID")
            val name = item.getString("store_name")
            val addr = item.getString("store_addr")
            val num = item.getString("store_num")
            val image = item.getString("store_image")

            store.add(ContentsModel(o_id, s_id, name, addr, num, image))
        }
    }
    //추가
    private fun parseMenuJSON(items: JSONArray, flag : Int) {
        for (i in 0 until items.length()) {
            val item: JSONObject = items.getJSONObject(i)
            if(flag == item.getInt("store_ID"))
            {
                val m_id = item.getInt("storeMenu_ID")
                val s_id = item.getInt("store_ID")
                val name = item.getString("store_Menu")
                val image = item.getString("image")
                val price = item.getInt("price")
                menu.add(MenusModel(m_id, s_id, name, image, price))
            }
        }
    }



}