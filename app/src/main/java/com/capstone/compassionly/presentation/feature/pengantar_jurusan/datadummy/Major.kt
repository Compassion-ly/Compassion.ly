package com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy

data class Major (
    val majorId:Int,
    val majorName:String,
    val majorImage:String,
    val majorDefinition:String,
    val majorCourse: List<String>,
    val majorPersonality: List<String>,
    val futureProspect: List<String>
)