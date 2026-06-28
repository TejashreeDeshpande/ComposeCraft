package com.example.composecraft.features.instacart.data.datasource

import com.example.composecraft.features.instacart.domain.model.*

object InstacartMockDataSource {

    val categories = listOf(
        Category("fruits", "Fruits & Vegetables", "🥦", 0xFF4CAF50),
        Category("dairy", "Dairy & Eggs", "🥛", 0xFF2196F3),
        Category("meat", "Meat & Seafood", "🥩", 0xFFE53935),
        Category("bakery", "Bakery & Bread", "🍞", 0xFFFF9800),
        Category("frozen", "Frozen Foods", "🧊", 0xFF9C27B0),
        Category("snacks", "Snacks & Chips", "🍿", 0xFFFF5722),
        Category("beverages", "Beverages", "🥤", 0xFF00BCD4),
        Category("pantry", "Pantry & Dry Goods", "🥫", 0xFF795548),
        Category("personal", "Personal Care", "🧴", 0xFFE91E63),
        Category("household", "Household", "🧹", 0xFF607D8B)
    )

    val products = listOf(
        // Fruits & Vegetables
        Product("p1", "Organic Bananas", "Dole", 1.49, null, "bunch", "🍌", "fruits", 4.8f, 2134, true, true, listOf("organic", "fresh")),
        Product("p2", "Strawberries", "Driscoll's", 4.99, 6.49, "16 oz", "🍓", "fruits", 4.7f, 987, true, false, listOf("fresh", "sale")),
        Product("p3", "Avocados", "Organic", 2.99, null, "each", "🥑", "fruits", 4.6f, 1456, true, true, listOf("organic", "ripe")),
        Product("p4", "Baby Spinach", "Taylor Farms", 3.49, null, "5 oz", "🥬", "fruits", 4.5f, 743, true, true),
        Product("p5", "Cherry Tomatoes", "Sunset", 3.99, 4.99, "1 pint", "🍅", "fruits", 4.4f, 612, true, false, listOf("sale")),
        Product("p6", "Lemons", "Sunkist", 0.79, null, "each", "🍋", "fruits", 4.3f, 892, true, false),
        Product("p7", "Broccoli Crown", "Dole", 2.49, null, "each", "🥦", "fruits", 4.6f, 521, true, false),
        Product("p8", "Blueberries", "Driscoll's", 5.99, 7.49, "18 oz", "🫐", "fruits", 4.9f, 1823, true, false, listOf("sale", "premium")),

        // Dairy & Eggs
        Product("p9", "Whole Milk", "Organic Valley", 5.49, null, "1 gallon", "🥛", "dairy", 4.8f, 3421, true, true, listOf("organic")),
        Product("p10", "Large Eggs", "Vital Farms", 7.99, 9.99, "12 count", "🥚", "dairy", 4.7f, 2156, true, true, listOf("organic", "pasture-raised")),
        Product("p11", "Greek Yogurt", "Chobani", 1.49, null, "5.3 oz", "🍶", "dairy", 4.6f, 1876, true, false),
        Product("p12", "Butter", "Kerrygold", 5.99, null, "8 oz", "🧈", "dairy", 4.9f, 4532, true, false, listOf("premium")),
        Product("p13", "Cheddar Cheese", "Tillamook", 6.49, 7.99, "16 oz", "🧀", "dairy", 4.7f, 2341, true, false, listOf("sale")),
        Product("p14", "Heavy Cream", "Organic Valley", 4.29, null, "1 pint", "🫙", "dairy", 4.5f, 876, true, true),

        // Meat & Seafood
        Product("p15", "Chicken Breast", "Perdue", 8.99, 10.99, "per lb", "🍗", "meat", 4.5f, 1234, true, false, listOf("sale")),
        Product("p16", "Salmon Fillet", "Wild-Caught", 12.99, null, "per lb", "🐟", "meat", 4.8f, 987, true, false, listOf("premium", "wild-caught")),
        Product("p17", "Ground Beef 80/20", "Laura's Lean", 6.99, null, "per lb", "🥩", "meat", 4.4f, 1543, true, false),
        Product("p18", "Shrimp Large", "Sea Best", 11.99, 14.99, "1 lb", "🦐", "meat", 4.6f, 654, true, false, listOf("sale")),

        // Bakery
        Product("p19", "Sourdough Bread", "Boudin", 4.99, null, "24 oz", "🍞", "bakery", 4.8f, 2134, true, false, listOf("artisan")),
        Product("p20", "Croissants", "La Brea", 5.49, null, "6 count", "🥐", "bakery", 4.7f, 987, true, false),
        Product("p21", "Bagels", "Thomas'", 3.99, 4.99, "6 count", "🥯", "bakery", 4.5f, 1432, true, false, listOf("sale")),
        Product("p22", "Muffins Blueberry", "Otis Spunkmeyer", 4.49, null, "4 count", "🧁", "bakery", 4.3f, 765, true, false),

        // Frozen
        Product("p23", "Pizza Margherita", "Amy's", 8.99, 10.99, "13 oz", "🍕", "frozen", 4.6f, 1876, true, true, listOf("organic", "sale")),
        Product("p24", "Ice Cream Vanilla", "Ben & Jerry's", 5.49, null, "16 oz", "🍦", "frozen", 4.9f, 4321, true, false, listOf("premium")),
        Product("p25", "Edamame", "Seapoint Farms", 3.99, null, "14 oz", "🫛", "frozen", 4.7f, 876, true, false),
        Product("p26", "Waffles", "Eggo", 3.49, 4.49, "10 count", "🧇", "frozen", 4.4f, 2134, true, false, listOf("sale")),

        // Snacks
        Product("p27", "Tortilla Chips", "Tostitos", 4.29, 5.49, "12 oz", "🌮", "snacks", 4.5f, 3421, true, false, listOf("sale")),
        Product("p28", "Almonds", "Blue Diamond", 7.99, null, "16 oz", "🥜", "snacks", 4.7f, 2156, true, false, listOf("healthy")),
        Product("p29", "Popcorn", "Skinny Pop", 4.99, null, "4.4 oz", "🍿", "snacks", 4.6f, 1876, true, false, listOf("gluten-free")),
        Product("p30", "Dark Chocolate", "Lindt 70%", 3.99, null, "3.5 oz", "🍫", "snacks", 4.8f, 2341, true, false, listOf("premium")),

        // Beverages
        Product("p31", "Orange Juice", "Tropicana", 4.99, 6.49, "52 oz", "🍊", "beverages", 4.6f, 2134, true, false, listOf("sale")),
        Product("p32", "Sparkling Water", "LaCroix", 5.49, null, "12 pack", "💧", "beverages", 4.7f, 3421, true, false, listOf("variety pack")),
        Product("p33", "Cold Brew Coffee", "Chameleon", 6.99, null, "32 oz", "☕", "beverages", 4.8f, 987, true, true, listOf("organic")),
        Product("p34", "Kombucha", "GT's", 3.99, 4.99, "16 oz", "🫖", "beverages", 4.5f, 1543, true, true, listOf("organic", "sale")),

        // Pantry
        Product("p35", "Olive Oil", "California Olive Ranch", 9.99, 12.99, "16.9 fl oz", "🫙", "pantry", 4.8f, 2876, true, false, listOf("extra virgin", "sale")),
        Product("p36", "Pasta Penne", "Barilla", 1.99, null, "16 oz", "🍝", "pantry", 4.6f, 1432, true, false),
        Product("p37", "Canned Tomatoes", "Muir Glen", 2.99, null, "28 oz", "🍅", "pantry", 4.7f, 876, true, true, listOf("organic")),
        Product("p38", "Oatmeal", "Quaker", 4.49, 5.99, "42 oz", "🥣", "pantry", 4.5f, 2134, true, false, listOf("sale"))
    )

