package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import org.jsoup.select.Evaluator.IsNthChild

class CardFragment : Fragment() {

    private var _binding: FragmentCardBinding? = null

    private val binding get() = _binding!!

    private val card by lazy { arguments?.getSerializable(CARD_CARD_KEY) as? CardUiModel }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.card_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                sharePlayerText()
                true
            }

            else -> true
        }
    }

    private fun sharePlayerText() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, card.toString())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        card?.let { card ->
            binding.name.text = card.formattedName
            binding.price.text = card.formattedPrice
            binding.frequencyValue.text = card.formattedFrequency
            binding.powerValue.text = card.formattedPower
            binding.memoryValue.text = card.formattedMemory

            Glide
                .with(this)
                .load(card.photoUrl)
                .into(binding.icon)

            binding.heightCard.text = card.formattedheightCard
            binding.lengthCard.text = card.formattedlengthCard
            binding.widthCard.text = card.formattedwidthCard
            binding.additionalPower.text = card.formattedadditionalPower
            binding.fan.text = card.formattedfan
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CARD_CARD_KEY = "CARD"
    }
}