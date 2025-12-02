package com.talentland.talentlandappandroid

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import com.talentland.talentlandappandroid.presentation.ui.compose.MatchesComposeScreen
import com.talentland.talentlandappandroid.presentation.ui.compose.XmlFragmentContainer
import com.talentland.talentlandappandroid.ui.theme.BeSoccerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        val isDarkMode = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        windowInsetsController.isAppearanceLightStatusBars = !isDarkMode

        setupContent()
    }

    private fun setupContent() {
        setContent {
            BeSoccerTheme {
                var showCompose by rememberSaveable { mutableStateOf(true) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (showCompose) {
                            Button(
                                onClick = {
                                    showCompose = true
                                },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Compose",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    showCompose = true
                                },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "Compose",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            }
                        }

                        if (!showCompose) {
                            Button(
                                onClick = {
                                    showCompose = false
                                },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "XML Views",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            }
                        } else {
                            OutlinedButton(
                                onClick = {
                                    showCompose = false
                                },
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = "XML Views",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    if (showCompose) {
                        MatchesComposeScreen()
                    } else {
                        XmlFragmentContainer(
                            fragmentManager = supportFragmentManager,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
