package com.example.composecraft.presentation.features.disastercommandcenter.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Commit
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class IncidentType(
    val title: String,
    val iconSpec: IconSpec,
) {
    ALL(
        title = "All",
        iconSpec = IconSpec(emoji = "", Color(0xFFE57373))
    ),
    FIRE(
        title = "Fire",
        iconSpec = IconSpec(emoji = "\uD83D\uDD25", Color(0xFFE57373))
    ),
    FLOOD(
        title = "Flood",
        iconSpec = IconSpec(emoji = "⛈\uFE0F", Color(0xFFE57373))
    ),
    MEDICAL(
        title = "Medical",
        iconSpec = IconSpec(emoji = "✚", Color(0xFFE57373))
    ),

    POWER_OUTAGE(
        title = "Power Outage",
        iconSpec = IconSpec(emoji = "⚡", Color(0xFFE57373))
    ),
    EARTHQUAKE(
        title = "Earthquake",
        iconSpec = IconSpec(emoji = "♒\uFE0E", Color(0xFFE57373))
    ),
}

data class Incident(
    val type: IncidentType,
    val title: String,
    val address: String,
    val severity: Severity,
    val numberOfTeams: Int,
    val distanceInKm: Double,
    val etaInMin: Int
)

data class IconSpec(
    val emoji: String,
    val color: Color
)

data class KpiModel(
    val type: KpiType,
    val title: String,
    val value: String,
    val trend: String,
    val trendSuffix: String = "",
)

enum class KpiType(
    val title: String,
    val iconSpec: IconSpec,
) {

    INCIDENTS(
        title = "Active Incidents",
        iconSpec = IconSpec(
            emoji = "⚠️",
            color = Color(0xFFE57373)
        )
    ),

    PEOPLE_AFFECTED(
        title = "People Affected",
        iconSpec = IconSpec(
            emoji = "👥",
            color = Color(0xFF64B5F6)
        )
    ),

    TEAMS_DEPLOYED(
        title = "Teams Deployed",
        iconSpec = IconSpec(
            emoji = "🛡️",
            color = Color(0xFF81C784)
        )
    ),

    RESOURCES_ACTIVE(
        title = "Resources Active",
        iconSpec = IconSpec(
            emoji = "🚚",
            color = Color(0xFFFFB74D)
        )
    ),

    RESCUE_PROGRESS(
        title = "Rescue Progress",
        iconSpec = IconSpec(
            emoji = "📈",
            color = Color(0xFFBA68C8)
        )
    ),

//    SAFE_ZONES(
//        title = "Safe Zones",
//        iconSpec = IconSpec(
//            emoji = "🟢",
//            color = Color(0xFF4DB6AC)
//        )
//    )
}

data class Team(
    val name: String,
    val incident: Incident,
    val status: TeamLiveStatus,
    val users: List<User>
)

enum class TeamLiveStatus(
    val title: String,
    val color: Color
) {

    EN_ROUTE(
        title = "En Route",
        color = Color(0xFF3B82F6)
    ),

    ON_SITE(
        title = "On Site",
        color = Color(0xFFF59E0B)
    ),

    RESCUING(
        title = "Rescuing",
        color = Color(0xFFEF4444)
    ),

    EVACUATING(
        title = "Evacuating",
        color = Color(0xFFA855F7)
    ),

    STANDBY(
        title = "Standby",
        color = Color(0xFF22C55E)
    )
}

data class User(
    val id: Int,
    val name: String,
    val avatar: String
)

val mockKpiData = listOf(
    KpiModel(
        type = KpiType.INCIDENTS,
        title = "Active Incidents",
        value = "23",
        trend = "+8",
        trendSuffix = "today"
    ),
    KpiModel(
        type = KpiType.PEOPLE_AFFECTED,
        title = "People Affected",
        value = "5,247",
        trend = "+12%",
        trendSuffix = "since morning"
    ),
    KpiModel(
        type = KpiType.TEAMS_DEPLOYED,
        title = "Teams Deployed",
        value = "48",
        trend = "12",
        trendSuffix = "on field"
    ),
    KpiModel(
        type = KpiType.RESOURCES_ACTIVE,
        title = "Resources Active",
        value = "76",
        trend = "9",
        trendSuffix = "available"
    ),
    KpiModel(
        type = KpiType.RESCUE_PROGRESS,
        title = "Rescue Progress",
        value = "76%",
        trend = "+14%",
        trendSuffix = "completed"
    ),
//    KpiModel(
//        type = KpiType.SAFE_ZONES,
//        title = "Safe Zones",
//        value = "18",
//        trend = "+3",
//        trendSuffix = "opened"
//    )
)

