package com.forexmaster.app.data

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val longDescription: String,
    val price: String,
    val level: CourseLevel,
    val lessons: Int,
    val duration: String,
    val instructor: String,
    val topics: List<String>
)

enum class CourseLevel(val label: String) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    EXPERT("Expert")
}
