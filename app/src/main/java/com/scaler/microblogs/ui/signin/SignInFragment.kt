package com.scaler.microblogs.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scaler.microblogs.MainActivity
import com.scaler.microblogs.R
import com.scaler.microblogs.databinding.FragmentSignInBinding
import com.scaler.microblogs.ui.feed.FeedFragment

class SignInFragment:Fragment() {

    private lateinit var signInViewModel: SignInViewModel
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FeedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParentActivity().navView.visibility=View.GONE
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        binding.btnSignUp.setOnClickListener{getParentActivity().navController.navigate(R.id.action_sign_in_fragment_to_registration_fragment)}
        binding.signInLayout.setOnClickListener{getParentActivity().navController.navigate(R.id.action_sign_in_fragment_to_registration_fragment)}
    }

    private fun getParentActivity(): MainActivity = requireActivity() as MainActivity


}