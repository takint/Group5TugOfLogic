package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

class tempUser(val name: String, val isOnline: Boolean) {

    companion object {
        //for testing first, replace by data later
        private var lastContactId = 0
        fun createStudentsList(numStudents: Int) : ArrayList<tempUser> {
            val students = ArrayList<tempUser>()
            for (i in 1..numStudents) {
                students.add(tempUser("Student " + ++lastContactId, i <= numStudents / 2))
            }
            return students
        }


        /**
         * Read students from database
         */
         fun readAllStudent(): ArrayList<tempUser>{
            val students = ArrayList<tempUser>()
            return students
        }
    }


}