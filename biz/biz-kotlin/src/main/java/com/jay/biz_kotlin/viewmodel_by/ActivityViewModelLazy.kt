package com.jay.biz_kotlin.viewmodel_by

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/9/17
 */
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import kotlin.reflect.KClass


/**
 * Returns a [Lazy] delegate to access the ComponentActivity's ViewModel, if [factory] is specified
 * it will be used to create [ViewModel] first time.
 *
 * ```
 * class MyComponentActivity : ComponentActivity() {
 *     val viewmodel: MyViewModel by viewmodels()
 * }
 * ```
 *
 * This property can be accessed only after the Activity is attached to the Application,
 * and access prior to that will result in IllegalArgumentException.
 */
@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
    factory: ViewModelProvider.Factory? = null
): Lazy<VM> = ActivityViewModelLazy(this, VM::class, factory)

/**
 * An implementation of [Lazy] used by [ComponentActivity.viewModels] tied to the given [activity],
 * [viewModelClass], [factory]
 */
class ActivityViewModelLazy<VM : ViewModel>(
    private val activity: ComponentActivity,
    private val viewModelClass: KClass<VM>,
    private val factory: ViewModelProvider.Factory?
) : Lazy<VM> {
    private var cached: VM? = null
    override val value: VM
        get() {
            var viewModel = cached
            if (viewModel == null) {
                val application = activity.application
                    ?: throw IllegalArgumentException(
                        "ViewModel can be accessed " +
                                "only when Activity is attached"
                    )
                val resolvedFactory = factory ?: AndroidViewModelFactory.getInstance(application)
                viewModel = ViewModelProvider(activity, resolvedFactory).get(viewModelClass.java)
                cached = viewModel
            }
            return viewModel
        }

    override fun isInitialized() = cached != null
}