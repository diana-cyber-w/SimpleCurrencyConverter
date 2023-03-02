package com.example.currencyconverter.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.interactors.CurrencyInteractor
import com.example.currencyconverter.domain.model.CurrencyModel
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyInteractor: CurrencyInteractor) : ViewModel() {

    val conversation: LiveData<CurrencyModel> get() = _conversation
    private val _conversation = MutableLiveData<CurrencyModel>()

    fun getCurrencyConversation(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            _conversation.value = currencyInteractor.getCurrencyConversion(from, to, amount)
        }
    }
}