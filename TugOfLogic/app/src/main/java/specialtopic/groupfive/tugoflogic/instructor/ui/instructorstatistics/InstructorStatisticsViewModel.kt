package specialtopic.groupfive.tugoflogic.instructor.ui.instructorstatistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructorStatisticsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Game Statistics"
    }
    val text: LiveData<String> = _text
}