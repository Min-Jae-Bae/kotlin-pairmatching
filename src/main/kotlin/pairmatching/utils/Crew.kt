package pairmatching.utils

import java.nio.file.Files
import java.nio.file.Paths

open class Crew {

    companion object {
        fun makeCrew(course: String): List<String> {
            val filePath = Paths.get(getPath(course))
            return Files.readAllLines(filePath, Charsets.UTF_8)
                .toString()
                .removePrefix("[")
                .removeSuffix("]")
                .split(" ")
        }


        private fun getPath(course: String): String {
            if (course == "백엔드") return BACKEND_CREW_PATH
            return FRONTEND_CREW_PATH
        }
    }
}

const val BACKEND_CREW_PATH = "src/main/kotlin/resources/backend-crew.md"
const val FRONTEND_CREW_PATH = "src/main/kotlin/resources/frontend-crew.md"

