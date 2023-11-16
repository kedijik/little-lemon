package com.kedijik.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kedijik.littlelemon.data.MenuItem
import com.kedijik.littlelemon.data.MenuViewModel


@Composable
fun Home(){
   val vm: MenuViewModel = viewModel()
    val searchTerm = remember { mutableStateOf( TextFieldValue("")) }
    val category = remember { mutableStateOf( "") }

    Column( modifier = Modifier
        .fillMaxSize()
        .background(colors.background)
        .padding(horizontal = 12.dp)){
        //Hero Section
        Column(verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight(0.36f)
                .background(colors.surface)){
            Text("Little Lemon", style = MaterialTheme.typography.h1 ,modifier= Modifier
                .padding(horizontal = 12.dp)
                .height(56.dp) )
            Row(modifier=Modifier.padding(horizontal = 12.dp)){
                Column(
                    Modifier
                        .fillMaxWidth(.67f)
                        .padding(end = 12.dp)){
                    Text("Chicago",  style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",fontWeight = FontWeight.Bold, color= colors.background) }
                Image(painter=painterResource(id = R.drawable.hero) , contentDescription = "Hero",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .align(Alignment.Bottom)) }
            SearchBox(state = searchTerm)
        }
        //Categories

        Column(verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight(0.14f)
                .fillMaxWidth()
                .background(colors.background)
                ){
            Categories(category,vm.menuItems.value.map{it.category}.distinct())}

        Divider(color = colors.onSecondary )
        //MenuList

        MenuItems(filterItems(vm.menuItems.value,searchTerm.value,category.value))}


}

@Composable
fun Categories(state: MutableState<String>, allCategories: List<String>){
    Text("ORDER FOR DELIVERY!", fontWeight = FontWeight.Bold, color=Color.Black, fontSize = 20.sp)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp) ,  modifier = Modifier.fillMaxWidth()){
        items(allCategories){
            Button(onClick = { if(state.value==it){state.value=""} else state.value=it },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if(state.value!=it) colors.primaryVariant else colors.onSurface,
                    contentColor = colors.background
                    ), shape = RoundedCornerShape(12.dp)) {
                Text(it, color = Color.Black)
            }
        }

    }
}
@Composable
fun SearchBox(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),

        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
   //     placeholder = { Text("Enter Search Phrase", textAlign = TextAlign.Center) },
        singleLine = true,
        shape = RoundedCornerShape(12.dp), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = colors.onPrimary,
            cursorColor = colors.onPrimary,
            leadingIconColor = colors.onPrimary,
            trailingIconColor = colors.onPrimary,
            backgroundColor = colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}




@Composable
fun MenuItems(menu: List<MenuItem>?){
    if(!menu.isNullOrEmpty()){
    LazyColumn (modifier = Modifier
        .fillMaxWidth()
        .background(colors.background)){
        items(menu.sortedBy { it.id }) { dish ->
            MenuItemCard(dish)
            Divider(color = colors.onSecondary )
        }

    }}
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemCard(dish: MenuItem) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)){
        Text(dish.title, textAlign = TextAlign.Start, fontWeight = FontWeight.Bold,color=Color.Black)
        Spacer(Modifier.padding(4.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)){
            Column(modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(end = 4.dp)){
                Text(dish.desc, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 14.sp)
                Spacer(Modifier.padding(4.dp))
                Text("\$${"%.2f".format(dish.price)}")
            }

           GlideImage(model = dish.imgURL, contentDescription = dish.desc, contentScale = ContentScale.Crop)
        }
    }

}

/*@Preview
@Composable
fun ItemListLook(){
    val dish = MenuItem(
        id=4,
        title="Pasta",
        desc="Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
        price=10.0,
        imgURL= "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
        category ="mains")
    val dish2 = MenuItem(
        id=2,
        title="Lemon Desert",
        desc="Penne with fried aubergines, cherry tomatoes, tomato sauce, fresh chili, garlic, basil & salted ricotta cheese.",
        price=10.0,
        imgURL= "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg?raw=true",
        category ="desserts")

      //  MenuItems(listOf(dish,dish,dish))
    MaterialTheme{
        Home(listOf(dish,dish2,dish2,dish))
    }




}*/