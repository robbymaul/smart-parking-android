import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.smartparking.activity.ActivationActivity
import com.dev.smartparking.activity.AuthForgotPasswordActivity
import com.dev.smartparking.activity.IndexActivity
import com.dev.smartparking.activity.IntroActivity
import com.dev.smartparking.activity.LoginActivity
import com.dev.smartparking.activity.OtpActivity
import com.dev.smartparking.activity.PaymentActivity
import com.dev.smartparking.activity.RegisterActivity
import com.dev.smartparking.route.Screen
import com.dev.smartparking.ui.screen.DetailMallScreen
import com.dev.smartparking.ui.screen.DetailTicketScreen
import com.dev.smartparking.ui.screen.ParkingSlotScreen
import com.dev.smartparking.ui.screen.PaymentScreen
import com.dev.smartparking.ui.screen.SplashScreen


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        startDestination = Screen.Splash.route,
        navController = navController,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(Screen.Intro.route) {
            IntroActivity(
                navController = navController
            )
        }
        composable(Screen.Login.route) {
            LoginActivity(
                navController = navController
            )
        }
        composable(Screen.Register.route) {
            RegisterActivity(
                navController = navController
            )
        }
        composable(Screen.ForgotPassword.route) {
            AuthForgotPasswordActivity(
                navController = navController
            )
        }
        composable(Screen.OTP.route) {
            OtpActivity(
                navController = navController
            )
        }
        composable(Screen.Activation.route) {
            ActivationActivity(
                navController = navController
            )
        }

        composable(Screen.Main.route) {
            IndexActivity(
                navController = navController
            )
        }

        composable(Screen.DetailMall.route) {
            DetailMallScreen(
                navController = navController
            )
        }

        composable(Screen.Parking.route) {
            ParkingSlotScreen(
                navController = navController
            )
        }

        composable(Screen.Payment.route) {
            PaymentActivity(
                navController = navController
            )
        }

        composable(Screen.DetailTicket.route) {
            DetailTicketScreen(
                navController = navController
            )
        }
    }
}