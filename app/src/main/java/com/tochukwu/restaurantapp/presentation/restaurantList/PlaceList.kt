package com.tochukwu.restaurantapp.presentation.restaurantList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tochukwu.restaurantapp.R
import com.tochukwu.restaurantapp.databinding.PlaceFragmentBinding
import com.tochukwu.restaurantapp.util.Status
import com.tochukwu.restaurantapp.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlaceList(
    var placeAdapter: RestaurantAdapter,
    var viewModel: PlaceViewModel? = null
) : Fragment(R.layout.place_fragment) {

    val TAG = "PlaceList"
    private var _binding: PlaceFragmentBinding? = null

    private val binding get() =  _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlaceFragmentBinding.inflate(inflater, container, false)

        return _binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)

        setUpRecyclerView()
        subscribeToObserve()


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.btn.setOnClickListener{
            searchRestaurants()
        }
    }


    private fun searchRestaurants(){
        hideKeyboard()
        if(binding.editTxt.text.toString().isBlank()){
            Toast.makeText(activity, "Input Postal Code", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel?.getRestaurants(binding.editTxt.text.toString())

    }



    private fun subscribeToObserve(){

        viewModel?.restaurantChannel?.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let{result ->
                when(result.status){
                    Status.SUCCESS->{
                        val data = result.data
                        placeAdapter.submitList(data)
                        binding.progressBar.visibility = View.INVISIBLE
                    }

                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    Status.ERROR ->{
                        result.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                        binding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(activity, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        binding.searchrv.apply{

            adapter = placeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
        }
    }

}
