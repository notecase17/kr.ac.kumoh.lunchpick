package kr.ac.kumoh.lunchpick.contentsList

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContentsModel (
        val imageUrl : String ="",
        val storeName : String = "",
        val storeAddress : String = ""
) : Parcelable