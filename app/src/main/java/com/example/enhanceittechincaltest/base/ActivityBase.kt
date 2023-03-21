package com.example.enhanceittechincaltest.base

import android.content.Intent
import inc.credible.homerlibs.ActivityHomer
import inc.credible.homerlibs.FragmentHomer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ActivityBase : ActivityHomer(),FragmentHomer.OnFragmentUiLoadedListener {
    override fun doOnFragmentUiLoaded(fragment: FragmentHomer?) {

    }

    public open fun logUserOut(){
//        startActivity(Intent(this, ActivityLogin::class.java))
        //super.logout()
        finish()
        CoroutineScope(Dispatchers.IO).launch {
//            AuthSessionRepository.getInstance().logoutUser()
        }
    }

}