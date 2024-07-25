package uz.elmurod.navigation.ktx

import androidx.fragment.app.Fragment
import uz.elmurod.navigation.Navigator

val Fragment.navController: Navigator
    get() = activity as Navigator