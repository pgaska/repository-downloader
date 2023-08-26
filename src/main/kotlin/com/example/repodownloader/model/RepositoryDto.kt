package com.example.repodownloader.model

data class RepositoryDto(
    val name: String,
    val login: String,
    val branches: List<BranchDto>?
)
