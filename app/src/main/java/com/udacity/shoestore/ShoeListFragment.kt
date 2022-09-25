package com.udacity.shoestore

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding


class ShoeListFragment:Fragment(){
    private  val viewModel:SharedShoeListViewModel by activityViewModels()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentShoeListBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_list,container,false)

        binding.addFloating.setOnClickListener{view:View-> view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())}

        setHasOptionsMenu(true)










        viewModel.shoeList.observe(viewLifecycleOwner, Observer{ updatedShoeList->
            for(element in updatedShoeList) {
                val mytext = TextView(context)
                mytext.text=getString(R.string.shoe,element.name,element.company,StringDoubleConverter.toString(element.size),element.description)
                mytext.setTextAppearance(R.style.textview_style2)
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                params.setMargins(16,16,16,16);
                mytext.layoutParams = params
                Log.i("Image","image: $element")
                if(element.imageUri!=null){
                    Log.i("Image","image: $element")
                    val myImageView=ImageView(context)
                    myImageView.setImageURI(element.imageUri)
                    binding.linearLayout.addView(myImageView)
                }
                binding.linearLayout.addView(mytext)



            }

        })

        return binding.root
    }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       super.onCreateOptionsMenu(menu, inflater)
       inflater.inflate(R.menu.logout_menu,menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}