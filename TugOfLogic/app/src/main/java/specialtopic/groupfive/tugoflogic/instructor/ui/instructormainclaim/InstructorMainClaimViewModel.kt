package specialtopic.groupfive.tugoflogic.instructor.ui.instructormainclaim

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructorMainClaimViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Main Claim Fragment"
    }
    val text: LiveData<String> = _text
}