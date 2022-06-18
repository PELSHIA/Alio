package com.project.alio.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.project.alio.R
import com.project.alio.databinding.FragmentMissionQuoteBinding
import java.util.*

class MissionQuoteFragment : Fragment() {

    private lateinit var binding: FragmentMissionQuoteBinding
    private val quoteList = resources.getStringArray(R.array.array_quote)
    private val quoteNameList = resources.getStringArray(R.array.array_quote_name)
    private val random = Random().nextInt(quoteList.size)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionQuoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingQuote()
        comparisonQuote()
    }

    private fun settingQuote() {
        binding.alarmQuoteText.text = quoteList[random]
        binding.alarmQuoteEditText.hint = quoteList[random]
        binding.alarmQuotePeople.text = quoteNameList[random]
    }

    private fun comparisonQuote() {
        binding.alarmQuoteCheck.setOnClickListener {
            val writeQuote = binding.alarmQuoteEditText.text.toString()
            if (writeQuote == quoteList[random]) {
                activity?.finish()
            } else {
                Toast.makeText(activity, "다시 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}