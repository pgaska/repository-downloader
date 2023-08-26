package com.example.repodownloader.model.github

import com.fasterxml.jackson.annotation.JsonProperty

data class BranchResponseDto(
    val name: String,
    @JsonProperty("commit") val commit: CommitDto
)

data class CommitDto(
    val sha: String
)