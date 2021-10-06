package com.medibank.shop.usecase

import com.medibank.shop.dto.SourcesDto
import com.medibank.shop.model.NewsData
import com.medibank.shop.model.SourcedsData
import com.medibank.shop.repository.INewsRepository
import com.medibank.shop.utils.orDefault
import javax.inject.Inject


interface INewsUseCase {
    suspend fun getNews(sourceId: String): List<SourcedsData>
    suspend fun getSources(): List<SourcedsData>
}

/**
 * Responsible for all astronauts related business logic
 */
class NewsUseCaseImpl @Inject constructor(private val repo: INewsRepository) : INewsUseCase {

    override suspend fun getNews(sourceId: String): List<SourcedsData> {
        return repo.getNews(sourceId).map { newsDto ->
            SourcedsData(
                newsDto.source,
                newsDto.author.orDefault(),
                newsDto.title.orDefault(),
                newsDto.description.orDefault(),
                newsDto.urlToImage.orDefault(),
                newsDto.url.orDefault(),
                newsDto.publishedAt.orDefault(),
                newsDto.content.orDefault(),
                newsDto.isSelected,
                newsDto.isSaved
            )
        }
    }

    override suspend fun getSources(): List<SourcedsData> {
        return repo.getSources().map { sourcesDto ->
            SourcedsData(
                source = SourcesDto(sourcesDto.source?.id, sourcesDto.source?.name),
                sourcesDto.author,
                sourcesDto.title,
                sourcesDto.description,
                sourcesDto.urlToImage,
                sourcesDto.url,
                sourcesDto.publishedAt,
                sourcesDto.content,
                sourcesDto.isSelected,
                sourcesDto.isSaved
            )
        }
    }
}