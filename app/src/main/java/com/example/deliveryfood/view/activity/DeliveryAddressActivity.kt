package com.example.deliveryfood.view.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Gravity.BOTTOM
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryfood.R
import com.example.deliveryfood.databinding.ActivityDeliveryAdressBinding
import com.example.deliveryfood.databinding.DeliveryAddressListBinding
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import com.hyewon.deliveryfood.vo.Delivery_Address
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject

class DeliveryAddressActivity : Activity() {

    private val TAG = "DeliveryAddressActivity"

    private lateinit var viewDataBinding : ActivityDeliveryAdressBinding

    private val layoutResouceId : Int
        get() = R.layout.activity_delivery_adress

    private val viewmodel : DeliveryAddressViewModel by inject()

    private lateinit var addressListadapter : DeliveryAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = ActivityDeliveryAdressBinding.inflate(layoutInflater)
        activityPopupSetting()
        setContentView(viewDataBinding.root)

        updateUI()

        viewDataBinding.apply {
            this.addNewAddress.setOnClickListener { addNewAddress() }
        }
    }

    private fun activityPopupSetting(){
        window.apply {
            attributes = WindowManager.LayoutParams().apply {
                this.gravity = BOTTOM
            }
        }
    }

    private fun updateUI(){
        val list = viewmodel.selectUserAddress("okewon").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                addressListadapter = DeliveryAddressAdapter()
                viewDataBinding.deliveryAddressList.adapter = addressListadapter
                it.forEach {
                    addressListadapter.addUserAddress(it)
                }
            }, {
                Log.d(TAG, "error occured!!")
            })
    }

    private fun addNewAddress(){
        Toast.makeText(this, "ADD NEW ADDRESS!", Toast.LENGTH_LONG).show()
    }

    private inner class DeliveryAddressAdapter() : RecyclerView.Adapter<DeliveryAddressAdapter.AddressHolder>() {

        private lateinit var listBinding : DeliveryAddressListBinding
        private val list = mutableListOf<Delivery_Address>()

        fun addUserAddress(address : Delivery_Address){
            list.add(address)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
            listBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.delivery_address_list, parent, false)
            return AddressHolder(listBinding)
        }

        override fun onBindViewHolder(holder: AddressHolder, position: Int) {
            holder.apply {
                setUserAddress(list[position])
                itemView.setOnClickListener{
                    val clickedItem = list[position]
                    viewmodel.changeDeliveryAddress(clickedItem.MEM_ID, clickedItem.DELIVERY_ADDRESS_MAIN_ADDRESS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.d(TAG, it.toString())
                        },{
                            Log.d("FAIL", it.toString())
                        })
                }
            }
        }

        override fun getItemCount() = list.size

        private inner class AddressHolder(val binding : DeliveryAddressListBinding) : RecyclerView.ViewHolder(binding.root){

            fun setUserAddress(userAddress : Delivery_Address) {
                binding.apply {
                    address.text =
                        "${userAddress.DELIVERY_ADDRESS_MAIN_ADDRESS}  ${userAddress.DELIVERY_ADDRESS_SUB_ADDRESS}"
                    defaultDeliveryAddress.visibility =
                        if (userAddress.DEFAULT_ADDRESS) View.VISIBLE else View.GONE
                    if (userAddress.SENDING_PHONE_NUMBER == null && userAddress.SENDING_PERSON_NAME == null) {
                        sendingPersonName.visibility = View.GONE
                        sendingPhoneNumber.visibility = View.GONE
                        divideBar.visibility = View.GONE
                    } else {
                        sendingPersonName.text = userAddress.SENDING_PERSON_NAME
                        sendingPhoneNumber.text = userAddress.SENDING_PHONE_NUMBER
                    }
                    nowSelectedAddress.visibility =
                        if (userAddress.SELECTED_ADDRESS) View.VISIBLE else View.GONE
                    possibleDeliveryType.text = userAddress.DELIVERY_TYPE
                }
            }
        }
    }

}