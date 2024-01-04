package br.com.derlandybelchior.domain.di

import br.com.derlandybelchior.domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @Provides
    @ViewModelScoped
    fun providesValidateNutrientsUseCase(): ValidateNutrients{
        return ValidateNutrients()
    }
}