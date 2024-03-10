package com.example.mobiledevelopmentcourselabapp.presentation.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.mobiledevelopmentcourselabapp.R
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentListBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.adapter.CardAdapter
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.decorator.VerticalSpaceItemDecorator
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.generator.Generator
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.model.CardUiModel
import com.example.mobiledevelopmentcourselabapp.utils.dpToPx
import com.example.mobiledevelopmentcourselabapp.utils.orZero


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val adapter by lazy { CardAdapter(::onCardClicked) }

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
        adapter.updateItems(Generator.generate())
        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)
        binding.cardList.addItemDecoration(dividerItemDecoration)
        binding.cardList.addItemDecoration(VerticalSpaceItemDecorator(context?.dpToPx(30).orZero()))

    }

    private fun onCardClicked(card: CardUiModel) {
        val bundle = bundleOf(CardFragment.CARD_CARD_KEY to card)
        view?.findNavController()?.navigate(R.id.action_navigation_list_to_cardFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}