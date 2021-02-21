package com.jay.lib_persistence.db;

import android.content.Context;


public abstract class AbstractPersistence<DB> {

    private DB db;

    private Context context;

    public AbstractPersistence(Context context) {
        this.context = context;
    }

    public DB getDb() {
        if (db == null) {
            init();
        }
        return db;
    }

    protected abstract String getDbName();

    private void init() {

        //todo 封装获取一个数据库的公共方法
//        db = Room.databaseBuilder(context, DB, getDbName())
//                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build();
    }
}
