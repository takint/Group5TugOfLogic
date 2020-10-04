package specialtopic.groupfive.tugoflogic.student

class Student(val name: String, val isOnline: Boolean) {

    companion object {
        //for testing first, replace by data later
        private var lastContactId = 0
        fun createStudentsList(numStudents: Int) : ArrayList<Student> {
            val students = ArrayList<Student>()
            for (i in 1..numStudents) {
                students.add(Student("Student " + ++lastContactId, i <= numStudents / 2))
            }
            return students
        }


        /**
         * Read students from database
         */
         fun readAllStudent(): ArrayList<Student>{
            val students = ArrayList<Student>()
            return students
        }
    }


}