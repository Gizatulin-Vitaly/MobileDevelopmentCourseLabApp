package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentCardBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter.CommentsAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.CardPresenter
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CardFragment : MvpAppCompatFragment(), CardMvpView {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val card by lazy { arguments?.getSerializable(CARD_CARD_KEY) as? CardUiModel }
    private val adapter by lazy { CommentsAdapter() }

    @Inject
    lateinit var presenterProvider: Provider<CardPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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

            Glide.with(this).load(card.photoUrl).into(binding.icon)

            binding.heightCard.text = card.formattedheightCard
            binding.lengthCard.text = card.formattedlengthCard
            binding.widthCard.text = card.formattedwidthCard
            binding.additionalPower.text = card.formattedadditionalPower
            binding.fan.text = card.formattedfan
        }

        binding.comments.commentsList.adapter = adapter

        binding.comments.sendButton.setOnClickListener {
            presenter.onSendButtonClicked()
            binding.comments.commentInputLayout.setText("")
        }

        presenter.onCommentChanged(binding.comments.commentInputLayout.text)

        binding.comments.commentTitle.setOnClickListener {
            presenter.onCommentsTitleClicked()
        }

        binding.comments.chevron.setOnClickListener {
            presenter.onCommentsTitleClicked()
        }

        binding.comments.commentInputLayout.doOnTextChanged { text, _, _, _ ->
            presenter.onCommentChanged(text)
        }

    }

    override fun setHiddenGroupVisibility(isVisible: Boolean) {
        binding.comments.hiddenGroup.isVisible = isVisible
    }

    override fun setCommentChevronIcon(rotation: Float) {
        binding.comments.chevron.rotation = rotation
    }

    override fun setSendButtonEnabled(isEnabled: Boolean) {
        binding.comments.sendButton.isEnabled = isEnabled
    }

    override fun setMessageError(error: String) {
        binding.comments.commentInputLayout.error = error
    }

    override fun addComment(comment: String) {
        adapter.addComment(comment)
    }

    override fun showSnackbar() {
        Snackbar.make(
            requireContext(),
            binding.root,
            "Сообщение отправлено",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun setCommentText(text: String) {
        binding.comments.commentInputLayout.setText(text)
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