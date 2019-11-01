package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Category
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.domain.utils.CompareUtil

class GetCategoriesUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<List<Category>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Category>? {
        val tempToken = token ?: return null

        val dbData = dbRepository.getCategories().toMutableList()
        val data = mutableListOf<Category>()

        dbData.filter { !it.synchronized }.forEach {
            networkRepository.createCategory(tempToken, it)
        }

        val netData = networkRepository.getCategories(tempToken) ?: return dbData

        dbRepository.clearCategories()

        netData.forEach { netCategory ->
            dbRepository.addCategory(netCategory)
            data.add(netCategory)
        }

        return data
    }
}