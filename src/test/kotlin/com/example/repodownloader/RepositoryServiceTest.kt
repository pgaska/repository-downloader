package com.example.repodownloader

import com.example.repodownloader.model.BranchDto
import com.example.repodownloader.model.RepositoryDto
import com.example.repodownloader.model.ResultDto
import com.example.repodownloader.model.github.BranchResponseDto
import com.example.repodownloader.model.github.CommitDto
import com.example.repodownloader.model.github.OwnerDto
import com.example.repodownloader.model.github.RepoResponseDto
import com.example.repodownloader.service.GithubClient
import com.example.repodownloader.service.RepositoryService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RepositoryServiceTest {
	private final val githubClient: GithubClient = mockk()
	private val repositoryService: RepositoryService = RepositoryService(githubClient)

	@Test
	fun whenGetUserRepositories_thenReturnRepositories() {
		val resultDto = ResultDto(
			listOf(
				RepositoryDto(
					name = "test",
					login = "testUser",
					branches = listOf(BranchDto("main", "8345674t37588"))
				)
			)
		)
		val repoResponseDto = RepoResponseDto(
			"test",
			OwnerDto("testUser")
		)
		every { githubClient.getRepositories("testUser") } returns mutableListOf(repoResponseDto)

		val branchResponseDto = BranchResponseDto(
			"main",
			CommitDto("8345674t37588")
		)
		every { githubClient.getBranch("test", "testUser") } returns mutableListOf(branchResponseDto)

		val result = repositoryService.getUserRepositories("testUser")
		assert(result == resultDto)
	}

}
