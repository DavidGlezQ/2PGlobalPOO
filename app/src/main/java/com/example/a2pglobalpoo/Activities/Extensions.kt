package com.example.a2pglobalpoo.Activities

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.goActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}