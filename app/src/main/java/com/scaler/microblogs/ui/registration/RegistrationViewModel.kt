package com.scaler.microblogs.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.scaler.libconduit.requests.UserSignupData
import com.scaler.microblogs.repository.RegistrationRepository

class RegistrationViewModel: ViewModel() {

    fun postRegisterUser(userSignupData: UserSignupData)= RegistrationRepository.newInstance().postRegisterUser(userSignupData).asLiveData(viewModelScope.coroutineContext)
}