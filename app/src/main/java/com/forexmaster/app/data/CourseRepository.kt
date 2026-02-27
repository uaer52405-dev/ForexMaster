package com.forexmaster.app.data

object CourseRepository {

    fun getAllCourses(): List<Course> = courses

    fun getCourseById(id: Int): Course? = courses.find { it.id == id }

    fun getCoursesByLevel(level: CourseLevel): List<Course> = courses.filter { it.level == level }

    private val courses = listOf(
        Course(
            id = 1,
            title = "Forex Fundamentals",
            description = "Master the basics of currency trading, pip calculations, and market structure.",
            longDescription = "This comprehensive course covers everything a beginner needs to know about the foreign exchange market. You will learn how currency pairs work, what drives exchange rates, how to read charts, and how to place your first trades. The course includes practical exercises with demo accounts and real market scenarios to build your confidence before trading with real capital.",
            price = "$49.99",
            level = CourseLevel.BEGINNER,
            lessons = 24,
            duration = "8 hours",
            instructor = "James Mitchell",
            topics = listOf("Currency Pairs", "Pip Calculations", "Market Hours", "Order Types", "Demo Trading", "Risk Basics")
        ),
        Course(
            id = 2,
            title = "Technical Analysis Mastery",
            description = "Learn chart patterns, indicators, and price action strategies for consistent profits.",
            longDescription = "Dive deep into technical analysis with this intermediate-level course. You will study candlestick patterns, support and resistance levels, trend lines, and the most effective technical indicators including Moving Averages, RSI, MACD, and Bollinger Bands. Each module includes backtesting exercises and live chart analysis sessions to sharpen your pattern recognition skills.",
            price = "$89.99",
            level = CourseLevel.INTERMEDIATE,
            lessons = 36,
            duration = "14 hours",
            instructor = "Sarah Chen",
            topics = listOf("Candlestick Patterns", "Support & Resistance", "Moving Averages", "RSI & MACD", "Bollinger Bands", "Price Action")
        ),
        Course(
            id = 3,
            title = "Risk Management Pro",
            description = "Protect your capital with professional position sizing and risk control methods.",
            longDescription = "Risk management is the cornerstone of long-term trading success. This course teaches you how professional traders protect their capital through position sizing algorithms, stop-loss strategies, portfolio diversification, and drawdown management. You will build your own risk management plan and learn to calculate optimal lot sizes for every trade based on your account size and risk tolerance.",
            price = "$69.99",
            level = CourseLevel.INTERMEDIATE,
            lessons = 18,
            duration = "6 hours",
            instructor = "David Park",
            topics = listOf("Position Sizing", "Stop-Loss Strategies", "Risk-Reward Ratios", "Drawdown Control", "Portfolio Balance", "Capital Preservation")
        ),
        Course(
            id = 4,
            title = "Advanced Price Action",
            description = "Trade like institutional players using pure price action and order flow analysis.",
            longDescription = "This advanced course reveals how institutional traders read the market using price action alone. You will learn to identify smart money footprints, liquidity zones, order blocks, and fair value gaps. The course covers advanced concepts like market structure shifts, inducement patterns, and how to align your trades with the larger players. Suitable for traders who already have a solid foundation in technical analysis.",
            price = "$129.99",
            level = CourseLevel.ADVANCED,
            lessons = 42,
            duration = "18 hours",
            instructor = "Michael Torres",
            topics = listOf("Order Blocks", "Liquidity Zones", "Fair Value Gaps", "Market Structure", "Smart Money Concepts", "Institutional Flow")
        ),
        Course(
            id = 5,
            title = "Algorithmic Trading Systems",
            description = "Build and deploy automated trading bots using proven quantitative strategies.",
            longDescription = "Learn to design, backtest, and deploy algorithmic trading systems for the forex market. This expert-level course covers strategy development from concept to execution, including statistical analysis, optimization techniques, walk-forward testing, and live deployment on MetaTrader platforms. You will build multiple trading algorithms and learn to evaluate their performance using professional metrics.",
            price = "$199.99",
            level = CourseLevel.EXPERT,
            lessons = 48,
            duration = "22 hours",
            instructor = "Elena Vasquez",
            topics = listOf("Strategy Design", "Backtesting", "Optimization", "Walk-Forward Analysis", "MetaTrader Integration", "Performance Metrics")
        ),
        Course(
            id = 6,
            title = "Fundamental Analysis Deep Dive",
            description = "Understand macroeconomic drivers and central bank policies that move currency markets.",
            longDescription = "Go beyond charts and understand the economic forces that drive currency valuations. This course covers interest rate differentials, inflation metrics, employment data, GDP analysis, and central bank communication. You will learn to read economic calendars, anticipate market reactions to news releases, and combine fundamental analysis with technical setups for high-probability trades.",
            price = "$79.99",
            level = CourseLevel.INTERMEDIATE,
            lessons = 30,
            duration = "12 hours",
            instructor = "Robert Kim",
            topics = listOf("Interest Rates", "Economic Indicators", "Central Bank Policy", "News Trading", "Sentiment Analysis", "Macro Fundamentals")
        )
    )
}
