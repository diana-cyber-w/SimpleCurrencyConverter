package com.example.currencyconverter.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyConverterLayoutBinding
import com.example.currencyconverter.presentation.viewModel.CurrencyViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CurrencyConverterFragment : Fragment(R.layout.currency_converter_layout) {

    private val binding: CurrencyConverterLayoutBinding by viewBinding(
        CurrencyConverterLayoutBinding::bind
    )
    private val viewModel: CurrencyViewModel by sharedViewModel()
    private var baseCurrency = "BYN"
    private var convertedCurrency = "USD"
    private var convertedAmount = -1.0
    private var prevConvertedAmount = -1.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinner()
    }

    override fun onResume() {
        super.onResume()
        binding.firstCurrencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                baseCurrency = parent?.getItemAtPosition(position).toString()
                binding.firstMoneyLine.setText(EMPTY_LINE)
                binding.secondMoneyLine.setText(EMPTY_LINE)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                baseCurrency = "BYN"
            }
        }

        binding.secondCurrencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                convertedCurrency = parent?.getItemAtPosition(position).toString()
                binding.firstMoneyLine.setText(EMPTY_LINE)
                binding.secondMoneyLine.setText(EMPTY_LINE)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                convertedCurrency = "USD"
            }
        }
        binding.firstMoneyLine.doAfterTextChanged { firstLineText ->
            if (!firstLineText.isNullOrEmpty()) {
                if (convertedAmount != firstLineText.toString().toDouble()) {
                    viewModel.getCurrencyConversation1(
                        baseCurrency,
                        convertedCurrency,
                        firstLineText.toString().toDouble()
                    )
                    getCurrencyConversation1()
                } else {
                    convertedAmount = -1.0
                }
            }
        }
        binding.secondMoneyLine.doAfterTextChanged { secondLineText ->
            if (!secondLineText.isNullOrEmpty()) {
                if (convertedAmount != secondLineText.toString().toDouble()) {
                    viewModel.getCurrencyConversation2(
                        convertedCurrency,
                        baseCurrency,
                        secondLineText.toString().toDouble()
                    )
                    getCurrencyConversation2()
                } else {
                    convertedAmount = -1.0
                }
            }
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency1,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.firstCurrencySpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency2,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.secondCurrencySpinner.adapter = adapter
        }
    }

    private fun getCurrencyConversation1() {
        viewModel.conversation1.observe(viewLifecycleOwner) { conversation ->
            if (conversation.status == SUCCESS_STATUS) {
                convertedAmount =
                    conversation.rates?.values?.toList()?.get(0)?.rate_for_amount ?: -1.0
                if (prevConvertedAmount != convertedAmount) {
                    prevConvertedAmount = convertedAmount
                    binding.secondMoneyLine.setText(
                        conversation.rates?.values?.toList()?.get(0)?.rate_for_amount.toString()
                    )
                }
            }
        }
    }

    private fun getCurrencyConversation2() {
        viewModel.conversation2.observe(viewLifecycleOwner) { conversation ->
            if (conversation.status == SUCCESS_STATUS) {
                convertedAmount =
                    conversation.rates?.values?.toList()?.get(0)?.rate_for_amount ?: -1.0
                if (prevConvertedAmount != convertedAmount) {
                    prevConvertedAmount = convertedAmount
                    binding.firstMoneyLine.setText(
                        conversation.rates?.values?.toList()?.get(0)?.rate_for_amount.toString()
                    )
                }
            }
        }
    }

    companion object {
        private const val SUCCESS_STATUS = "success"
        private const val EMPTY_LINE = ""
    }
}