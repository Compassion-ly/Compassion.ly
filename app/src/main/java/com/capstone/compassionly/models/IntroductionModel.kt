package com.capstone.compassionly.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IntroductionModel(
    val cover: Int?,
    val featureName: String?,
    val featureDescription: String?,
    val step: List<Step>?
): Parcelable

@Parcelize
data class Step(
    val picture: Int?,
    val descriptionStep: String?
): Parcelable
