package com.capstone.compassionly.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopicModel (
    val id: Int,
    val topic: String,
    val picture: Int,
    val short: String,
    val material: List<Material>
): Parcelable

@Parcelize
data class Material (
    val id: Int,
    val course: String,
    val listImage : List<ImageMaterial>
): Parcelable

@Parcelize
data class ImageMaterial (
    val detailImage: Int
):Parcelable