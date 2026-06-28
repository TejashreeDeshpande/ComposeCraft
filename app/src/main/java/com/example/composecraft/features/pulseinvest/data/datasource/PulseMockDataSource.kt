package com.example.composecraft.features.pulseinvest.data.datasource

import com.example.composecraft.features.pulseinvest.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PulseMockDataSource @Inject constructor() {

    fun getStocks(): List<Stock> = listOf(
        Stock("NVDA", "NVIDIA", 188.42, 7.64, 4.23, "2.29T", "68.4", 210.80, 108.13,
            "NVIDIA Corporation is a leading technology company focused on GPU and AI.",
            listOf(55f, 45f, 50f, 28f, 32f, 15f, 8f)),
        Stock("AAPL", "Apple", 191.25, 2.10, 1.11, "2.95T", "28.1", 199.62, 124.17,
            "Apple Inc. designs, manufactures, and markets smartphones, PCs, tablets, wearables and accessories.",
            listOf(60f, 52f, 55f, 38f, 40f, 25f, 18f)),
        Stock("MSFT", "Microsoft", 432.18, -4.02, -0.92, "3.21T", "35.8", 468.35, 310.15,
            "Microsoft Corporation develops and supports software, services, devices and solutions worldwide.",
            listOf(30f, 35f, 28f, 42f, 38f, 50f, 55f)),
        Stock("TSLA", "Tesla", 248.60, 9.18, 3.84, "792B", "72.3", 278.98, 138.80,
            "Tesla, Inc. designs, develops, manufactures and sells electric vehicles and energy generation and storage systems.",
            listOf(65f, 70f, 55f, 58f, 45f, 48f, 38f)),
        Stock("AMZN", "Amazon", 196.21, 2.65, 1.38, "2.05T", "42.7", 201.20, 101.26,
            "Amazon.com, Inc. engages in the retail sale of consumer products and subscriptions.",
            listOf(68f, 58f, 62f, 42f, 46f, 28f, 20f)),
        Stock("GOOGL", "Google", 172.28, 1.23, 0.72, "2.18T", "24.5", 193.31, 115.83,
            "Alphabet Inc. provides various products and platforms in the United States, Europe, and Asia.",
            listOf(70f, 62f, 65f, 48f, 50f, 32f, 25f))
    )

    fun getHoldings(): List<Holding> = listOf(
        Holding(getStocks()[0], 10, 145.20),
        Holding(getStocks()[1], 8, 168.40),
        Holding(getStocks()[2], 5, 385.60),
        Holding(getStocks()[3], 3, 210.15),
        Holding(getStocks()[4], 4, 178.90),
        Holding(getStocks()[5], 6, 152.30)
    )

    fun getPortfolio(): Portfolio = Portfolio(
        totalValue = 124523.21,
        todayChange = 3984.76,
        todayChangePercent = 3.78,
        holdings = getHoldings(),
        allocationData = listOf(
            AllocationSlice("Technology", 0.60f, 64750.87, "#00FF88"),
            AllocationSlice("ETFs", 0.24f, 23879.21, "#4488FF"),
            AllocationSlice("Healthcare", 0.12f, 14933.11, "#FF8844"),
            AllocationSlice("Cash", 0.04f, 14972.02, "#AA44FF")
        ),
        history = listOf(55f, 48f, 52f, 38f, 42f, 35f, 40f, 28f, 32f, 20f, 15f, 8f, 12f)
    )

    fun getNews(): List<NewsArticle> = listOf(
        NewsArticle("1", "NVIDIA Surges on Strong Earnings & AI Demand", "Bloomberg", "2h ago",
            isFeatured = true, relatedSymbol = "NVDA", portfolioImpact = "+\$842.35 (0.68%)", emoji = "🟢"),
        NewsArticle("2", "Markets Rally as Tech Leads Gains", "CNBC", "3h ago", emoji = "📊"),
        NewsArticle("3", "Fed Signals Possible Rate Cuts in Q3 2024", "Reuters", "5h ago", emoji = "🏦"),
        NewsArticle("4", "Tesla Secures Major European Manufacturing Deal", "WSJ", "6h ago", emoji = "⚡"),
        NewsArticle("5", "Pharma Sector Outperforms on New Drug Approvals", "Bloomberg", "8h ago", emoji = "💊")
    )

    fun getLearningProgress(): LearningProgress = LearningProgress(
        level = 5, levelTitle = "Level 5 Investor",
        currentXp = 750, maxXp = 1000,
        courses = listOf(
            Course("1", "What is a Stock?", 5, CourseStatus.COMPLETED, "📚"),
            Course("2", "What is an ETF?", 8, CourseStatus.COMPLETED, "📊"),
            Course("3", "Understanding Risk", 8, CourseStatus.IN_PROGRESS, "⚖️"),
            Course("4", "How Markets Work", 12, CourseStatus.LOCKED, "🏛️"),
            Course("5", "Portfolio Diversification", 10, CourseStatus.LOCKED, "🎯")
        )
    )

    fun getNotifications(): List<PulseNotification> = listOf(
        PulseNotification("1", "Order Executed", "You bought 5 shares of NVIDIA at \$188.42", "2m ago", false, "✅"),
        PulseNotification("2", "Price Alert", "AAPL fell 2% in the last hour", "18m ago", false, "⚠️"),
        PulseNotification("3", "Market Update", "Tech stocks are trending up significantly today", "31m ago", true, "📰"),
        PulseNotification("4", "Deposit Successful", "\$1,000 deposited to your account", "1h ago", true, "💵"),
        PulseNotification("5", "Learning Reminder", "Continue your course on portfolio diversification", "2h ago", true, "📚"),
        PulseNotification("6", "NVIDIA Earnings Beat", "NVDA up 4.2% on strong AI revenue growth", "3h ago", true, "🟢")
    )

    fun getPriceAlerts(): List<PriceAlert> = listOf(
        PriceAlert("1", "NVIDIA", "🟢", "Alert above \$200.00", true),
        PriceAlert("2", "Apple", "🍎", "Alert below \$150.00", true),
        PriceAlert("3", "Tesla", "⚡", "Alert above \$300.00", false)
    )

    fun getStatements(): List<Statement> = listOf(
        Statement("1", "Monthly Statement", "June 2024", StatementType.MONTHLY),
        Statement("2", "Monthly Statement", "May 2024", StatementType.MONTHLY),
        Statement("3", "Monthly Statement", "April 2024", StatementType.MONTHLY),
        Statement("4", "Tax Report", "2023 Tax Year", StatementType.TAX),
        Statement("5", "Tax Report", "2022 Tax Year", StatementType.TAX)
    )

    fun getSupportMessages(): List<SupportMessage> = listOf(
        SupportMessage("1", "Hi! How can we help you? 😊", MessageSender.SUPPORT),
        SupportMessage("2", "I need help with my deposit.", MessageSender.USER),
        SupportMessage("3", "Sure, I can help with that. Please share more details.", MessageSender.SUPPORT),
        SupportMessage("4", "My deposit hasn't arrived yet.", MessageSender.USER),
        SupportMessage("5", "Let me check that for you. Bank transfers can take 1–3 business days to process. Your deposit of \$1,000 should arrive by end of day tomorrow.", MessageSender.SUPPORT)
    )

    fun getTrending(): List<String> = listOf(
        "🔥 Artificial Intelligence",
        "📈 Top ETFs",
        "💻 Technology Stocks",
        "₿ Cryptocurrencies"
    )
}
