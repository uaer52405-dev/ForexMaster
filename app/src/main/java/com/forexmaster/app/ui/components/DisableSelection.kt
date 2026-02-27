package com.forexmaster.app.ui.components

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.runtime.Composable

@Composable
fun DisableSelection(content: @Composable () -> Unit) {
    DisableSelection {
        content()
    }
}
