package com.dkmfromcg.mvvmplayer.util

import android.util.Log
import com.dkmfromcg.mvvmplayer.api.ILogger

object Logger : ILogger {

    private var mLogger: ILogger? = null
    private var mOpenLog: Boolean = false

    /**
     * 可以在application中就去设置Logger
     */
    fun setLogger(logger: ILogger) {
        mLogger = logger
    }

    fun openLogger(openLog: Boolean) {
        mOpenLog = openLog
    }

    override fun v(tag: String, message: String) {
        if (!mOpenLog) {
            return
        }
        mLogger?.let {
            it.v(tag, message)
            return
        }
        Log.v(tag, message)
    }

    override fun d(tag: String, message: String) {
        if (!mOpenLog) {
            return
        }
        mLogger?.let {
            it.d(tag, message)
            return
        }
        Log.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        if (!mOpenLog) {
            return
        }
        mLogger?.let {
            it.i(tag, message)
            return
        }
        Log.i(tag, message)
    }

    override fun w(tag: String, message: String) {
        if (!mOpenLog) {
            return
        }
        mLogger?.let {
            it.w(tag, message)
            return
        }
        Log.w(tag, message)
    }

    override fun e(tag: String, message: String) {
        if (!mOpenLog) {
            return
        }
        mLogger?.let {
            it.e(tag, message)
            return
        }
        Log.e(tag, message)
    }

}