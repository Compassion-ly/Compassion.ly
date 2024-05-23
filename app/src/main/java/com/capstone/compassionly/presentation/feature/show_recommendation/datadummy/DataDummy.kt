package com.capstone.compassionly.presentation.feature.show_recommendation.datadummy

object DataDummyUtil {
    private val listCourse: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3")
    private val listMajorPersonality: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3")
    private val listfutureProspect: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3")
    fun getMajors(): ArrayList<Major> {
        val listMajors = ArrayList<Major>().apply {
            add(
                Major(
                    1,
                    "Jurusan A",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Jurusan A merupakan asdf",
                    listCourse,
                    listMajorPersonality,
                    listfutureProspect
                ),
            )

            add(
                Major(
                    2,
                    "Jurusan B",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Jurusan B merupakan asdf",
                    listCourse,
                    listMajorPersonality,
                    listfutureProspect
                ),
            )

            add(
                Major(
                    3,
                    "Jurusan C",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Jurusan C merupakan asdf",
                    listCourse,
                    listMajorPersonality,
                    listfutureProspect
                ),
            )

            add(
                Major(
                    4,
                    "Jurusan D",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Jurusan D merupakan asdf",
                    listCourse,
                    listMajorPersonality,
                    listfutureProspect
                ),
            )

            add(
                Major(
                    5,
                    "Jurusan E",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Jurusan E merupakan asdf",
                    listCourse,
                    listMajorPersonality,
                    listfutureProspect
                ),
            )
        }
        return listMajors

    }
    fun getCategory(): ArrayList<Category> {
        return arrayListOf(
            Category(
                1,
                "Category A"
            ),
            Category(
                2,
                "Category B"
            ),
            Category(
                3,
                "Category B"
            ),
        )
    }
}