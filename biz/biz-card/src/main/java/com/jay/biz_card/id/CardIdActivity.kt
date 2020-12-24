package com.jay.biz_card.id

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.arouter.ARPath
import com.jay.biz_card.R
import kotlinx.android.synthetic.main.biz_card_activity_card_id.*

@Route(path = ARPath.PathCard.CARD_ID_ACTIVITY_PATH)
class CardIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.biz_card_activity_card_id)
        randomIDCardNo()

    }

    private fun randomIDCardNo() {
        ll_id.setOnClickListener {

            val idNo = IDCardHelper.getIdNo(false)
            tv_id.text = idNo
            Log.d(TAG, "idNo: $idNo")
            val infoMap = IDCardHelper.getBirAgeSex(idNo)
            Log.d(TAG, "randomIDCardNo: $infoMap")//{sex=M, birth=2010-06-21, age=10}
            Log.d(TAG, "randomIDCardNo: ${infoMap["birth"]}")
            Log.d(TAG, "randomIDCardNo: ${infoMap["age"]}")
            Log.d(TAG, "randomIDCardNo: ${infoMap["sex"]}")

            val sex = infoMap["sex"]
            tv_gender.text = if (sex == "M") "男" else "女"
            val birth = infoMap["birth"].toString()
            val split = birth.split("-")
            Log.d(TAG, "split: ${split.size}")
            if (split.size == 3) {
                tv_birth.text = split[0]
                tv_birth_month.text = split[1]
                tv_birth_day.text = split[2]
            }
        }
    }

    companion object {
        const val TAG = "Card_Id"
    }

}