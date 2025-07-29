package com.example.ntslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntslibrary.ui.theme.NTSLIBRARYTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.platform.LocalContext
class Books : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NTSLIBRARYTheme {
                BookScreenWithNavBar()
            }
        }
    }
}

// --- Navigation Item Data Class ---
data class NavItem(val label: String, val icon: ImageVector)

// --- Main Screen with Bottom Navigation ---
@Composable
fun BookScreenWithNavBar() {
    val navItems = listOf(
        NavItem("For You", Icons.Default.Home),
        NavItem("Explore", Icons.Default.Explore),
        NavItem("Library", Icons.Default.LibraryBooks)
    )
    var selectedNavIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = { TopAppBarContent() },
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedNavIndex == index,
                        onClick = { selectedNavIndex = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedNavIndex) {
            0 -> BookScreenContent(Modifier.padding(innerPadding))
            1 -> ExploreScreen(Modifier.padding(innerPadding))
            2 -> LibraryScreen(Modifier.padding(innerPadding))
        }
    }
}

// --- Top Toolbar ---
@Composable
fun TopAppBarContent() {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            (context as? android.app.Activity)?.finish()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { /* Handle share */ }) {
            Icon(Icons.Default.Share, contentDescription = "Share")
        }
    }
}

// --- Main Book Screen Content ---
@Composable
fun BookScreenContent(modifier: Modifier = Modifier) {
    val featuredBook = Book(
        "Echoes of the Ocean",
        "",
        "Nature's beauty, resilience, and intrinsic value are woven intricately into the fabric of our world. From the serene landscapes to the rugged terrains...",
        5.0f,
        android.R.drawable.ic_menu_gallery
    )

    val books = listOf(
        Book("Silence", "Ava Morgan", "Delve into the depths of human emotions and...", 4.5f, android.R.drawable.ic_menu_gallery),
        Book("Evolving Over Time", "Olivia Bennett", "A tapestry woven from the threads of change...", 4.0f, android.R.drawable.ic_menu_gallery),
        Book("The Secret Key", "Noah Ramirez", "Within its pages lies a hidden world, waiting...", 3.5f, android.R.drawable.ic_menu_gallery),
        Book("Spectrum of Dreams", "Benjamin Clarke", "In this tapestry of tales, dreams weave their vibrant...", 5.0f, android.R.drawable.ic_menu_gallery),
        Book("Beyond the Horizon", "Amelia Khan", "A journey into uncharted territories that stretch...", 4.5f, android.R.drawable.ic_menu_gallery)
    )

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(8.dp))

        // --- Featured Book ---
        Image(
            painter = painterResource(id = featuredBook.coverImage),
            contentDescription = "Featured Book Cover",
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = featuredBook.title,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }
        val descText = featuredBook.description
        val displayText = if (!expanded && descText.length > 80) descText.take(80) + "..." else descText

        Text(
            displayText,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.DarkGray
        )

        if (!expanded && descText.length > 80) {
            Text(
                "More",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { expanded = true }
            )
        }

        Spacer(Modifier.height(16.dp))

        // --- Books List ---
        books.forEach { book ->
            BookListItem(book)
            Divider()
        }

        Spacer(Modifier.height(16.dp))
    }
}

// --- Book List Item ---
@Composable
fun BookListItem(book: Book) {
    Row(
        Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = book.coverImage),
            contentDescription = "Book cover",
            modifier = Modifier
                .size(56.dp)
                .align(Alignment.CenterVertically)
        )
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(book.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(book.author, fontSize = 14.sp, color = Color.Gray)
            Text(
                book.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            RatingBar(book.rating)
        }
        IconButton(onClick = { /* bookmark logic */ }) {
            Icon(Icons.Default.BookmarkBorder, contentDescription = "Bookmark")
        }
    }
}

// --- Rating Bar ---
@Composable
fun RatingBar(rating: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..5) {
            Icon(
                imageVector = if (rating >= i) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(16.dp)
            )
        }
        Text(text = String.format("  (%.1f)", rating), fontSize = 13.sp)
    }
}

// --- Book Data Class ---
data class Book(
    val title: String,
    val author: String,
    val description: String,
    val rating: Float,
    val coverImage: Int
)

// --- Explore Screen & Helpers ---
@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 60.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        // Title
        Text(
            "Explore",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(14.dp))
        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Title, author, host, or topic", color = Color(0xFFB0B8C1)) },
            leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color(0xFFB0B8C1)) },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE8EDF2), focusedBorderColor = Color(0xFF246BFD)
            )
        )
        Spacer(Modifier.height(18.dp))
        // Categories
        Text(
            "Categories",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ExploreCategories()
        Spacer(Modifier.height(16.dp))
        // This New Story Section
        SectionHeader("This New Story")
        ExploreBookRow(books = sampleExploreBooks, showCoin = true)
        // Popular Section
        SectionHeader("Popular")
        ExploreBookRow(books = samplePopularBooks)
        // Collections of You
        SectionHeader("Collections of You", subHeader = "Read and Get to Know Yourself")
        CollectionsOfYouRow(collections = sampleCollections)
        // Membership Only Section
        Text(
            "Membership Only",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        MembershipCard(title = "Bookstore", subtitle = "Discover new books curated just for you")
        MembershipCard(title = "Audiobooks", subtitle = "Enjoy Audiobooks Narrated by Top Voices")
        Spacer(Modifier.height(30.dp))
    }
}

