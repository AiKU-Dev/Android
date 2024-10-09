package com.aiku.presentation.ui.group.schedule.viewmodel

import androidx.lifecycle.ViewModel
import com.aiku.presentation.state.group.GroupState
import com.aiku.presentation.state.schedule.GroupScheduleOverviewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = WaitingScheduleViewModel.Factory::class)
class WaitingScheduleViewModel @AssistedInject constructor(
    @Assisted("group") private val group: GroupState,
    @Assisted("schedule") private val schedule: GroupScheduleOverviewState
) : ViewModel() {


    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("group") group: GroupState,
            @Assisted("schedule") schedule: GroupScheduleOverviewState
        ): WaitingScheduleViewModel
    }
}