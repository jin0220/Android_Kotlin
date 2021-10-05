package com.example.myapplication.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerContentBinding
import java.text.SimpleDateFormat

class Adapter(val listDate:MutableList<Memo>): RecyclerView.Adapter<Adapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { //화면에 보여질 아이템을 만들어줌
        val binding = RecyclerContentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        //1.사용할 데이터를 꺼내고
        val memo = listDate.get(position)
        //2.홀더에 데이터를 전달
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int = listDate.size

    class Holder(val binding:RecyclerContentBinding):RecyclerView.ViewHolder(binding.root){
        lateinit var currentMemo: Memo
        //클릭처리는 init에서만 한다.
        init {
            binding.root.setOnClickListener{
                Toast.makeText(binding.root.context, "클릭된 아이템: ${currentMemo.title}", Toast.LENGTH_SHORT).show()
            }
        }

        //3.받은 데이터를 화면에 출력한다.
        fun setMemo(memo: Memo){
            currentMemo = memo
            with(binding) {
                no.text = "${memo.no}"
                title.text = "${memo.title}"

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val formattedDate = sdf.format(memo.timestamp)
                date.text = formattedDate
            }
        }
    }
}