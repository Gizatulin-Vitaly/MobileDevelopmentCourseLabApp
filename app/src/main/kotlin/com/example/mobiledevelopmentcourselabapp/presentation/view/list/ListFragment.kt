package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter.CardAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.ItemUiModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.presenter.ListPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Provider
import javax.inject.Inject

class ListFragment : MvpAppCompatFragment(), ListMvpView {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { CardAdapter(presenter::onCardClicked) }

    @Inject
    lateinit var presenterProvider: Provider<ListPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardList.adapter = adapter
    }

    override fun navigateToCard(card: CardUiModel) {
        val bundle = bundleOf(CardFragment.CARD_CARD_KEY to card)
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_cardFragment, bundle)
    }

    override fun showCards(cards: List<ItemUiModel>) {
        adapter.updateItems(cards)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        onDetach()
    }
}