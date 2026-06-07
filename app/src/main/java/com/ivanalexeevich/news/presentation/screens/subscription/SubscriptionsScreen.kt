@file:OptIn(ExperimentalMaterial3Api::class)

package com.ivanalexeevich.news.presentation.screens.subscription

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddModerator
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.room.Query
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import coil3.size.Scale
import com.ivanalexeevich.news.R
import com.ivanalexeevich.news.domain.entity.Article
import com.ivanalexeevich.news.presentation.utils.formatDate
import java.io.UncheckedIOException
import javax.inject.Inject

@Composable
fun SubscriptionScreen(
    modifier: Modifier = Modifier,
    onNavigateToSettings: () -> Unit,
    viewModel: SubscriptionsViewModel = hiltViewModel()

) {

}


@Composable
private fun SubscriptionsTopBar(
    modifier: Modifier = Modifier,
    onRefreshDataClick: () -> Unit,
    onClearArticlesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(R.string.top_bar_title))
        },
        actions = {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = onRefreshDataClick
                    )
                    .padding(8.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.refresh_data_content_description)
            )
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = onClearArticlesClick
                    )
                    .padding(8.dp),
                imageVector = Icons.Default.Clear,
                contentDescription = stringResource(R.string.clear_button_content_description)
            )
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = onSettingsClick
                    )
                    .padding(8.dp),
                imageVector = Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings_button_content_description)
            )
        }
    )
}


@Composable
private fun SubscriptionChip(
    modifier: Modifier = Modifier,
    topic: String,
    isSelected: Boolean,
    onSubscriptionClick: (String) -> Unit,
    onDeleteSubscription: (String) -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = {
            onSubscriptionClick(topic)
        },
        label = {
            Text(
                text = topic
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                        onDeleteSubscription(topic)
                    },
                imageVector = Icons.Default.Clear,
                contentDescription = stringResource(R.string.remove_subscription_button)
            )
        }

    )
}

@Composable
private fun Subscriptions(
    modifier: Modifier = Modifier,
    subscriptions: Map<String, Boolean>,
    query: String,
    isSubscriptionButtonEnabled: Boolean,
    onQueryChanged: (String) -> Unit,
    onTopicClick: (String) -> Unit,
    onDeleteSubscription: (String) -> Unit,
    onSubscribeButtonClick: () -> Unit
) {
    Column(
        modifier = modifier

            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = onQueryChanged,
            label = { Text(stringResource(R.string.what_are_you_looking_for)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onSubscribeButtonClick,
            enabled = isSubscriptionButtonEnabled
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_subscription_from_query_button)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.add_subscription)
            )
        }
        if (subscriptions.isNotEmpty()) {
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.subscriptions, subscriptions.size)
            )
            Spacer(modifier = Modifier.width(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                subscriptions.forEach { (topic, isSelected) ->
                    item(key = topic) {
                        SubscriptionChip(
                            topic = topic,
                            isSelected = isSelected,
                            onSubscriptionClick = onTopicClick,
                            onDeleteSubscription = onDeleteSubscription
                        )
                    }

                }

            }

        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.there_are_no_subscriptions_yet),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        article.imageUrl?.let { imageUrl ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp),
                model = imageUrl,
                contentDescription = stringResource(R.string.image_from_article, article.title),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            text = article.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.width(8.dp))

        if (article.description.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = article.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = article.sourceName,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                fontSize = 12.sp,
                text = article.publishedAt.formatDate(),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier= Modifier.weight(1f),
                onClick = {}
            ) {
                Icon(


                )

            }

        }
    }

}

