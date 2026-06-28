package com.example.composecraft.features.animtedfilter

data class MosaicItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val filter: MosaicFilter,
    val emoji: String
)

val mockMosaicItems = listOf(

    // AI
    MosaicItem(
        id = "ai_1",
        title = "Lex Fridman",
        subtitle = "AI, consciousness & future tech",
        description = "Conversations with scientists, engineers, creators and researchers exploring the future of artificial intelligence and humanity.",
        filter = MosaicFilter.AI,
        emoji = "🧠",
    ),
    MosaicItem(
        id = "ai_2",
        title = "Hard Fork",
        subtitle = "AI and technology trends",
        description = "Weekly discussions on AI breakthroughs, internet culture, startups and the rapidly evolving tech industry.",
        filter = MosaicFilter.AI,
        emoji = "✨"
    ),
    MosaicItem(
        id = "ai_3",
        title = "Practical AI",
        subtitle = "Machine learning in production",
        description = "Focused on real-world machine learning systems, engineering practices and production AI workflows.",
        filter = MosaicFilter.AI,
        emoji = "⚡"
    ),
    MosaicItem(
        id = "ai_4",
        title = "The AI Breakdown",
        subtitle = "Daily AI insights and news",
        description = "Fast-paced updates covering generative AI tools, OpenAI news, industry shifts and emerging technologies.",
        filter = MosaicFilter.AI,
        emoji = "🚀"
    ),
    MosaicItem(
        id = "ai_5",
        title = "Eye on AI",
        subtitle = "Future of artificial intelligence",
        description = "Deep dives into the impact of artificial intelligence across society, business and innovation.",
        filter = MosaicFilter.AI,
        emoji = "💡"
    ),

    // Motion
    MosaicItem(
        id = "motion_1",
        title = "Motion Hatch",
        subtitle = "Motion design inspiration",
        description = "Creative discussions around motion graphics, animation workflows and visual storytelling.",
        filter = MosaicFilter.MOTION,
        emoji = "🌊"
    ),
    MosaicItem(
        id = "motion_2",
        title = "Design Better",
        subtitle = "Animation and product design",
        description = "Exploring modern product design systems, interaction design and motion experiences.",
        filter = MosaicFilter.MOTION,
        emoji = "🎬"
    ),
    MosaicItem(
        id = "motion_3",
        title = "UI Narrative",
        subtitle = "Storytelling through motion",
        description = "Focused on how motion and transitions improve digital storytelling and user engagement.",
        filter = MosaicFilter.MOTION,
        emoji = "🌀"
    ),
    MosaicItem(
        id = "motion_4",
        title = "Creative Motion",
        subtitle = "Modern visual interaction",
        description = "A futuristic look into animation systems, immersive interfaces and motion-driven products.",
        filter = MosaicFilter.MOTION,
        emoji = "✨"
    ),
    MosaicItem(
        id = "motion_5",
        title = "The Futur",
        subtitle = "Creative motion systems",
        description = "Creative industry insights covering branding, motion systems and modern visual communication.",
        filter = MosaicFilter.MOTION,
        emoji = "🎨"
    ),

    // Android
    MosaicItem(
        id = "android_1",
        title = "Android Developers Backstage",
        subtitle = "Jetpack Compose and Android",
        description = "Official Android engineering discussions about Compose, architecture and platform development.",
        filter = MosaicFilter.ANDROID,
        emoji = "🤖"
    ),
    MosaicItem(
        id = "android_2",
        title = "Fragmented Podcast",
        subtitle = "Modern Android engineering",
        description = "Conversations around Android architecture, engineering practices and developer growth.",
        filter = MosaicFilter.ANDROID,
        emoji = "📱"
    ),
    MosaicItem(
        id = "android_3",
        title = "Talking Kotlin",
        subtitle = "Kotlin and Compose ecosystem",
        description = "Focused on Kotlin language updates, Compose ecosystem and modern development practices.",
        filter = MosaicFilter.ANDROID,
        emoji = "⚙️"
    ),
    MosaicItem(
        id = "android_4",
        title = "The Developers’ Bakery",
        subtitle = "Android architecture and UX",
        description = "Practical software engineering discussions around architecture, UX and mobile systems.",
        filter = MosaicFilter.ANDROID,
        emoji = "🥐"
    ),
    MosaicItem(
        id = "android_5",
        title = "Compose Crunch",
        subtitle = "Declarative UI and motion",
        description = "Exploring advanced Compose UI, animations and scalable reusable component systems.",
        filter = MosaicFilter.ANDROID,
        emoji = "🌱"
    ),

    // Music
    MosaicItem(
        id = "music_1",
        title = "Song Exploder",
        subtitle = "Artists break down songs",
        description = "Musicians explain how iconic songs were created layer by layer in immersive storytelling format.",
        filter = MosaicFilter.MUSIC,
        emoji = "🎵"
    ),
    MosaicItem(
        id = "music_2",
        title = "Tape Notes",
        subtitle = "Inside music production",
        description = "Behind-the-scenes conversations with producers and artists about creative music production.",
        filter = MosaicFilter.MUSIC,
        emoji = "🎧"
    ),
    MosaicItem(
        id = "music_3",
        title = "Dissect",
        subtitle = "Deep analysis of music albums",
        description = "Detailed breakdowns of influential albums, lyrics, themes and production techniques.",
        filter = MosaicFilter.MUSIC,
        emoji = "💿"
    ),
    MosaicItem(
        id = "music_4",
        title = "Switched on Pop",
        subtitle = "Why pop music works",
        description = "Analyzing modern pop music through songwriting, trends and production insights.",
        filter = MosaicFilter.MUSIC,
        emoji = "🎼"
    ),
    MosaicItem(
        id = "music_5",
        title = "Sound Stories",
        subtitle = "Immersive audio experiences",
        description = "A cinematic audio experience combining storytelling, soundscapes and futuristic music visuals.",
        filter = MosaicFilter.MUSIC,
        emoji = "📶"
    )
)