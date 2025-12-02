package com.talentland.talentlandappandroid.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.talentland.talentlandappandroid.core.CornerStyle
import com.talentland.talentlandappandroid.core.util.MatchItemDimensions
import com.talentland.talentlandappandroid.domain.model.Match
import com.talentland.talentlandappandroid.presentation.viewmodel.MatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesComposeScreen(
    viewModel: MatchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.refreshMatches() }
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refrescar"
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.matches.isEmpty() -> {
                    EmptyState(
                        onRetry = { viewModel.refreshMatches() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    MatchList(
                        matches = uiState.matches,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun MatchList(
    matches: List<Match>,
    modifier: Modifier = Modifier,
) {
    val cornerStyles = remember(matches) {
        matches.mapIndexed { index, _ ->
            when {
                matches.size == 1 -> CornerStyle.FULL
                index == 0 -> CornerStyle.TOP
                index == matches.size - 1 -> CornerStyle.BOTTOM
                else -> CornerStyle.MIDDLE
            }
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        itemsIndexed(
            items = matches,
            key = { _, match -> match.id }
        ) { index, match ->
            MatchItem(
                match = match,
                cornerStyle = cornerStyles[index],
            )
        }
    }
}

@Composable
private fun MatchItem(
    match: Match,
    cornerStyle: CornerStyle,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val cornerRadius = remember { MatchItemDimensions.CardCornerRadius }
    val shape = remember(cornerStyle) {
        when (cornerStyle) {
            CornerStyle.TOP -> {
                RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = cornerRadius,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            }

            CornerStyle.MIDDLE -> {
                RoundedCornerShape(0.dp)
            }

            CornerStyle.BOTTOM -> {
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = cornerRadius,
                    bottomEnd = cornerRadius
                )
            }

            CornerStyle.SINGLE -> {
                RoundedCornerShape(cornerRadius)
            }

            CornerStyle.FULL -> {
                RoundedCornerShape(cornerRadius)
            }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.White, shape),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 2.dp,
            hoveredElevation = 2.dp,
            focusedElevation = 2.dp,
            draggedElevation = 2.dp
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MatchItemDimensions.ItemHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = match.homeTeam,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 12.dp),
                style = typography.bodyMedium,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorScheme.onSurface
            )
            Box(
                modifier = Modifier
                    .size(MatchItemDimensions.ShieldSize)
            ) {
                if (match.localShield.isNotBlank()) {
                    AsyncImage(
                        model = match.localShield,
                        contentDescription = "Escudo ${match.homeTeam}",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(Modifier.width(MatchItemDimensions.ShieldSpacing))


            Text(
                text = if (match.homeScore != null && match.awayScore != null) {
                    "${match.homeScore} - ${match.awayScore}"
                } else {
                     "-"
                },
                style = typography.bodyLarge,
                color = colorScheme.onSurface
            )

            Spacer(Modifier.width(MatchItemDimensions.ShieldSpacing))

            Box(
                modifier = Modifier
                    .size(MatchItemDimensions.ShieldSize)
            ) {
                if (match.visitorShield.isNotBlank()) {
                    AsyncImage(
                        model = match.visitorShield,
                        contentDescription = "Escudo ${match.awayTeam}",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(
                text = match.awayTeam,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, end = 16.dp),
                style = typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colorScheme.onSurface
            )
            }
            
            if (match.liveMinute != null) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(
                            top = MatchItemDimensions.LiveIndicatorMargin,
                            end = MatchItemDimensions.LiveIndicatorMargin
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .width(MatchItemDimensions.LiveIndicatorWidth)
                            .background(
                                color = Color(0xFF1B5E20),
                                shape = RoundedCornerShape(MatchItemDimensions.LiveIndicatorCornerRadius)
                            )
                            .padding(vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${match.liveMinute}'",
                            style = typography.labelSmall,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No hay datos disponibles",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Reintentar")
        }
    }
}
