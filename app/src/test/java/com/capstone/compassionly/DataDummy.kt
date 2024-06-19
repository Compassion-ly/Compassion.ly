package com.capstone.compassionly

import com.capstone.compassionly.models.DataItem
import com.capstone.compassionly.models.DetailUserModel
import com.capstone.compassionly.models.SchoolMajor
import com.capstone.compassionly.models.SchoolModel
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.models.User

object DataDummy {

    fun generateDummyGetUser(): DetailUserModel {
        return DetailUserModel(
            school = SchoolModel("Pabuaran", 1, "Pekalongan", "2234515", "Jawa Tengah"),
            schoolMajor = SchoolMajor(1, "TKJ"),
            user = User(
                "sdf",
                true,
                "male",
                1,
                intArrayOf(1, 2, 4).toList(),
                "andres",
                "9879875",
                1
            )
        )
    }

    fun getListTopic() : List<TopicModel>{
        val data = mutableListOf<TopicModel>()
        data.add(TopicModel(1,"sampel", "sampel", floatArrayOf(1.2.toFloat(),3.1.toFloat()).toList(), 1, "sampel", "sampel", "sample"))
        return data
    }

}