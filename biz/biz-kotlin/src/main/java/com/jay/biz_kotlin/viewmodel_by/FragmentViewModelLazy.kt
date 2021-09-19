package com.jay.biz_kotlin.viewmodel_by

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/17
 */
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.ViewModelProvider.Factory
import kotlin.reflect.KClass

/**
 * Returns a property delegate to access Activity's [ViewModel], if [factory] is specified
 * it will be used to create [ViewModel] first time.
 *
 * ```
 * class MyFragment : Fragment() {
 *     val viewmodel: NYViewModel by viewmodels()
 * }
 * ```
 *
 * This property can be accessed only after this Fragment is attached i.e., after
 * [Fragment.onAttach()], and access prior to that will result in IllegalArgumentException.
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(factory: Factory? = null): Lazy<VM> =
    FragmentViewModelLazy(this, VM::class, factory)

/**
 * An implementation of [Lazy] used by [Fragment.viewModels] tied to the given [fragment],
 * [viewModelClass], [factory]
 */
class FragmentViewModelLazy<VM : ViewModel>(
    private val fragment: Fragment,
    private val viewModelClass: KClass<VM>,
    private val factory: Factory?
) : Lazy<VM> {
    private var cached: VM? = null
    override val value: VM
        get() {
            var viewModel = cached
            if (viewModel == null) {
                val application = fragment.activity?.application
                    ?: throw IllegalArgumentException(
                        "ViewModel can be accessed " +
                                "only when Fragment is attached"
                    )
                val resolvedFactory = factory ?: AndroidViewModelFactory.getInstance(application)
                viewModel = ViewModelProvider(fragment, resolvedFactory).get(viewModelClass.java)
                cached = viewModel
            }
            return viewModel
        }

    override fun isInitialized() = cached != null
}