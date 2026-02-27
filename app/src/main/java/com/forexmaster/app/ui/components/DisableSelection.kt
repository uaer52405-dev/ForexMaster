package com.forexmaster.app.ui.components

import androidx.compose.foundation.text.selection.DisableSelection as ComposeDisableSelection
import androidx.compose.runtime.Composable

@Composable
fun DisableSelection(content: @Composable () -> Unit) {
    ComposeDisableSelection {
        content()
    }
}
