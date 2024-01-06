package br.com.derlandybelchior.core.data.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.derlandybelchior.core.domain.model.ActivityLevel
import br.com.derlandybelchior.core.domain.model.Gender
import br.com.derlandybelchior.core.domain.model.GoalType
import br.com.derlandybelchior.core.domain.model.UserInfo
import br.com.derlandybelchior.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharePref: SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharePref.edit {
            putString(Preferences.KEY_GENDER, gender.name)
        }
    }

    override fun saveAge(age: Int) {
        sharePref.edit {
            putInt(Preferences.KEY_AGE, age)
        }
    }

    override fun saveWeight(weight: Float) {
        sharePref.edit {
            putFloat(Preferences.KEY_WEIGHT, weight)
        }
    }

    override fun saveHeight(height: Int) {
        sharePref.edit {
            putInt(Preferences.KEY_HEIGHT, height)
        }
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharePref.edit {
            putString(Preferences.KEY_ACTIVITY_LEVEL, level.name)
        }
    }

    override fun saveGoalType(type: GoalType) {
        sharePref.edit {
            putString(Preferences.KEY_GOAL_TYPE, type.name)
        }
    }

    override fun saveCarbRatio(ratio: Float) {
        sharePref.edit {
            putFloat(Preferences.KEY_CARB_RATIO, ratio)
        }
    }

    override fun saveProteinRatio(ratio: Float) {
        sharePref.edit {
            putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
        }
    }

    override fun saveFatRatio(ratio: Float) {
        sharePref.edit {
            putFloat(Preferences.KEY_FAT_RATIO, ratio)
        }
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharePref.getInt(Preferences.KEY_AGE, -1)
        val height = sharePref.getInt(Preferences.KEY_HEIGHT, -1)
        val weight = sharePref.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = sharePref.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharePref.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharePref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharePref.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharePref.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharePref.getFloat(Preferences.KEY_FAT_RATIO, -1f)

        return UserInfo(
            age = age,
            gender = Gender.fromString(genderString),
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString),
            goalType = GoalType.fromString(goalType),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio,
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharePref.edit {
            putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
        }
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharePref.getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
    }
}