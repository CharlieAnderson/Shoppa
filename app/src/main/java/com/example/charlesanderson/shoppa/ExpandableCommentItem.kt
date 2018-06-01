//package com.example.charlesanderson.shoppa
//
//import android.view.LayoutInflater
//import android.view.View
//import com.xwray.groupie.ExpandableGroup
//import com.xwray.groupie.ExpandableItem
//import com.xwray.groupie.Item
//import com.xwray.groupie.ViewHolder
//
//open class ExpandableCommentItem constructor(
//        private val comment: Comment,
//        private val depth: Int) : Item<ViewHolder>(), ExpandableItem {
//
//    private lateinit var expandableGroup: ExpandableGroup
//
//    private fun addingDepthViews(viewHolder: ViewHolder) {
//        viewHolder.itemView.separatorContainer.removeAllViews()
//        viewHolder.itemView.separatorContainer.visibility =
//                if (depth > 0) {
//                    View.VISIBLE
//                } else {
//                    View.GONE
//                }
//        for (i in 1..depth) {
//            val view : View = LayoutInflater.from(viewHolder.itemView.context)
//                    .inflate(R.layout.separator_view, viewHolder.itemView.separatorContainer, false)
//            viewHolder.itemView.separatorContainer.addView(view)
//        }
//    }
//
//    override fun bind(viewHolder: ViewHolder, position: Int) {
//        addingDepthViews(viewHolder)
//        viewHolder.itemView.tv_user.setText(comment.author)
//        viewHolder.itemView.body.setText(comment.body)
//        viewHolder.itemView.apply {
//            setOnLongClickListener {
//                expandableGroup.onToggleExpanded()
//                true
//            }
//        }
//    }
//
//    override fun getLayout(): Int {
//        return R.layout.item_expandable_comment
//    }
//
//    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
//        this.expandableGroup = onToggleListener
//    }
//
//})
//
