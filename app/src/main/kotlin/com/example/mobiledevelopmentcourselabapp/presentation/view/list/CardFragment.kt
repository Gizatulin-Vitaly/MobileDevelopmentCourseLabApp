package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.utils.orFalse
import com.google.android.material.snackbar.Snackbar
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

    private fun visibleHiddenGroup() {
        binding.comments.hiddenGroup.isVisible = binding.comments.hiddenGroup.isVisible.not()
        if (binding.comments.hiddenGroup.isVisible) {
            binding.comments.chevron.rotation = DEGREE_OF_ROTATION
        } else {
            binding.comments.chevron.rotation = NOT_ROTATION
        }
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

        binding.comments.sendButton.isEnabled = false



        binding.comments.commentTitle.setOnClickListener {
            visibleHiddenGroup()
        }
        binding.comments.chevron.setOnClickListener {
            visibleHiddenGroup()
        }

        binding.comments.commentInput.doOnTextChanged { text, _, _, _ ->
            binding.comments.sendButton.isEnabled = text?.isNotBlank().orFalse()
        }

        binding.comments.sendButton.setOnClickListener {
            Snackbar.make(
                requireContext(),
                binding.root,
                binding.comments.commentInput.text.toString(),
                Snackbar.LENGTH_LONG
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CARD_CARD_KEY = "CARD"
        const val DEGREE_OF_ROTATION = 180f // Угол поворота 180
        const val NOT_ROTATION = 0f
    }
}