package br.com.derlandybelchior.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel("low")
    object Medium: ActivityLevel("medium")
    object High: ActivityLevel("high")

    companion object {
        fun fromString(name: String?): ActivityLevel {
            return when(name) {
                Low.name -> Low
                Medium.name -> Medium
                High.name -> High
                else -> Medium
            }
        }
    }
}