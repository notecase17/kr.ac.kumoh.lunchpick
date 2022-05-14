package kr.ac.kumoh.lunchpick.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.databinding.ActivityContentsListBinding
import kotlin.properties.ReadOnlyProperty

class ContentsListActivity : AppCompatActivity() {

<<<<<<< HEAD
=======
    private lateinit var binding: ActivityContentsListBinding
    private val store: StoreViewModel by viewModels()
    private lateinit var adapter: ContentsRVAdapter


>>>>>>> 309777ad89f4562ac6113e74ef65f92331960325
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContentsRVAdapter(baseContext, store)
        binding.rv.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@ContentsListActivity.adapter
        }


        store.storeList.observe(this, {
            adapter.notifyDataSetChanged()
        })


        store.requestStoreList()

    }
    private fun adapterOnClick(store: ContentsModel): Unit {
        Toast.makeText(this, store.storeName, Toast.LENGTH_SHORT).show()
    }

}