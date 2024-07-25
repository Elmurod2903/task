package uz.elmurod.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

class Navigate {

    lateinit var navController: NavController
    private val navBuilder = NavOptions.Builder()

    fun navigate(direction: Direction, args: Any? = null) {

        when (direction) {
            Direction.Main -> {
                navController.navigate(BaseNavGraphDirections.toMain(), navBuilder.build())
            }
            Direction.Saved -> {
                navController.navigate(BaseNavGraphDirections.toSaved(), navBuilder.build())
            }
            Direction.Search -> {
                navController.navigate(BaseNavGraphDirections.toSearch(), navBuilder.build())
            }
        }

    }
}