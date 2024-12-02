package com.aiku.aiku.di

import com.aiku.domain.repository.GroupRepository
import com.aiku.domain.repository.KakaoRepository
import com.aiku.domain.repository.ScheduleRepository
import com.aiku.domain.repository.TermsRepository
import com.aiku.domain.repository.UserRepository
import com.aiku.domain.usecase.BetUseCase
import com.aiku.domain.usecase.CreateGroupUseCase
import com.aiku.domain.usecase.ReadTermsUseCase
import com.aiku.domain.usecase.SaveUserUseCase
import com.aiku.domain.usecase.group.FetchGroupsUseCase
import com.aiku.domain.usecase.schedule.ConvertLatLngToAddressUseCase
import com.aiku.domain.usecase.schedule.FetchUserSchedulesUseCase
import com.aiku.domain.usecase.schedule.SearchPlacesByKeywordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSaveUserUseCase(
        userRepository: UserRepository
    ): SaveUserUseCase {
        return SaveUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideReadTermUseCase(
        termsRepository: TermsRepository
    ): ReadTermsUseCase {
        return ReadTermsUseCase(termsRepository)
    }

    @Provides
    @Singleton
    fun provideBetUseCase(
        scheduleRepository: ScheduleRepository
    ): BetUseCase {
        return BetUseCase(scheduleRepository)
    }

    @Provides
    @Singleton
    fun provideCreateGroupUseCase(
        groupRepository: GroupRepository
    ): CreateGroupUseCase {
        return CreateGroupUseCase(groupRepository)
    }

    @Provides
    @Singleton
    fun provideFetchGroupsUseCase(
        groupRepository: GroupRepository
    ): FetchGroupsUseCase {
        return FetchGroupsUseCase(groupRepository)
    }

    @Provides
    @Singleton
    fun provideFetchUserSchedulesUseCase(
        scheduleRepository: ScheduleRepository
    ): FetchUserSchedulesUseCase {
        return FetchUserSchedulesUseCase(scheduleRepository)
    }

    @Provides
    @Singleton
    fun provideSearchPlacesByKeywordUseCase(
        kakaoRepository: KakaoRepository
    ): SearchPlacesByKeywordUseCase {
        return SearchPlacesByKeywordUseCase(kakaoRepository)
    }

    @Provides
    @Singleton
    fun provideConvertLatLngToAddressUseCase(
        kakaoRepository: KakaoRepository
    ): ConvertLatLngToAddressUseCase {
        return ConvertLatLngToAddressUseCase(kakaoRepository)
    }

}