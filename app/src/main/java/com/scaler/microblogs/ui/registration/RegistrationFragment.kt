package com.scaler.microblogs.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.scaler.libconduit.data.DataState
import com.scaler.libconduit.requests.UserSignupData
import com.scaler.microblogs.MainActivity
import com.scaler.microblogs.R
import com.scaler.microblogs.databinding.FragmentRegistrationBinding
import com.scaler.microblogs.ui.feed.FeedFragment

class RegistrationFragment : Fragment() {


    private lateinit var registrationViewModel: RegistrationViewModel
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FeedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationViewModel= ViewModelProvider(this).get(RegistrationViewModel::class.java)
        getParentActivity().navView.visibility=View.GONE
        setOnClickListeners()
    }


    private fun setOnClickListeners(){
        binding.btnSignUp.setOnClickListener{observerRegisterUser()}
        binding.signInLayout.setOnClickListener{getParentActivity().navController.navigate(R.id.action_registration_fragment_to_sign_in_fragment)}
    }

    private fun getParentActivity(): MainActivity = requireActivity() as MainActivity


    private fun observerRegisterUser(){
        registrationViewModel.postRegisterUser(UserSignupData(binding.etEmail.toString(),
        binding.password.toString(),
        binding.etUsername.toString())).observe(viewLifecycleOwner,{
            when(it){
                is DataState.Loading -> {
                    binding.progressDialog.progressDialog.visibility=View.VISIBLE

                }
                is DataState.Success -> {
                    binding.progressDialog.progressDialog.visibility=View.GONE
                    showSnackBar("Success ${it.data.user?.token}")


                }
                is DataState.Error -> {
                    binding.progressDialog.progressDialog.visibility=View.GONE
                    showSnackBar(it.statusDescription)

                }
            }
        })
    }

    fun showSnackBar(message :String){
        val snackBar= Snackbar.make(getParentActivity().findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        snackBar.show()
    }



}