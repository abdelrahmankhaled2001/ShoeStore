 package com.udacity.shoestore

 import android.Manifest.permission.READ_EXTERNAL_STORAGE
 import android.app.Activity
 import android.app.AlertDialog
 import android.content.ContentResolver
 import android.content.DialogInterface
 import android.content.Intent
 import android.content.pm.PackageManager
 import android.graphics.Bitmap
 import android.net.Uri
 import android.os.Bundle
 import android.provider.MediaStore
 import android.provider.Settings
 import android.util.Log
 import android.view.LayoutInflater
 import android.view.View
 import android.view.ViewGroup
 import androidx.core.app.ActivityCompat
 import androidx.core.content.ContextCompat
 import androidx.databinding.DataBindingUtil
 import androidx.fragment.app.Fragment
 import androidx.fragment.app.activityViewModels
 import androidx.navigation.findNavController
 import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
 import com.udacity.shoestore.models.Shoe
 import java.lang.Exception
 import java.util.jar.Manifest

 class ShoeDetailFragment :Fragment(){
     private  val viewModel:SharedShoeListViewModel by activityViewModels()
     private lateinit var binding: FragmentShoeDetailBinding
     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
          binding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_detail,container,false)
         binding.newShoe= Shoe("",0.0,"","", mutableListOf(), null)
         binding.cancelButton.setOnClickListener { view:View->view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()) }
         binding.saveButton.setOnClickListener { view:View->
             viewModel.addToShoeList(binding.newShoe)
             view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())}
         binding.uploadImageButton.setOnClickListener{
             if(askForPermissions()) //Don't forget to add <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/> above the application tag in android manifest
             openGalleryForImage()
         }


         return binding.root
     }

     private fun openGalleryForImage() {
         val intent = Intent(Intent.ACTION_PICK)
         intent.type = "image/*"
         startActivityForResult(intent, targetRequestCode)
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (resultCode == Activity.RESULT_OK && requestCode == targetRequestCode){
             binding.newShoe?.imageUri=data?.data // handle chosen image
         }
     }

     private fun isPermissionsAllowed(): Boolean {
         return (ContextCompat.checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)

     }

     fun askForPermissions(): Boolean {
         if (!isPermissionsAllowed()) {
             if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),READ_EXTERNAL_STORAGE)) {
                 showPermissionDeniedDialog()
             } else {
                 ActivityCompat.requestPermissions(requireActivity(),arrayOf(READ_EXTERNAL_STORAGE),targetRequestCode)
             }
             return false
         }
         return true
     }

     override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>,grantResults: IntArray) {
         when (requestCode) {
             targetRequestCode -> {
                 if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     // permission is granted, you can perform your operation here
                 } else {

                 }
                 return
             }
         }
     }

     private fun showPermissionDeniedDialog() {
         AlertDialog.Builder(context)
             .setTitle("Permission Denied")
             .setMessage("Permission is denied, Please allow permissions from App Settings.")
             .setPositiveButton("App Settings",
                 DialogInterface.OnClickListener { dialogInterface, i ->
                     // send to app settings if permission is denied permanently
                     val intent = Intent()
                     intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                     val uri = Uri.fromParts("package", context?.packageName, null)
                     intent.data = uri
                     startActivity(intent)
                 })
             .setNegativeButton("Cancel",null)
             .show()
     }






 }