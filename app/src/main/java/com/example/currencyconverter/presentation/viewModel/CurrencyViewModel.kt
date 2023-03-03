package com.example.currencyconverter.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.interactors.CurrencyInteractor
import com.example.currencyconverter.domain.model.CurrencyModel
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyInteractor: CurrencyInteractor) : ViewModel() {

    val conversation1: LiveData<CurrencyModel> get() = _conversation1
    private val _conversation1 = MutableLiveData<CurrencyModel>()

    val conversation2: LiveData<CurrencyModel> get() = _conversation2
    private val _conversation2 = MutableLiveData<CurrencyModel>()

    fun getCurrencyConversation1(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            _conversation1.value = currencyInteractor.getCurrencyConversion(from, to, amount)
        }
    }

    fun getCurrencyConversation2(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            _conversation2.value = currencyInteractor.getCurrencyConversion(from, to, amount)
        }
    }
}