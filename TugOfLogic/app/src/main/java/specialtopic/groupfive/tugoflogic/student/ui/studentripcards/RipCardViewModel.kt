package specialtopic.groupfive.tugoflogic.student.ui.studentripcards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RipCardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is RIP Fragment"
    }
    val text: LiveData<String> = _text
}