package app.atom.navigation_bar

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AtomNavbar() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(128.dp)
            .background(color = Color.Gray.copy(alpha = 0.1F))
            .padding(horizontal = 32.dp, vertical = 26.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("")
        TextButton(

            onClick = {
                val startMain = Intent(Intent.ACTION_MAIN)
                startMain.addCategory(Intent.CATEGORY_HOME)
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(startMain)
            }) {
            Icon(
                modifier = Modifier.size(44.dp),
                tint = Color.Unspecified,
                painter = painterResource(id = R.drawable.ic_nav_home),
                contentDescription = null
            )
        }
        Text("")
    }
}

@Preview(showBackground = true)
@Composable
fun AtomNavbarPreview() {
    AtomNavbar()
}

