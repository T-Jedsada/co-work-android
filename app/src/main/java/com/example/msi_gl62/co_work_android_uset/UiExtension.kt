package com.example.msi_gl62.co_work_android_uset
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.ImageView

infix fun ImageView.load(url: String?) = this.apply {
    com.bumptech.glide.Glide.with(context).load(url).into(this) }

fun View.simpleFadeInAnimation() = let {
    this.animate()
            .alpha(1.0f)
            .setListener(null)
    this.show() }

fun View.simpleFadeOutAnimation(): ViewPropertyAnimator = let {
    this.animate()
            .alpha(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    this@simpleFadeOutAnimation.hide() } }) }

fun View.hide() = let {
    this.visibility = View.GONE }

fun View.show() = let {
    this.visibility = View.VISIBLE }
