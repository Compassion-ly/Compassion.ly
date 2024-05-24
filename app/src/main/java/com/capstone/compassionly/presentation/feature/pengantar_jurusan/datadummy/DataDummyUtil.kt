package com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy

object DataDummyUtil {
    private val listCourse: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3","Matkul 1", "Matkul 2", "Matkul 3","Matkul 1", "Matkul 2", "Matkul 3","Matkul 1", "Matkul 2", "Matkul 3")
    private val listMajorPersonality: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3")
    private val listfutureProspect: List<String> = listOf("Matkul 1", "Matkul 2", "Matkul 3")
    fun getMajors(): ArrayList<Major> {
        val listMajors = ArrayList<Major>().apply {
            add(
                Major(
                    1,
                    "Jurusan A",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.",
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

            add(
                Major(
                    1,
                    "Jurusan A",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.",
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
            add(
                Major(
                    1,
                    "Jurusan A",
                    "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.",
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
    fun getCourses(): ArrayList<Course> {
        return arrayListOf(
            Course(
                1,
                "Matkul A",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul A merupakan asdf",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sagittis ornare neque et posuere.",
            ),
            Course(
                2,
                "Matkul B",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul B merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                3,
                "Matkul C",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul C merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                4,
                "Matkul D",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul D merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                5,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                6,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                7,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                8,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                9,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                10,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                11,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                12,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),
            Course(
                13,
                "Matkul E",
                "https://www.mcdelivery.co.id/id/static/1696172850912/assets/62/products/233023.png?",
                "Matkul E merupakan asdf",
                "Jurusan A merupakan asdf"
            ),

        )
    }
}
