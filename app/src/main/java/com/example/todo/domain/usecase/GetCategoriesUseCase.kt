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

        val netData = networkRepository.getCategories(tempToken)
        val dbData = dbRepository.getCategories().toMutableList()
        val data = mutableListOf<Category>()

        if (netData == null) return dbData

        netData.forEach { netCategory ->
            val dbCategory = dbData.find { it.id == netCategory.id }
            dbData.remove(dbCategory)

            if (dbCategory == null) dbRepository.addCategory(netCategory)
            else if (CompareUtil.compareCategory(netCategory, dbCategory)) {
                if (netCategory.synchronized != dbCategory.synchronized)
                {
                    dbCategory.synchronized = true
                    dbRepository.updateCategory(dbCategory)
                }
            }
            else {
                if (!dbCategory.synchronized) {
                    networkRepository.createCategory(tempToken, dbCategory)
                }
                else dbRepository.updateCategory(netCategory)
            }

            data.add(netCategory)
        }

        dbData.forEach {
            if (!it.synchronized) {
                networkRepository.createCategory(tempToken, it)
                data.add(it)
            }
        }

        return data
    }
}