val mockActiveIncidents = listOf(
    Incident(
        type = IncidentType.FIRE,
        title = "Warehouse Fire",
        address = "Northbrook, Sector 7",
        severity = Severity.HIGH,
        numberOfTeams = 4,
        distanceInKm = 2.4,
        etaInMin = 8
    ),
    Incident(
        type = IncidentType.FLOOD,
        title = "Street Flooding",
        address = "Riverbend Ave",
        severity = Severity.MEDIUM,
        numberOfTeams = 3,
        distanceInKm = 5.1,
        etaInMin = 14
    ),
    Incident(
        type = IncidentType.MEDICAL,
        title = "Medical Emergency",
        address = "Central Transit Hub",
        severity = Severity.CRITICAL,
        numberOfTeams = 2,
        distanceInKm = 1.2,
        etaInMin = 5
    ),
    Incident(
        type = IncidentType.POWER_OUTAGE,
        title = "Power Grid Failure",
        address = "Eastline District",
        severity = Severity.HIGH,
        numberOfTeams = 5,
        distanceInKm = 7.8,
        etaInMin = 18
    ),
    Incident(
        type = IncidentType.EARTHQUAKE,
        title = "Structural Damage",
        address = "Old Market Zone",
        severity = Severity.CRITICAL,
        numberOfTeams = 6,
        distanceInKm = 3.6,
        etaInMin = 11
    )
)

enum class Severity {
    CRITICAL,
    HIGH,
    MEDIUM,
    LOW,
}

data class BottomBarCard(
    val title: String,
    val icon: ImageVector
)

val bottomBarCards = listOf(
    BottomBarCard(
        title = "Command",
        icon = Icons.Default.Commit
    ),
    BottomBarCard(
        title = "Chat",
        icon = Icons.Default.Commit
    ),
    BottomBarCard(
        title = "Alert",
        icon = Icons.Default.Commit
    ),
    BottomBarCard(
        title = "Log",
        icon = Icons.Default.Commit
    ),
)
val mockTeams = listOf(

    Team(
        name = "Alpha Team",
        incident = mockActiveIncidents[0],
        status = TeamLiveStatus.EN_ROUTE,
        users = listOf(
            User(
                id = 1,
                name = "Sarah Kim",
                avatar = "👩🏻‍🚒"
            ),
            User(
                id = 2,
                name = "David Ross",
                avatar = "👨🏽‍🚒"
            ),
            User(
                id = 3,
                name = "Emma Clark",
                avatar = "👩🏼‍⚕️"
            )
        )
    ),

    Team(
        name = "Bravo Team",
        incident = mockActiveIncidents[1],
        status = TeamLiveStatus.RESCUING,
        users = listOf(
            User(
                id = 4,
                name = "Noah Patel",
                avatar = "👨🏾‍🚒"
            ),
            User(
                id = 5,
                name = "Olivia Chen",
                avatar = "👩🏻‍💻"
            ),
            User(
                id = 6,
                name = "Lucas Green",
                avatar = "👨🏼‍🔧"
            )
        )
    ),

    Team(
        name = "Charlie Squad",
        incident = mockActiveIncidents[2],
        status = TeamLiveStatus.ON_SITE,
        users = listOf(
            User(
                id = 7,
                name = "Mia Johnson",
                avatar = "👩🏽‍⚕️"
            ),
            User(
                id = 8,
                name = "Ethan Walker",
                avatar = "👨🏻‍⚕️"
            )
        )
    ),

    Team(
        name = "Delta Team",
        incident = mockActiveIncidents[3],
        status = TeamLiveStatus.STANDBY,
        users = listOf(
            User(
                id = 9,
                name = "Sophia Lee",
                avatar = "👩🏼‍🔧"
            ),
            User(
                id = 10,
                name = "James Carter",
                avatar = "👨🏽‍🔧"
            ),
            User(
                id = 11,
                name = "Ava Wilson",
                avatar = "👩🏻‍💻"
            )
        )
    ),

    Team(
        name = "Urban Division",
        incident = mockActiveIncidents[4],
        status = TeamLiveStatus.EVACUATING,
        users = listOf(
            User(
                id = 12,
                name = "Liam Brown",
                avatar = "👨🏿‍🚒"
            ),
            User(
                id = 13,
                name = "Charlotte Adams",
                avatar = "👩🏼‍🚒"
            ),
            User(
                id = 14,
                name = "Benjamin Scott",
                avatar = "👨🏻‍🔧"
            )
        )
    )
)