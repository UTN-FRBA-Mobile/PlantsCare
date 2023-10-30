package ar.edu.utn.frba.mobile.plantscare.ui.main.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.ui.theme.colorPrimaryProfile2
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor

@Composable
fun Profile(navController: NavHostController) {
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

@Composable
fun ArticleView(article: Article) {
  LazyColumn {
    item {
      // Display article metadata like title, author, etc.
      Text(text = article.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
      Text(text = "Author: ${article.author}")
      Text(text = "Reading Time: ${article.readingTimeMinutes} minutes")
      Text(text = "Published on: ${article.createdAt}")
    }

    items(article.blocks.size) { index ->
      val block = article.blocks[index]

      // Display blocks as paragraphs
      when (block.type) {
        "text" -> {
          Text(
            text = block.value,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
          )
        }
        "image" -> {
          // Display images or handle image rendering
        }
        // Add more block types as needed
      }
    }
  }
}

@Composable
fun ArticleScreen() {
  val article = Article(
    name = "lorem",
    readingTimeMinutes = 10,
    author = "John Doe",
    createdAt = "2023-10-01T03:00:00.000Z",
    title = "lorem ipsum",
    blocks = listOf(
      Block(type = "text", value = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
      Block(type = "text", value = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
      Block(type = "image", value = "url"),
      Block(type = "text", value = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
    )
  )

  ArticleView(article)
}

@Preview
@Composable
fun ArticleScreenPreview() {
  ArticleScreen()
}