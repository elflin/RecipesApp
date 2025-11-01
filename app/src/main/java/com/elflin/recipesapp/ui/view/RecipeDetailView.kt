package com.elflin.recipesapp.ui.view

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elflin.recipesapp.ui.model.Meal
import com.elflin.recipesapp.ui.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailView(
    modifier: Modifier = Modifier,
    id: Int,
    viewModel: RecipeDetailViewModel = viewModel()
) {

    LaunchedEffect(key1 = id) {
        viewModel.loadData(id)
    }

    val meal: Meal? by viewModel.meal.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (meal == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal!!.mealThumb)
                    .crossfade(true)
                    .build(),
                contentDescription = "${meal!!.meal} Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = meal!!.meal,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${meal!!.category} • ${meal!!.area}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ID: ${meal!!.id}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            meal!!.linkYoutube.takeIf { it.isNotBlank() }?.let { youtubeLink ->
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // 1. Buat Intent dengan aksi VIEW
                        val intent = Intent(Intent.ACTION_VIEW, youtubeLink.toUri())

                        // 2. Coba jalankan Intent
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Handle kasus di mana tidak ada aplikasi yang dapat menangani URL (jarang terjadi)
                            e.printStackTrace()
                            // Opsional: Tampilkan Toast kepada pengguna
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tonton Tutorial di YouTube")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Instruksi:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = meal!!.instructions,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeDetailPreview(
    modifier: Modifier = Modifier
) {
    val sampleDetailMeal = Meal(
        meal = "Ratatouille",
        category = "Vegetarian",
        area = "French",
        instructions = "Cut the aubergines in half lengthways. Place them on the board, cut side down, slice in half lengthways again and then across into 1.5cm chunks. Cut off the courgettes ends, then across into 1.5cm slices. Peel the peppers from stalk to bottom. Hold upright, cut around the stalk, then cut into 3 pieces. Cut away any membrane, then chop into bite-size chunks.\\r\\nScore a small cross on the base of each tomato, then put them into a heatproof bowl. Pour boiling water over the tomatoes, leave for 20 secs, then remove. Pour the water away, replace the tomatoes and cover with cold water. Leave to cool, then peel the skin away. Quarter the tomatoes, scrape away the seeds with a spoon, then roughly chop the flesh.\\r\\nSet a sauté pan over medium heat and when hot, pour in 2 tbsp olive oil. Brown the aubergines for 5 mins on each side until the pieces are soft. Set them aside and fry the courgettes in another tbsp oil for 5 mins, until golden on both sides. Repeat with the peppers. Don’t overcook the vegetables at this stage, as they have some more cooking left in the next step.\\r\\nTear up the basil leaves and set aside. Cook the onion in the pan for 5 mins. Add the garlic and fry for a further min. Stir in the vinegar and sugar, then tip in the tomatoes and half the basil. Return the vegetables to the pan with some salt and pepper and cook for 5 mins. Serve with basil.",
        mealThumb = "https://www.themealdb.com/images/media/meals/wrpwuu1511786491.jpg",
        id = 52882,
        linkYoutube = "https://www.youtube.com/watch?v=BFdQUgAFtGU"
    )

    RecipeDetailView(id = sampleDetailMeal.id, modifier = modifier)
}