package app.atom.navigation_bar

import android.content.Intent
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class AtomNavBarService : LifecycleService(), SavedStateRegistryOwner {

    private val savedStateRegistryController = SavedStateRegistryController.create(this)

    private lateinit var contentView: View

    override fun onCreate() {
        super.onCreate()

        savedStateRegistryController.performAttach()
        savedStateRegistryController.performRestore(null)

        contentView = ComposeView(this).apply {
            setViewTreeSavedStateRegistryOwner(this@AtomNavBarService)
            setContent {
                AtomNavbar();
            }
        }
        contentView.setViewTreeLifecycleOwner(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val lp = WindowManager.LayoutParams()
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY
        lp.format = PixelFormat.RGBA_8888
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.flags = WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED or
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        lp.gravity = Gravity.BOTTOM
        wm.addView(contentView, lp)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.removeView(contentView)
    }

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry
}