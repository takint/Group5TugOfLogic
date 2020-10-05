package specialtopic.groupfive.tugoflogic.instructor.ui.instructorgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructorGameViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to \nTug of Logic"
    }
    val text: LiveData<String> = _text
}