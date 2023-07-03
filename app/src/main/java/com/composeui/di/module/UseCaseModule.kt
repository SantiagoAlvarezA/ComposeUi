package com.composeui.di.module

import com.composeui.data.repository.TaskRepository
import com.composeui.usecases.model.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideUseCase(repository: TaskRepository) =
        UseCase(repository)
}