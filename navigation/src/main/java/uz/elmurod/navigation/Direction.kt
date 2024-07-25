package uz.elmurod.navigation

sealed class Direction {
    object Main : Direction()
    object Saved: Direction()
    object Search : Direction()
}
