package com.kedijik.littlelemon.data

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException

class MenuViewModel(app: Application): AndroidViewModel(app){
    private val _menu = mutableStateOf(emptyList<MenuItem>())
    val menuItems: MutableState<List<MenuItem>>
        get() = _menu
    lateinit var client: HttpClient

    private var dao: MenuDAO = MenuDB.getDaoInstance(app.applicationContext)
    init{
        client = HttpClient() {
            install(ContentNegotiation) {
                json(contentType = ContentType.Any)
            }
        }

        viewModelScope.launch() {
            _menu.value=getMenu()
        }
    }



   suspend fun getMenu(): List<MenuItem>{
        return withContext(Dispatchers.IO) {
            try {
                refreshMenu()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException -> {
                        if (dao.getAllItems().isEmpty())
                            throw Exception(
                                "Something went wrong. " +
                                        "We have no menu items."
                            )
                    }
                    else -> throw e
                }
            }
            return@withContext dao.getAllItems()
        }
    }
    suspend fun refreshMenu(){
        val httpResponse= client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
        if (httpResponse.status.value in 200..299) {


         val Menu : MenuNetwork = httpResponse.body()
            val itemsLocal = mutableListOf<MenuItem>()
            if(Menu != null){
              Menu.items.forEach{i -> itemsLocal.add(MenuItem(i.id, i.title,i.desc,i.price,i.imgURL,i.category))}

            }
            dao.truncateMenu()
            dao.putItems(itemsLocal)
     }
    }
}