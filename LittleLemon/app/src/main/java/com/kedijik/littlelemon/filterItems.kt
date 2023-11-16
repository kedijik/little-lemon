package com.kedijik.littlelemon

import androidx.compose.ui.text.input.TextFieldValue
import com.kedijik.littlelemon.data.MenuItem

fun filterItems(items:List<MenuItem>?, searchTerm: TextFieldValue?, category: String?): List<MenuItem>?{
    var res = items
    if((searchTerm?.text ?: "") != ""){
        res = res?.filter{it.title.contains(searchTerm?.text?:"", ignoreCase=true)}
    }
    if((category ?: "") != ""){
        res = res?.filter { it.category==category }
    }
    return res
}