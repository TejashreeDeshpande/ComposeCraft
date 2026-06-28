package com.example.composecraft.features.vehicle.notification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// --- Root Screen ---

@Composable
fun NotificationsScreen(
    uiState: NotificationsUiState,
    onMarkAllRead: () -> Unit,
    onDismiss: (String) -> Unit,
    onTap: (String) -> Unit,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            NotificationsTopBar(
                hasUnread = uiState.hasUnread,
                onMarkAllRead = onMarkAllRead,
                onBack = onBack
            )
        }
    ) { innerPadding ->
        if (uiState.notifications.isEmpty()) {
            NotificationsEmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        } else {
            NotificationsList(
                notifications = uiState.notifications,
                onDismiss = onDismiss,
                onTap = onTap,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

// --- Top Bar ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsTopBar(
    hasUnread: Boolean,
    onMarkAllRead: () -> Unit,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            // "Mark all read" grayed out when nothing is unread
            TextButton(
                onClick = onMarkAllRead,
                enabled = hasUnread
            ) {
                Text(
                    text = "Mark all read",
                    color = if (hasUnread) Color(0xFF1E88E5)
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}

// --- Notifications List ---

@Composable
fun NotificationsList(
    notifications: List<AppNotification>,
    onDismiss: (String) -> Unit,
    onTap: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 4.dp)
    ) {
        items(
            items = notifications,
            key = { it.id }                 // stable key = correct swipe-dismiss animation
        ) { notification ->
            SwipeToDismissNotification(
                notification = notification,
                onDismiss = { onDismiss(notification.id) },
                onTap = { onTap(notification.id) }
            )

            // Divider between rows — not after last item
            if (notification.id != notifications.last().id) {
                HorizontalDivider(
                    modifier = Modifier.padding(start = 72.dp),
                    thickness = DividerDefaults.Thickness,
                    color = DividerDefaults.color
                )
            }
        }
    }
}

// --- Swipe To Dismiss Wrapper ---

@Composable
fun SwipeToDismissNotification(
    notification: AppNotification,
    onDismiss: () -> Unit,
    onTap: () -> Unit
) {
    val dismissState =
        rememberSwipeToDismissBoxState(
            SwipeToDismissBoxValue.Settled,
            SwipeToDismissBoxDefaults.positionalThreshold
        )
    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,   // left swipe only
        enableDismissFromEndToStart = true,
        backgroundContent = {
            DismissBackground(dismissState = dismissState)
        }
    ) {
        NotificationRow(
            notification = notification,
            onTap = onTap
        )
    }
}

@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            SwipeToDismissBoxValue.EndToStart -> Color(0xFFE53935)
            else -> Color.Transparent
        },
        label = "dismiss_bg"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        AnimatedVisibility(
            visible = dismissState.targetValue == SwipeToDismissBoxValue.EndToStart
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete notification",
                tint = Color.White
            )
        }
    }
}

// --- Notification Row ---

@Composable
fun NotificationRow(
    notification: AppNotification,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onTap)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Colored icon circle — type drives color
        NotificationIcon(type = notification.type)

        // Title + subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = notification.title,
                style = MaterialTheme.typography.bodyMedium,
                // Bold if unread — matches screenshot exactly
                fontWeight = if (notification.isRead) FontWeight.Normal
                else FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = notification.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Timestamp + unread dot — right aligned, stacked
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = notification.relativeTime,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (!notification.isRead) {
                UnreadDot()
            } else {
                // Placeholder to keep column height stable
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

// --- Notification Icon ---

@Composable
fun NotificationIcon(type: NotificationType) {
    val (bgColor, iconTint, icon) = when (type) {
        NotificationType.TRIP_UPDATE -> Triple(
            Color(0xFFE3F2FD),
            Color(0xFF1E88E5),
            Icons.Default.DirectionsCar
        )
        NotificationType.PROMO -> Triple(
            Color(0xFFFFFDE7),
            Color(0xFFFDD835),
            Icons.Default.LocalOffer
        )
        NotificationType.ALERT -> Triple(
            Color(0xFFFFEBEE),
            Color(0xFFE53935),
            Icons.Default.Warning
        )
    }

    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = type.name,
            tint = iconTint,
            modifier = Modifier.size(22.dp)
        )
    }
}

// --- Unread Dot ---

@Composable
fun UnreadDot() {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(Color(0xFF1E88E5))
    )
}

// --- Empty State ---

@Composable
fun NotificationsEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.NotificationsNone,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No notifications",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "You're all caught up",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}