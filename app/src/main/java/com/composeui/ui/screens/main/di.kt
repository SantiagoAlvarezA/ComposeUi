package com.composeui.ui.screens.main

import com.composeui.data.repository.Repository
import com.composeui.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    @ViewModelScoped
    fun provideUseCase(repository: Repository) =
        UseCase(repository)
}