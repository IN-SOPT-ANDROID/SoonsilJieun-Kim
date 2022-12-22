package org.sopt.sample.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.databinding.ItemHomeBinding
import org.sopt.sample.data.model.response.ResponseMusicDto

class MusicAdapter(Item: List<ResponseMusicDto.Data>, context: Context) : RecyclerView.Adapter<MusicAdapter.RepoViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<ResponseMusicDto.Data> = emptyList()
    lateinit var Binding: ItemHomeBinding

    class RepoViewHolder(
        private val binding: ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMusicDto.Data) {
            Glide.with(binding.root)
                .load(data.image)
                .circleCrop()
                .into(binding.ivGit)
            binding.tvContent.text = data.title
            binding.tvName.text = data.singer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        Binding = ItemHomeBinding.inflate(inflater, parent, false)
        return RepoViewHolder(Binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount() = repoList.size

    fun setRepoList(repolist: List<ResponseMusicDto.Data>) {
        this.repoList = repolist.toList() // 원본이 바뀌어도 문제가 생기지 않도록 "얇은 복사" 처리
        // notifyDataSetChanged() // 새로운 데이터셋을 인식시켜 이를 기반으로 다시 리사이클러 뷰를 그리도록 함
        notifyItemRangeChanged(0, repoList.size)
    }
}
