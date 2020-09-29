package com.haag.news_app

import androidx.lifecycle.LifecycleOwner
import com.haag.news_app.repo.NewsRepository
import com.haag.news_app.room.Read
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Before


class ExampleUnitTest {

    private lateinit var repo: NewsRepository
    lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setup() {
        lifecycleOwner = mockk()
        repo = mockk()
    }

    @Test
    fun `create_getTitleList()_mock`() {
        var readList = mutableListOf<Read>()
        var read = Read("Dutch news")
        readList.add(read)

        coEvery { repo.getTitleList() } returns readList
        runBlocking { repo.getTitleList() }
        coVerify { repo.getTitleList() }
    }
}