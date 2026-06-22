@file:OptIn(ExperimentalMaterial3Api::class)

package com.ivanalexeevich.news.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ivanalexeevich.news.R
import com.ivanalexeevich.news.domain.settings.Interval
import com.ivanalexeevich.news.domain.settings.Language
import com.ivanalexeevich.news.presentation.utils.titleString


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SettingsTopAppBar(
                onBackArrowClick = onBackClick
            )
        }
    ) { innerPadding ->
        val state = viewModel.state.collectAsState()

        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            CardForLanguage(
                title = stringResource(R.string.switch_language),
                subTitle = stringResource(R.string.select_language_for_news_search),
                selectedLanguage = state.value.selectedLanguage,
                onMenuItemClick = {
                    viewModel.processCommand(SettingsCommand.SelectLanguage(it))
                }
            )
            CardForInterval(
                title = stringResource(R.string.select_interval),
                subTitle = stringResource(R.string.select_interval_for_update),
                selectedInterval = state.value.selectedInterval,
                onMenuItemClick = {
                    viewModel.processCommand(SettingsCommand.SelectInterval(it))
                }
            )
            CardForSettingsWithSwitch(
                isSwitched = state.value.isNotificationEnabled,
                title = stringResource(R.string.notifications),
                subTitle = stringResource(R.string.show_notifications_about_new_articles),
                switchContentDescription = stringResource(R.string.switch_notifications),
                onClickSwitch = {
                    viewModel.processCommand(SettingsCommand.SwitchNotification)
                }
            )
            CardForSettingsWithSwitch(
                isSwitched = state.value.isWifiOnlyEnabled,
                title = stringResource(R.string.update_only_via_wi_fi),
                subTitle = stringResource(R.string.save_mobile_data),
                switchContentDescription = stringResource(R.string.switch_wi_fi_only),
                onClickSwitch = {
                    viewModel.processCommand(SettingsCommand.SwitchWifiOnly)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopAppBar(
    modifier: Modifier = Modifier,
    onBackArrowClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier.padding(horizontal = 16.dp),
        title = {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.settings)
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        onClick = onBackArrowClick
                    ),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button"
            )
        }
    )
}

@Composable
fun CardForSettingsWithSwitch(
    modifier: Modifier = Modifier,
    isSwitched: Boolean,
    title: String,
    subTitle: String,
    switchContentDescription: String,
    onClickSwitch: (Boolean) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column() {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subTitle
                )
                Spacer(modifier = Modifier.height(8.dp))
                Switch(
                    modifier = Modifier.semantics { contentDescription = switchContentDescription },
                    checked = isSwitched,
                    onCheckedChange = onClickSwitch,
                    thumbContent = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                )

            }
        }
    }
}

@Composable
fun CardForLanguage(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    selectedLanguage: Language,
    onMenuItemClick: (Language) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column() {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subTitle
                )
                Spacer(modifier = Modifier.height(8.dp))

                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedLanguage.titleString(),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = stringResource(R.string.search_language)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier.menuAnchor(
                            type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                            enabled = true
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Language.entries.forEach {
                            DropdownMenuItem(
                                text = { Text(it.titleString()) },
                                onClick = {
                                    expanded = false
                                    onMenuItemClick(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardForInterval(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    selectedInterval: Interval,
    onMenuItemClick: (Interval) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column() {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subTitle
                )
                Spacer(modifier = Modifier.height(8.dp))

                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = stringResource(selectedInterval.titleString()) ,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.updates_interval)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier.menuAnchor(
                            type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                            enabled = true
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Interval.entries.forEach {
                            DropdownMenuItem(
                                text = { Text(stringResource(it.titleString())) },
                                onClick = {
                                    expanded = false
                                    onMenuItemClick(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}