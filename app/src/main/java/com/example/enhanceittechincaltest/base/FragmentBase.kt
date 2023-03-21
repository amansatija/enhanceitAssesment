package com.example.enhanceittechincaltest.base

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.enhanceittechincaltest.R

import inc.credible.homerlibs.FragmentHomer



open class FragmentBase: FragmentHomer() {

    protected var mLlErrorContainer: LinearLayout? = null


    override fun findViewByIds(v: View?) {
        super.findViewByIds(v)
        try {
            mLlErrorContainer = v?.findViewById(R.id.load_n_error_ll_centerWrapper)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun doOnDataNotFound( mStrMsg :String?) {
        super.doOnDataNotFound(mStrMsg)
        try {
            mLlErrorContainer?.visibility=View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showLoader() {
        super.showLoader()
        try {
            mLlErrorContainer?.visibility=View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun hideLoader() {
        try {
            mPbLoaderProgess.visibility =View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

   open fun showError(argStrMsg:String?){
        try {
            mLlErrorContainer?.visibility=View.VISIBLE
            mTvErrMsg.visibility = View.VISIBLE
            if(argStrMsg==null){
                mTvErrMsg.text = "Opps something went wrong"
            }else{
                mTvErrMsg.text = argStrMsg
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun doOnDataFound() {
        super.doOnDataFound()
        try {
            mLlErrorContainer?.visibility=View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}