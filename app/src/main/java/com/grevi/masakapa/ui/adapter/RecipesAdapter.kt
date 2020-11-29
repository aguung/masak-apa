package com.grevi.masakapa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grevi.masakapa.R
import com.grevi.masakapa.model.Recipes
import com.grevi.masakapa.util.Listenear
import kotlinx.android.synthetic.main.list_main_recipes.view.*
import kotlinx.android.synthetic.main.lists_recipes.view.*
import kotlinx.android.synthetic.main.lists_recipes.view.imgThumb
import kotlinx.android.synthetic.main.lists_recipes.view.recipesTitle

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesVH>() {

    private val recipes : MutableList<Recipes> = mutableListOf()
    private var listenear : Listenear? = null

    inner class RecipesVH(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(recipes : Recipes) {
            with(itemView) {
                Glide.with(this.context).load(recipes.imageThumb).placeholder(R.drawable.placeholder).into(imgThumb)
                recipesTitleMain.text = recipes.name
                dificultyTextMain.text = recipes.dificulty
                portionTextMain.text = recipes.portion
                timesTextMain.text = recipes.times
                this.setOnClickListener { listenear?.onItemSelected(recipes) }
            }
        }
    }

    internal fun itemRecipes(listenear: Listenear) {
        this.listenear = listenear
    }

    fun addItem(item : MutableList<Recipes>) {
        notifyDataSetChanged()
        recipes.clear()
        recipes.addAll(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_main_recipes, parent, false)
        return RecipesVH(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipesVH, position: Int) {
        holder.bind(recipes[position])
    }
}