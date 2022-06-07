package kr.ac.kumoh.lunchpick.mypage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val name: String = "",
    val isStore: Boolean = false,
    val itemID: Int = 0,
    val image: String = ""
) : Parcelable