@Composable
fun SectionHeader(title: String, subHeader: String? = null) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            if (subHeader != null) {
                Text(subHeader, color = Color.Gray, fontSize = 13.sp)
            }
        }
        Text(
            "More",
            color = Color(0xFF246BFD),
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 6.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreCategories() {
    val tags = listOf(
        "Management", "Creativity", "Environment", "Career & success",
        "Technology", "Health", "Develop", "Design", "Psychology"
    )
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        tags.forEach { Chip(it) }
    }
}

@Composable
fun Chip(label: String) {
    Surface(
        color = Color(0xFFF3F6FA),
        shape = RoundedCornerShape(50),
        tonalElevation = 0.dp,
        modifier = Modifier.height(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFF246BFD),
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(5.dp))
            Text(label, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color(0xFF192A3E))
        }
    }
}

@Composable
fun ExploreBookRow(books: List<ExploreBook>, showCoin: Boolean = false) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books) { book ->
            ExploreBookCard(book, showCoin = showCoin)
        }
    }
    Spacer(Modifier.height(8.dp))
}

@Composable
fun ExploreBookCard(book: ExploreBook, showCoin: Boolean = false) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .width(148.dp)
            .height(220.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Cover", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            }
            Column(Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                if (showCoin) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Filled.MonetizationOn,
                            contentDescription = null,
                            tint = Color(0xFFFFC72C),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(3.dp))
                        Text(
                            "100",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color(0xFF476184)
                        )
                        Spacer(Modifier.width(5.dp))
                    }
                }
                Text(
                    book.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 1
                )
                Text(book.author, fontSize = 13.sp, color = Color.Gray, maxLines = 1)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC72C),
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        "3,2N",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CollectionsOfYouRow(collections: List<CollectionBook>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(collections) { book ->
            CollectionBookCard(book)
        }
    }
    Spacer(Modifier.height(14.dp))
}

@Composable
fun CollectionBookCard(book: CollectionBook) {
    Card(
        shape = RoundedCornerShape(13.dp),
        modifier = Modifier
            .width(145.dp)
            .height(172.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(94.dp)
                    .background(Color(0xFFDFECFA)),
                contentAlignment = Alignment.Center
            ) {
                Text("Cover", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
            }
            Column(Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                Text(book.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 2)
                Text(
                    book.subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun MembershipCard(title: String, subtitle: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1EEFB)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 7.dp)
            .height(72.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(subtitle, fontSize = 13.sp, color = Color(0xFF476184))
            }
            Box(
                Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFAACFF7), Color(0xFFD7E9FB))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Book,
                    contentDescription = null,
                    tint = Color(0xFF246BFD),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

// --- Explore Sample Models ---
data class ExploreBook(val title: String, val author: String)

val sampleExploreBooks = listOf(
    ExploreBook("Journey to the...", "Marwa Saleh"),
    ExploreBook("A Journey of Im...", "Alexander Hayes"),
    ExploreBook("The Secret Key", "Noah Ramirez")
)
val samplePopularBooks = listOf(
    ExploreBook("Spectrum of Dre...", "Ben Clarke"),
    ExploreBook("Dance of Shad...", "Isabella Patel"),
    ExploreBook("Mysteries of the...", "Lucas Carter")
)

data class CollectionBook(val title: String, val subtitle: String)

val sampleCollections = listOf(
    CollectionBook("The Ocean", "Nature's beauty, resilience and intrinsic value"),
    CollectionBook("Chasing Dreams", "Encourage individual to step out their comfort zones")
)

// --- Library Screen ---
@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    val sections = listOf(
        LibrarySection("Saved", "4 items", Icons.Default.BookmarkBorder),
        LibrarySection("Favorites", "10 items", Icons.Default.FavoriteBorder),
        LibrarySection("Collections", "7 items", Icons.Default.ViewList),
        LibrarySection("Downloads", "0 items", Icons.Default.Download),
        LibrarySection("Finished", "2 items", Icons.Default.Done)
    )
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        Spacer(Modifier.height(28.dp))
        Text(
            "My Library",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(start = 20.dp, bottom = 24.dp)
        )
        sections.forEach { section ->
            LibraryItemRow(section)
            Divider(color = Color(0xFFF4F6F8))
        }
    }
}

data class LibrarySection(val name: String, val info: String, val icon: ImageVector)

@Composable
fun LibraryItemRow(section: LibrarySection) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { /* TODO: Item click */ }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            section.icon,
            contentDescription = null,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                section.name,
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
            )
            Text(
                section.info,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color(0xFF8696A6)
            )
        }
        Icon(
            Icons.Default.KeyboardArrowRight,
            contentDescription = "Go",
            modifier = Modifier.size(22.dp),
            tint = Color(0xFFB8C1D0)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BookScreenPreview() {
    NTSLIBRARYTheme {
        BookScreenWithNavBar()
    }
}