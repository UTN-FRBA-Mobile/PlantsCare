package ar.edu.utn.frba.mobile.plantscare.ui.main.guide

import android.icu.text.ListFormatter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.colorPrimaryProfile2
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Guide(navController: NavHostController) {
  ResultScreen()
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen() {
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = stringResource(R.string.loading)
//    )
  Text(text = "LOADING...")
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen() {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
//        )
//        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
//    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Preview
@Composable
fun ResultScreen() {
  Box(
//    contentAlignment = Alignment.TopEnd,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    ArticleScreen()
  }
}

data class Article(
  val name: String,
  val readingTimeMinutes: Int,
  val author: String,
  val createdAt: String,
  val title: String,
  val blocks: List<Block>
)

data class Block(
  val type: String,
  val value: String
)

fun formatDate(dateString: String): String {
  val inputPattern = "yyyy-MM-dd'T'HH:mm:ss" // Define the input date format
  val outputPattern = "dd-MM-yyyy" // Define the desired output date format

  val inputFormatter = DateTimeFormatter.ofPattern(inputPattern)
  val outputFormatter = DateTimeFormatter.ofPattern(outputPattern)

  val parsedDate = LocalDateTime.parse(dateString, inputFormatter)
  return parsedDate.format(outputFormatter)
}

@Composable
fun HeaderImage(article: Article) {
  Card(
    backgroundColor = profileCardBackgroundColor,
    modifier = Modifier
      .fillMaxWidth()
      .height(180.dp),
    elevation = 3.dp
  ) {
    AsyncImage(
      model = ImageRequest.Builder(context = LocalContext.current)
        .data("https://via.placeholder.com/400")
        .crossfade(true)
        .build(),
//    error = painterResource(R.drawable.ic_broken_image),
//    placeholder = painterResource(R.drawable.loading_img),
      contentDescription = stringResource(R.string.guide_image_header_content_description),
      contentScale = ContentScale.Crop
    )
  }
}

@Composable
fun Title(title: String) {
  Text(text = title, fontWeight = FontWeight.Bold, fontSize = 35.sp)
}

@Composable
fun MinutesRead(minutesRead: Int) {
  Text(text = "$minutesRead minutes read", fontSize = 12.sp, color = Color.Gray)
}

@Composable
fun AuthorAndDate(author: String, createdAt: String) {
  Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
    Text(text = author, fontWeight = FontWeight.SemiBold)
    Text(text = formatDate(createdAt), color = Color.Gray)
  }
}

@Composable
fun HeaderData(article: Article) {
  Column(modifier = Modifier.padding(16.dp)) {
    MinutesRead(article.readingTimeMinutes)
    Title(article.title)
    Spacer(modifier = Modifier.height(10.dp))
    AuthorAndDate(author = article.author, createdAt = article.createdAt)
  }
}

@Composable
fun Body(block: Block) {
  // Display blocks as paragraphs
  when (block.type) {
    "text" -> {
      Text(
        text = block.value,
        fontSize = 16.sp,
        color = textColor,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(16.dp, 0.dp)
      )
    }
    // Handle image blocks if needed
//    "image" -> {}
    // Add more block types as needed
  }
}

@Composable
fun ArticleView(article: Article) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(12.dp),
//    modifier = Modifier.padding(16.dp)
  ) {
    item { HeaderImage(article) }
    item { HeaderData(article) }

    items(article.blocks.size) { index ->
      val block = article.blocks[index]
      Body(block)
    }
  }
}

@Composable
fun ArticleScreen() {
  val article = Article(
    name = "lorem",
    readingTimeMinutes = 10,
    author = "John Doe",
    createdAt = "2023-10-01T03:00:00",
    title = "The art of caring for plants",
    blocks = listOf(
      Block(type = "text", value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
      Block(
        type = "text",
        value = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
      ),
      Block(
        type = "text",
        value = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
      ),
      Block(type = "image", value = "url")
    )
  )

  ArticleView(article)
}

@Preview
@Composable
fun ArticleScreenPreview() {
  ArticleScreen()
}