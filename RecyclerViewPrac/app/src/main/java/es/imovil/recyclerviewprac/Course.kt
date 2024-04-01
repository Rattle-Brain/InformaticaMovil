package es.imovil.recyclerviewprac

class Course(a: String, p: String) {
    var asignatura: String = a
    var profesor: String = p

    companion object {
        fun createCourseList(asignaturas: Array<String>, profesores: Array<String>): List<Course> {
            val courses = mutableListOf<Course>()
            if (asignaturas.size == profesores.size) {
                asignaturas.zip(profesores) { asig, prof ->
                    courses.add(Course(asig, prof))
                }
            }
            return courses.toList()
        }
    }
}