package uz.elmurod.core.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController


abstract class BaseFragment : Fragment {

    private var setupUI = false
    private var savedView: View? = null

    private var byViewBinding = false

    constructor() : super()
    constructor(layoutResId: Int) : super(layoutResId) {
        byViewBinding = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when {
            byViewBinding -> {
                setupUI = true
                super.onCreateView(inflater, container, savedInstanceState)
            }
            savedView != null -> savedView
            else -> {
                savedView = createView()
                setupUI = true
                savedView
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observe()
        if (setupUI) {
            setup()
            setupUI = false
        }
    }

    open fun createView(): View? {
        return null
    }

    open fun observe() {}
    open fun setup() {}


    fun <T> currentEntryLiveData(key: String) =
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

    fun finish() = findNavController().popBackStack()


    protected fun navigate(direction: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(direction.actionId)?.let {
            navigate(direction)
        }
    }

    protected fun navigate(direction: NavDirections, navExtras: Navigator.Extras) =
        with(findNavController()) {
            currentDestination?.getAction(direction.actionId)?.let {
                navigate(direction, navExtras)
            }
        }

    private val appCompatActivity: BaseActivity
        get() {
            return requireActivity() as BaseActivity
        }

}
