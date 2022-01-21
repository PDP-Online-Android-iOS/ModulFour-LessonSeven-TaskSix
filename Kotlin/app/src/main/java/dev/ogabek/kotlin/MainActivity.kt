package dev.ogabek.kotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.webkit.URLUtil
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tv_main: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        tv_main = findViewById(R.id.tv_main)
        setLinks(tv_main, tv_main.text.toString())
    }

    private fun setLinks(tv_main: TextView, text: String) {
        val spannableString = SpannableString(text)
        val strings = text.split(" ")
        for (i in strings) {
            val string = SpannableString(i)
            if (!URLUtil.isValidUrl(i)) {
                continue
            }
            spannableString.setSpan(object:ClickableSpan() {
                override fun onClick(widget: View) {

                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = Color.parseColor("#33b5e5")
                    ds.isUnderlineText = false
                }

            }, text.indexOf(string.toString()), text.indexOf(string.toString()) + string.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv_main.movementMethod = LinkMovementMethod.getInstance()
        tv_main.text = spannableString
    }

}