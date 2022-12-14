package pairmatching.domain

enum class Course(private val course: String) {
    BACKEND("백엔드"), FRONTEND("프론트엔드");

    companion object {
        fun courseList(): List<String> = values().map { it.course }

        fun convertCourse(course: String) = when (course) {
                "백엔드" -> BACKEND
                else -> FRONTEND
            }

    }
}