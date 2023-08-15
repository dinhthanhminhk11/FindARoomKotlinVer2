package com.example.findaroomkotlinver2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.data.model.post.Post
import com.example.findaroomkotlinver2.data.util.TimeUtils
import com.example.findaroomkotlinver2.databinding.ItemPostHomeBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class PostAdapter(private val data: List<Post>, private val callback: Callback) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private val fm: NumberFormat = DecimalFormat("#,###")
    private val options =
        RequestOptions().centerCrop().placeholder(R.drawable.noimage).error(R.drawable.noimage)
//    private var callback: Callback? = null
//
//    fun setCallback(callback: Callback) {
//        this.callback = callback
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPostHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        if (item != null) {
//            repository.getUserById(item.idUser, Consumer { data ->
//                holder.binding.nameUser.text = data.fullName
//                Glide.with(holder.binding.imageUser.context)
//                    .load(data.image)
//                    .apply(optionsUser)
//                    .into(holder.binding.imageUser)
//            })
//
//            repository.getCountFavouriteByIdPost(item.id, Consumer { countFavourite ->
//                holder.binding.heart.text = countFavourite.data.size.toString()
//            })
//
//            repository.getListCommentParent(item.id, Consumer { commentListResponse ->
//                holder.binding.commentCount.text = commentListResponse.data.size.toString()
//            })

            holder.binding.title.text = item.title
            holder.binding.price.text = fm.format(item.price) + " VND"
            holder.binding.content.text = item.describe
            holder.binding.time.text = TimeUtils.getTimeAgo(item.timeLong)

            holder.binding.container.setOnClickListener {
                callback.onClickItem(item)
            }

            when (item.images.size) {
                1 -> {
                    Glide.with(holder.binding.contentImage1Imag1.context).load(item.images[0])
                        .apply(options).into(holder.binding.contentImage1Imag1)
                    holder.binding.contentImage1.visibility = View.VISIBLE
                    holder.binding.contentImage2.visibility = View.GONE
                    holder.binding.contentImage3.visibility = View.GONE
                    holder.binding.contentImage4.visibility = View.GONE
                }

                2 -> {
                    Glide.with(holder.binding.contentImage2Image1.context).load(item.images[0])
                        .apply(options).into(holder.binding.contentImage2Image1)
                    Glide.with(holder.binding.contentImage2Image2.context).load(item.images[1])
                        .apply(options).into(holder.binding.contentImage2Image2)
                    holder.binding.contentImage1.visibility = View.GONE
                    holder.binding.contentImage2.visibility = View.VISIBLE
                    holder.binding.contentImage3.visibility = View.GONE
                    holder.binding.contentImage4.visibility = View.GONE
                }

                3 -> {
                    Glide.with(holder.binding.contentImage3Image1.context).load(item.images[0])
                        .apply(options).into(holder.binding.contentImage3Image1)
                    Glide.with(holder.binding.contentImage3Image2.context).load(item.images[1])
                        .apply(options).into(holder.binding.contentImage3Image2)
                    Glide.with(holder.binding.contentImage3Image3.context).load(item.images[2])
                        .apply(options).into(holder.binding.contentImage3Image3)
                    holder.binding.contentImage1.visibility = View.GONE
                    holder.binding.contentImage2.visibility = View.GONE
                    holder.binding.contentImage3.visibility = View.VISIBLE
                    holder.binding.contentImage4.visibility = View.GONE
                }

                else -> {
                    Glide.with(holder.binding.contentImage4Image1.context).load(item.images[0])
                        .apply(options).into(holder.binding.contentImage4Image1)
                    Glide.with(holder.binding.contentImage4Image2.context).load(item.images[1])
                        .apply(options).into(holder.binding.contentImage4Image2)
                    Glide.with(holder.binding.contentImage4Image3.context).load(item.images[2])
                        .apply(options).into(holder.binding.contentImage4Image3)
                    Glide.with(holder.binding.contentImage4Image4.context).load(item.images[3])
                        .apply(options).into(holder.binding.contentImage4Image4)
                    if (item.images.size > 4) {
                        holder.binding.countImage.visibility = View.VISIBLE
                        holder.binding.countImage.text = "+" + (item.images.size - 4)
                        holder.binding.checkImage4.setBackgroundResource(R.drawable.gradient_bg)
                    } else {
                        holder.binding.countImage.visibility = View.GONE
                        holder.binding.checkImage4.background = null
                    }
                    holder.binding.contentImage1.visibility = View.GONE
                    holder.binding.contentImage2.visibility = View.GONE
                    holder.binding.contentImage3.visibility = View.GONE
                    holder.binding.contentImage4.visibility = View.VISIBLE
                }
            }
            holder.binding.btnHeart.setImageResource(R.drawable.ic_heart_item_post_animation_no_select)

//            val token = MySharedPreferences.getInstance(holder.itemView.context)
//                .getString(AppConstant.USER_TOKEN, "")
//            repository.getFavouriteByIdUserAndIdPost(UserClient.getInstance().getId(),
//                item.id,
//                Consumer { favouriteResponse ->
//                    if (!token.isEmpty()) {
//                        if (favouriteResponse.favourite != null) {
//                            holder.binding.btnHeart.isSelected = true
//                            val stateSet = intArrayOf(
//                                android.R.attr.state_checked * if (holder.binding.btnHeart.isSelected) 1 else -1
//                            )
//                            holder.binding.btnHeart.setImageState(stateSet, true)
//                        } else {
//                            holder.binding.btnHeart.isSelected = false
//                            val stateSet = intArrayOf(
//                                android.R.attr.state_checked * if (holder.binding.btnHeart.isSelected) 1 else -1
//                            )
//                            holder.binding.btnHeart.setImageState(stateSet, true)
//                        }
//                    }
//                })

            holder.binding.btnHeart.setOnClickListener {
//                if (!token.isEmpty()) {
                holder.binding.btnHeart.isSelected = !holder.binding.btnHeart.isSelected
                val stateSet = intArrayOf(
                    android.R.attr.state_checked * if (holder.binding.btnHeart.isSelected) 1 else -1
                )
                holder.binding.btnHeart.setImageState(stateSet, true)

                if (!holder.binding.btnHeart.isSelected) {
                    // xoá tim
                    holder.binding.heart.text =
                        (Integer.parseInt(holder.binding.heart.text.toString()) - 1).toString()
//                        repository.deleteFavourite(UserClient.getInstance().getId(),
//                            item.id,
//                            Consumer { favouriteResponse -> })
                } else {
                    // add tim
                    holder.binding.heart.text =
                        (Integer.parseInt(holder.binding.heart.text.toString()) + 1).toString()
//                        repository.addFavourite(Favourite(
//                            UserClient.getInstance().getId(), item.id
//                        ), Consumer { favouriteResponse -> })
                }
//                } else {
//                    initDiaLog(holder.itemView.context)
//                }
            }

//            holder.itemView.setOnClickListener { callback?.onClickItem(item) }

//            holder.binding.btnMore.setOnClickListener {
//                if (!token.isEmpty()) {
//                    val bottomsheetBookmark = BottomSheetBookmark(
//                        it.context, UserClient.getInstance().getId(), item.id, repository
//                    )
//                    bottomsheetBookmark.show()
//                } else {
//                    initDiaLog(holder.itemView.context)
//                }
//            }
//
//            holder.binding.heart.setOnClickListener {
//                if (Integer.parseInt(holder.binding.heart.text.toString()) > 0) {
//                    val bottomSheetPersonFavourite = BottomSheetPersonFavourite(
//                        it.context, item.id, repository
//                    )
//                    bottomSheetPersonFavourite.show()
//                }
//            }
//
//            holder.binding.comment.setOnClickListener {
//                val intent = Intent(holder.binding.comment.context, CommentActivity::class.java)
//                intent.putExtra(AppConstant.ID_POST, item.id)
//                holder.binding.comment.context.startActivity(intent)
//            }
//
//            holder.binding.profile.setOnClickListener {
//                val intent = Intent(it.context, ProfileActivity::class.java)
//                intent.putExtra(AppConstant.ID_USER, item.idUser)
//                it.context.startActivity(intent)
//            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemPostHomeBinding) : RecyclerView.ViewHolder(binding.root)

//    private fun initDiaLog(context: Context) {
//        val dialogConfirmLogin = Dialog(context)
//        dialogConfirmLogin.setContentView(R.layout.dialog_comfirm_no_login)
//        val window2: Window? = dialogConfirmLogin.window
//        window2?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        if (dialogConfirmLogin != null && dialogConfirmLogin.window != null) {
//            dialogConfirmLogin.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }
//        dialogConfirmLogin.findViewById<View>(R.id.login).setOnClickListener {
//            dialogConfirmLogin.dismiss()
//            context.startActivity(Intent(context, LoginActivity::class.java))
//        }
//        dialogConfirmLogin.show()
//    }

    interface Callback {
        fun onClickItem(item: Post)
        fun onClickMore(item: Post)
        fun onClickComment(item: Post)
        fun onClickProfile(item: Post)
    }
}