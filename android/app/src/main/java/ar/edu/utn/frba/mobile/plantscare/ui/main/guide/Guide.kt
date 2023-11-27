package ar.edu.utn.frba.mobile.plantscare.ui.main.guide

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.Block
import ar.edu.utn.frba.mobile.plantscare.model.GuideData
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Grey
import ar.edu.utn.frba.mobile.plantscare.ui.theme.profileCardBackgroundColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.textColor
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun Guide(navController: NavHostController, state: APICallState<GuideData>) {
  loadScreen(state) {
    ResultScreen(it)
  }
}

@Composable
fun ResultScreen(guideData: GuideData) {
  Box(
//    contentAlignment = Alignment.TopEnd,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    ArticleScreen(guideData)
  }
}

data class Article(
  val name: String,
  val coverImage: String,
  val readingTimeMinutes: Int,
  val author: String,
  val createdAt: String,
  val title: String,
  val blocks: List<Block>
)

fun formatDate(dateString: String): String {
  val inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX" // Define the input date format
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
        .data(article.coverImage)
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
    "SUB_TITLE" -> {
      Text(
        text = block.value,
        fontSize = 24.sp,
        color = Grey,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(16.dp, 0.dp)
      )
    }

    "TEXT" -> {
      Text(
        text = block.value,
        fontSize = 16.sp,
        color = textColor,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(16.dp, 0.dp)
      )
    }

    "IMAGE" -> {
      Card(
        backgroundColor = profileCardBackgroundColor,
        modifier = Modifier
          .height(150.dp)
          .padding(16.dp)
      ) {
        AsyncImage(
          model = ImageRequest.Builder(context = LocalContext.current)
            .data(block.value)
            .crossfade(true)
            .build(),
//    error = painterResource(R.drawable.ic_broken_image),
//    placeholder = painterResource(R.drawable.loading_img),
          contentDescription = stringResource(R.string.guide_image_header_content_description),
          contentScale = ContentScale.Crop
        )
      }
    }

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
fun ArticleScreen(guide: GuideData) {
  val article = Article(
    name = guide.name,
    readingTimeMinutes = guide.readingTimeInMinutes,
    author = guide.author,
    createdAt = guide.createdAt,
    title = guide.title,
    coverImage = guide.coverImage,
    blocks = guide.blocks
  )

  ArticleView(article)
}
