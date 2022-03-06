package com.setesh.commons.binding

import android.text.Spanned
import android.view.View
import android.widget.TextView

fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun <T : View> T.showIf(condition: Boolean) {
    if (condition) {
        show()
    } else {
        hide()
    }
}

fun TextView.efficientText(newText: CharSequence?) {
    val oldText = this.text
    if (newText == oldText || (newText == null && oldText.isEmpty())) {
        return
    }
    if (newText is Spanned) {
        if (newText == oldText) {
            return
        }
    } else if (!haveContentsChanged(newText, oldText)) {
        return
    }
    text = newText
}

private fun haveContentsChanged(str1: CharSequence?, str2: CharSequence?): Boolean {
    if (str1 == null != (str2 == null)) {
        return true
    } else if (str1 == null) {
        return false
    }
    val length = str1.length
    if (length != str2!!.length) {
        return true
    }
    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}
