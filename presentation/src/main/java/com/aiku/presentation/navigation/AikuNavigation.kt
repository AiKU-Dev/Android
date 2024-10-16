package com.aiku.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.aiku.domain.usecase.LoginUseCase
import com.aiku.presentation.navigation.route.BtmNav
import com.aiku.presentation.navigation.route.Routes
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import com.aiku.presentation.ui.component.navigation.BottomNavigation
import com.aiku.presentation.ui.group.GroupScreen
import com.aiku.presentation.ui.group.schedule.WaitingScheduleScreen
import com.aiku.presentation.ui.screen.home.HomeScreen
import com.aiku.presentation.ui.screen.login.composable.LoginScreen
import com.aiku.presentation.ui.screen.my.MyPageScreen
import com.aiku.presentation.ui.screen.schedule.MyScheduleScreen
import com.aiku.presentation.ui.screen.signup.composable.ProfileEditScreen
import com.aiku.presentation.ui.screen.signup.composable.TermsAgreementScreen
import com.aiku.presentation.ui.screen.signup.composable.TermsContentScreen
import com.aiku.presentation.ui.screen.splash.composable.SplashScreen
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.reflect.typeOf

@Composable
fun AikuNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    loginUseCase: LoginUseCase
) {

    var selectedBottomItem by remember { mutableStateOf(BtmNav.Home) }
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (backStackEntry.shouldShowBottomNav()) {
                BottomNavigation(
                    bottomItems = BtmNav.entries,
                    selectedBottomItem = selectedBottomItem,
                    onTabSelected = {
                        selectedBottomItem = it
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.Main.Graph,
            modifier = Modifier
        ) {
            composable<Routes.Splash> {
                SplashScreen(
                    loginUseCase = loginUseCase,
                    onComplete = { isSuccessful ->
                        navController.navigate(if (isSuccessful) Routes.Main.Graph else Routes.Auth.Graph) {
                            popUpTo<Routes.Splash> {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            navigation<Routes.Auth.Graph>(
                startDestination = Routes.Auth.Login,
            ) {
                composable<Routes.Auth.Login> {
                    LoginScreen(loginUseCase = loginUseCase, onLoginSuccess = {
                        navController.navigate(Routes.Main.Graph) {
                            popUpTo<Routes.Auth.Login> {
                                inclusive = true
                            }
                        }
                    })
                }
                composable<Routes.Auth.TermAgreement> {
                    TermsAgreementScreen(
                        onNavigateToProfileEditScreen = {
                            navController.navigate(Routes.Auth.ProfileEdit)
                        },
                        onNavigateToTermContentScreen = { identifier ->
                            navController.navigate(
                                Routes.Auth.TermContent(identifier)
                            )
                        }
                    )
                }
                composable<Routes.Auth.TermContent> { backStackEntry ->
                    val termContent = backStackEntry.toRoute<Routes.Auth.TermContent>()
                    val identifier = termContent.identifier
                    TermsContentScreen(identifier)
                }
                composable<Routes.Auth.ProfileEdit> {
                    ProfileEditScreen(
                        modifier = Modifier,
                        onCompleteEdit = {
                            navController.navigate(Routes.Main.Graph) {
                                popUpTo(Routes.Auth.Graph)
                            }
                        }
                    )
                }
            }

            navigation<Routes.Main.Graph>(
                startDestination = Routes.Main.Home,
            ) {
                composable<Routes.Main.Home> {
                    // 홈 화면
                    HomeScreen(
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        onGroupClicked = { groupId, groupName ->
                            navController.navigate(
                                Routes.Group.Group(groupId, groupName)
                            )
                        }
                    )
                }
                composable<Routes.Main.MySchedule> {
                    MyScheduleScreen()
                }
                composable<Routes.Main.MyPage> {
                    MyPageScreen()
                }

                composable<Routes.Main.Shop> {
                    // 상점 화면
                }
                composable<Routes.Main.Notification> {
                    // 알림 화면
                }
            }


            navigation<Routes.Group.Graph>(
                startDestination = Routes.Group.Group(1, "놀자놀자팟"),
            ) {
                composable<Routes.Group.Group> { backStackEntry ->
                    val groupArguments = backStackEntry.toRoute<Routes.Group.Group>()
                    val groupId = groupArguments.groupId
                    val groupName = groupArguments.groupName

                    GroupScreen(
                        groupId = groupId,
                        groupName = groupName,
                        onNavigateToWaitingScheduleScreen = { groupScheduleOverview, group ->
                            navController.navigate(
                                Routes.Group.ScheduleWaiting(
                                    group = group,
                                    groupScheduleOverview = groupScheduleOverview
                                )
                            )
                        },
                    )
                }
                composable<Routes.Group.ScheduleWaiting>(
                    typeMap = mapOf(
                        typeOf<GroupState>() to GroupNavType,
                        typeOf<GroupScheduleOverviewState>() to GroupScheduleOverviewNavType
                    )
                ) { backStackEntry ->
                    val group = backStackEntry.toRoute<Routes.Group.ScheduleWaiting>().group
                    val groupScheduleOverview =
                        backStackEntry.toRoute<Routes.Group.ScheduleWaiting>().groupScheduleOverview

                    WaitingScheduleScreen(
                        navController = navController,
                        group = group,
                        schedule = groupScheduleOverview
                    )
                }
                composable<Routes.Group.ScheduleRunning> {
                    // 진행 중 약속 화면
                }

                navigation<Routes.Group.CreateSchedule.Graph>(
                    startDestination = Routes.Group.CreateSchedule.First,
                ) {
                    composable<Routes.Group.CreateSchedule.First> {

                    }
                    // 약속 생성 화면 그래프
                }
            }
        }
    }

    LaunchedEffect(selectedBottomItem) {
        if (backStackEntry.shouldShowBottomNav()) {
            navController.navigate(
                when (selectedBottomItem) {
                    BtmNav.Home -> Routes.Main.Home
                    BtmNav.MySchedule -> Routes.Main.MySchedule
                    BtmNav.My -> Routes.Main.MyPage
                }
            ) {
                popUpTo<Routes.Main.Graph> {
                    inclusive = false
                }
                launchSingleTop = true
            }
        }
    }
}

@OptIn(ExperimentalSerializationApi::class)
private fun NavBackStackEntry?.shouldShowBottomNav(): Boolean {
    return when (this?.destination?.route) {
        Routes.Main.Home.serializer().descriptor.serialName,
        Routes.Main.MySchedule.serializer().descriptor.serialName,
        Routes.Main.MyPage.serializer().descriptor.serialName -> true

        else -> false
    }
}