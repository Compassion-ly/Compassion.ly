package com.capstone.compassionly.presentation.feature.show_recommendation.datadummy

data class Major (
    val majorId:Int,
    val majorName:String,
    val majorImage:String,
    val majorDefinition:String,
    val majorCourse: List<String>,
    val majorPersonality: List<String>,
    val futureProspect: List<String>
)