    val stores = listOf(
        Store("s1", "Whole Foods Market", "🌿", "30-60 min", 35.0),
        Store("s2", "Costco", "🏪", "45-75 min", 50.0),
        Store("s3", "Trader Joe's", "🛒", "25-50 min", 30.0),
        Store("s4", "Safeway", "🏬", "20-45 min", 25.0)
    )

    val addresses = listOf(
        Address("a1", "Home", "123 Main Street", "San Francisco", "CA", "94105"),
        Address("a2", "Work", "456 Market Street", "San Francisco", "CA", "94103")
    )

    val deliverySlots = listOf(
        DeliverySlot("d1", "As soon as possible", "30-60 min", 5.99),
        DeliverySlot("d2", "Today 2pm - 4pm", "2:00 PM - 4:00 PM", 3.99),
        DeliverySlot("d3", "Today 4pm - 6pm", "4:00 PM - 6:00 PM", 3.99),
        DeliverySlot("d4", "Today 6pm - 8pm", "6:00 PM - 8:00 PM", 0.0),
        DeliverySlot("d5", "Tomorrow 9am - 11am", "9:00 AM - 11:00 AM", 0.0),
        DeliverySlot("d6", "Tomorrow 11am - 1pm", "11:00 AM - 1:00 PM", 0.0)
    )

    val promoCodes = mapOf(
        "SAVE10" to 10.0,
        "FRESH20" to 20.0,
        "FIRSTORDER" to 15.0
    )

    fun getDeals(): List<Deal> = listOf(
        Deal(products.first { it.id == "p2" }, 23, "2h 45m"),
        Deal(products.first { it.id == "p10" }, 20, "5h 12m"),
        Deal(products.first { it.id == "p13" }, 19, "Tomorrow"),
        Deal(products.first { it.id == "p15" }, 18, "3h 30m"),
        Deal(products.first { it.id == "p23" }, 18, "Tomorrow"),
        Deal(products.first { it.id == "p27" }, 22, "1h 55m")
    )
}
