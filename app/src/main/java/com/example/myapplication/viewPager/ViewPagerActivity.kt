package com.example.myapplication.viewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {

    val binding by lazy { ActivityViewPagerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //1. 페이지 데이터를 로드
        val list = listOf(FragmentA(), FragmentB(), FragmentC())
        //2. 어댑터 생성
        val pagerAdapter = FragmentPagerAdapter(list, this)
        //3. 어댑터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdapter
        //4. 탭 메뉴의 개수만큼 제목을 목록으로 생성
        val titles = listOf("A","B","C")
        //5. 탭레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = titles.get(position)
        }.attach()
    }
}