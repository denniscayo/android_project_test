package com.latinos.mobiletest.features.character.paged

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.latinos.domain.characters.model.CharacterModel
import com.latinos.mobiletest.R
import com.latinos.mobiletest.databinding.ItemCharacterBinding
import com.latinos.mobiletest.features.base.BasePagingAdapter

class CharacterAdapter : BasePagingAdapter<CharacterModel, ItemCharacterBinding>(
    diffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

    }
) {
    var clickListener: ((CharacterModel) -> Unit)? = null
//    override fun createBinding(parent: ViewGroup): ItemCharacterBinding {
//        val binding = DataBindingUtil.inflate<ItemCharacterBinding>(
//            LayoutInflater.from(parent.context),
//            R.layout.item_character,
//            parent,
//            false
//        )
//
//        binding.root.setOnClickListener {
//            binding.model?.let {
//                repoClickCallback?.invoke(it)
//
//            }
//        }
//
//
//        return binding
//    }

    override val itemViewType = R.layout.item_character

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemCharacterBinding =
        ItemCharacterBinding::inflate

    override fun bind(binding: ItemCharacterBinding, item: CharacterModel, position: Int) {
        binding.model = item
        binding.root.setOnClickListener {
            binding.model?.let {
                clickListener?.invoke(item)

            }
        }
    }
}