package uz.elmurod.navigation


interface Navigator {
    fun navigate(direction: Direction, args: Any? = null)
}