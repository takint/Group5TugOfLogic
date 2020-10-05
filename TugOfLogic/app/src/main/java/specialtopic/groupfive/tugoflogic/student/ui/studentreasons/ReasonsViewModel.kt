package specialtopic.groupfive.tugoflogic.student.ui.studentreasons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReasonsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Reason Fragment"
    }
    val text: LiveData<String> = _text
}