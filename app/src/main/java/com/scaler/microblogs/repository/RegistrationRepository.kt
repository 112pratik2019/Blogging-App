package com.scaler.microblogs.repository

import com.scaler.libconduit.ConduitClient
import com.scaler.libconduit.apis.ConduitApi
import com.scaler.libconduit.requests.UserSignupData
import com.scaler.libconduit.requests.UserSignupRequest
import com.scaler.microblogs.ui.feed.FeedFragment

class RegistrationRepository: BaseRepository() {


    companion object {
        fun newInstance() = RegistrationRepository()
    }

    fun postRegisterUser(userSignupData: UserSignupData) = getData { ConduitClient().api.registerUser(UserSignupRequest(userSignupData)) }

}