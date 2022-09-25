package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment:Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentLoginBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        binding.newLoginButton.setOnClickListener { view:View -> view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()) }
        binding.existingAccountButton.setOnClickListener { view:View -> view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()) }

        return binding.root
    }


}