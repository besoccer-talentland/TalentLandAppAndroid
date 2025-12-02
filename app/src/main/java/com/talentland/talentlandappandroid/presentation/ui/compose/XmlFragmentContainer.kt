package com.talentland.talentlandappandroid.presentation.ui.compose

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.talentland.talentlandappandroid.presentation.ui.xml.MatchesXmlFragment


@Composable
fun XmlFragmentContainer(
    fragmentManager: FragmentManager,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            FragmentContainerView(context).apply {
                id = View.generateViewId()
            }
        },
        update = { view ->
            val fragment = MatchesXmlFragment()
            fragmentManager.beginTransaction()
                .replace(view.id, fragment)
                .commit()
        }
    )
}