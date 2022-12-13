package pairmatching.utils

import java.io.File

class Crew {

    companion object {
        fun makeCrewList(course: String): List<String> {
            val crewNameArray = arrayListOf<String>()
            var crewName: String?

            val crewFile = File(getPath(course))

            crewFile.bufferedReader().use {
                while (true) {
                    crewName = it.readLine()
                    crewName?.let { name -> crewNameArray.add(name) } ?: break
                }
            }
            return crewNameArray
        }


        private fun getPath(course: String): String {
            if (course == "백엔드") return BACKEND_CREW_PATH
            return FRONTEND_CREW_PATH
        }
    }
}

const val BACKEND_CREW_PATH = "src/main/kotlin/resources/backend-crew.md"
const val FRONTEND_CREW_PATH = "src/main/kotlin/resources/frontend-crew.md"

