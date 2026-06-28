package com.example.composecraft.features.disastercommandcenter.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.toArgb
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Color", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Color) = encoder.encodeInt(value.toArgb())
    override fun deserialize(decoder: Decoder): Color = Color(decoder.decodeInt())
}

@Serializable
enum class IncidentType(
    val title: String,
    val iconSpec: IconSpec,
) {
    ALL(
        title = "All",
        iconSpec = IconSpec(emoji = "◎", color = Color(0xFF94A3B8))
    ),
    FIRE(
        title = "Fire",
        iconSpec = IconSpec(emoji = "🔥", color = Color(0xFFFF5A36))
    ),
    FLOOD(
        title = "Flood",
        iconSpec = IconSpec(emoji = "🌊", color = Color(0xFF3B82F6))
    ),
    MEDICAL(
        title = "Medical",
        iconSpec = IconSpec(emoji = "✚", color = Color(0xFFEF4444))
    ),
    POWER_OUTAGE(
        title = "Power Outage",
        iconSpec = IconSpec(emoji = "⚡", color = Color(0xFFFACC15))
    ),
    EARTHQUAKE(
        title = "Earthquake",
        iconSpec = IconSpec(emoji = "⌁", color = Color(0xFFA855F7))
    )
}

@Serializable
data class Incident(
    val type: IncidentType,
    val title: String,
    val address: String,
    val severity: Severity,
    val numberOfTeams: Int,
    val distanceInKm: Double,
    val etaInMin: Int
)

@Serializable
data class IconSpec(
    val emoji: String,
    @Serializable(with = ColorSerializer::class)
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

@Serializable
data class Team(
    val name: String,
    val incident: Incident,
    val status: TeamLiveStatus,
    val users: List<User>
)

@Serializable
enum class TeamLiveStatus(
    val title: String,
    @Serializable(with = ColorSerializer::class)
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

@Serializable
data class User(
    val id: Int,
    val name: String,
    val title: String,
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

@Serializable
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
        icon = Icons.Default.Dashboard
    ),
    BottomBarCard(
        title = "Chat",
        icon = Icons.AutoMirrored.Filled.Chat
    ),
    BottomBarCard(
        title = "Alert",
        icon = Icons.Default.Notifications
    ),
    BottomBarCard(
        title = "Log",
        icon = Icons.Default.History
    ),
)
val mockTeams = listOf(
    Team(
        name = "Alpha Team",
        incident = mockActiveIncidents[0],
        status = TeamLiveStatus.EN_ROUTE,
        users = listOf(
            User(1, "Sarah Kim", "Fire Captain", "👩🏻‍🚒"),
            User(2, "David Ross", "Rescue Specialist", "👨🏽‍🚒"),
            User(3, "Emma Clark", "Medic", "👩🏼‍⚕️")
        )
    ),
    Team(
        name = "Bravo Team",
        incident = mockActiveIncidents[1],
        status = TeamLiveStatus.RESCUING,
        users = listOf(
            User(4, "Noah Patel", "Flood Response Lead", "👨🏾‍🚒"),
            User(5, "Olivia Chen", "Field Coordinator", "👩🏻‍💻"),
            User(6, "Lucas Green", "Equipment Engineer", "👨🏼‍🔧")
        )
    ),
    Team(
        name = "Charlie Squad",
        incident = mockActiveIncidents[2],
        status = TeamLiveStatus.ON_SITE,
        users = listOf(
            User(7, "Mia Johnson", "Paramedic", "👩🏽‍⚕️"),
            User(8, "Ethan Walker", "Emergency Doctor", "👨🏻‍⚕️")
        )
    ),
    Team(
        name = "Delta Team",
        incident = mockActiveIncidents[3],
        status = TeamLiveStatus.STANDBY,
        users = listOf(
            User(9, "Sophia Lee", "Power Technician", "👩🏼‍🔧"),
            User(10, "James Carter", "Grid Engineer", "👨🏽‍🔧"),
            User(11, "Ava Wilson", "Ops Analyst", "👩🏻‍💻")
        )
    ),
    Team(
        name = "Urban Division",
        incident = mockActiveIncidents[4],
        status = TeamLiveStatus.EVACUATING,
        users = listOf(
            User(12, "Liam Brown", "Search Lead", "👨🏿‍🚒"),
            User(13, "Charlotte Adams", "Evacuation Officer", "👩🏼‍🚒"),
            User(14, "Benjamin Scott", "Structural Engineer", "👨🏻‍🔧")
        )
    )
)

enum class ExerciseType(val title: String) {
    COMPOUND("Compound"),
    ISOLATION("Isolation")
}

enum class Equipment(val title: String) {
    BODYWEIGHT("Bodyweight"),
    BARBELL("Barbell"),
    DUMBBELL("Dumbbell"),
    KETTLEBELL("Kettlebell"),
    CABLE("Cable"),
    MACHINE("Machine"),
    RESISTANCE_BAND("Resistance Band"),
}

enum class PrimaryMuscleGroups(val title: String) {
    HAMSTRINGS("Hamstrings"),
    GLUTES("Glutes"),
    QUADS("Quads"),
    CALVES("Calves"),
    LOWER_BACK("Lower Back"),
    CHEST("Chest"),
    BACK("Back"),
    SHOULDERS("Shoulders"),
    BICEPS("Biceps"),
    TRICEPS("Triceps"),
    CORE("Core")
}

enum class FocusArea(val title: String) {
    ALL("All"),
    CHEST("Chest"),
    BACK("Back"),
    LEGS("Legs"),
    ARMS("Arms")
}

enum class DifficultyLevel(val title: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    ELITE("Elite")
}