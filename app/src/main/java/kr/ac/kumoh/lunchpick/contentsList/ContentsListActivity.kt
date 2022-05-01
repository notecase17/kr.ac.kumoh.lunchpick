package kr.ac.kumoh.lunchpick.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.lunchpick.R

class ContentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val rv : RecyclerView = findViewById(R.id.rv)

        val items = ArrayList<ContentsModel>()
        //임시 이미지 URL들
        items.add(ContentsModel("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA4MTRfMTA4%2FMDAxNTk3MzM1MDYzNjg2.--DoPDI9mCooc2na4nuK-bUcB7IL1TYXBGtrH65d1ycg.JY1Yw-7lppW-UNoyJZpTpCb_2yrNka5nPHO4H3cYUeAg.JPEG.rainy50%2F1597335066595.jpg",
            "공대닭갈비", "경상북도 구미시 대학로 39 2층"))
        items.add(ContentsModel("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDExMTRfMjc0%2FMDAxNjA1MzMwNjc4MzI2.l49JXCAShHtMytB7toSQyhgBrZyt_WfOeyySxUrnJxgg.dQzzbcRzLNqsTMLak4ll_5X7vWG2oeAQNe8fmf-B3KUg.JPEG.juong4712%2FKakaoTalk_20201114_134913338.jpg",
            "오 곱도리탕","경상북도 구미시 거의동 456 가동 2층 203호"))
        items.add(ContentsModel("https://search.pstatic.net/common/?autoRotate=true&quality=95&type=w750&src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAzMDNfMzUg%2FMDAxNjQ2Mjk0NTQ3ODM5.Qx8qnxpSZZUxCBWSjcLRi562R6hB8b_3MOyPqk6NOKsg.rCXJAfAOHybHMEwx3xVm4aPnnwucThdKTaeDaFcsAHog.JPEG.hhejshh%2FB612%25A3%25DF20220303%25A3%25DF114444%25A3%25DF302.jpg",
            "후라이드참잘하는집 옥계점","경상북도 구미시 옥계동 731-1 1층"))


        val rvAdapter = ContentsRVAdapter(baseContext, items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)
    }
}