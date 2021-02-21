package com.jay.biz_mvvm.room

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alibaba.android.arouter.facade.annotation.Route
import com.jay.base_component.app.BaseComponentApp
import com.jay.base_component.arouter.ARPath
import com.jay.biz_mvvm.R
import com.jay.biz_mvvm.room.data.User
import com.jay.biz_mvvm.room.db.AppDatabase
import kotlinx.android.synthetic.main.biz_jetpack_activity_room_use.*

@Route(path = ARPath.PathJetPack.ROOM_USE_ACTIVITY_PATH)
class RoomUseActivity : AppCompatActivity() {
    val db = Room.databaseBuilder(
        BaseComponentApp.getApp(),
        AppDatabase::class.java,
        "database-name"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biz_jetpack_activity_room_use)
    }


    fun add(view: View) {
        val user1 = User(getId(), "jay-111", "droid-111")
        val user2 = User(getId(), "jay-222", "droid-222")
        val user3 = User(getId(), "jay-333", "droid-444")
        db.userDao().insertAll(user1, user2, user3)
    }

    private fun getId(): Int {
        val id = System.currentTimeMillis()
        return id.toInt()
    }

    fun delete(view: View) {
        val allUser = db.userDao().getAll()
        allUser.forEach {
            db.userDao().delete(it)
        }

    }

    fun change(view: View) {

    }

    fun query(view: View) {
        val allUser = db.userDao().getAll()
        tv_info.text = allUser.toString()
    }


}