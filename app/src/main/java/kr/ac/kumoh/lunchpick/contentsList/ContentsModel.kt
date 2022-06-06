package kr.ac.kumoh.lunchpick.contentsList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentsModel(
        val ownerId: String = "",
        val storeId: Int,
        val storeName: String = "",
        val storeAddress: String = "",
        val storeNum: String = "",
        val image: String = ""
        //val menus: MutableList<MenusModel>
) : Parcelable

@Parcelize
data class MenusModel (
        val storeMenuId : Int,
        val storeId: Int,
        val menuName : String ="",
        val menuImage : String = "",
        val price : Int
) : Parcelable