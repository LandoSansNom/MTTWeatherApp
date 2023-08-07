//package com.example.mttweatherapp.ui
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardColors
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Shapes
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.Gray
//import androidx.compose.ui.graphics.Color.Companion.White
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.bumptech.glide.integration.compose.GlideImage
//import com.example.mttweatherapp.model.Weather
//import com.example.mttweatherapp.model.WeatherList
//import java.time.format.DateTimeFormatter
//
//@Composable
//fun WeatherCardToday(
//    state: Weather,
//    backgroundColor: Color,
//    modifier: Modifier = Modifier
//) {
//
//        Card(
//            backgroundColor = backgroundColor,
//            shape = RoundedCornerShape(10.dp),
//            modifier = modifier.padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = state.description?.let { Text(text = it) },
//                    modifier = Modifier.align(Alignment.End),
//                    color = Color.White
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                GlideImage(model = , contentDescription = "Wheather icon")
//                Image(painter = , contentDescription = "Wheather icon")
//
//            }
//
//        }
//
//    }
//
//
//
//    @Composable
//    fun FiveDayForecast(weather: List<WeatherList>) {
//        LazyColumn {
//            items(weather) { weatherData ->
//                WeatherCardForcast(weatherData)
//            }
//        }
//    }
//
//
//}
//
//@Composable
//fun WeatherCardForcast(weather: Weather) {
//    Card(
//        shape = RoundedCornerShape(10.dp),
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .fillMaxWidth(),
//        colors = CardColors(
//            containerColor = White, contentColor = White, disabledContainerColor = Gray,
//            disabledContentColor = Gray
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            weather.description?.let { Text(text = it) }
//            // Display weather information for each day in the WeatherCard
//            // You can use Text, Image, or other composables to display data
//            // from the weatherData object, such as temperature, weather description, icons, etc.
//        }
//    }
//}