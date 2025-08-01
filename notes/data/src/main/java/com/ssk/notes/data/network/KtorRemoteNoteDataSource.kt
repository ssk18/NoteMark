package com.ssk.notes.data.network

import com.ssk.core.data.Routes
import com.ssk.core.data.network.constructRoute
import com.ssk.core.data.network.get
import com.ssk.core.data.network.post
import com.ssk.core.domain.DataError
import com.ssk.core.domain.Result
import com.ssk.core.domain.SessionStorage
import com.ssk.core.domain.map
import com.ssk.core.domain.notes.Note
import com.ssk.core.domain.notes.PaginatedNotes
import com.ssk.core.domain.notes.RemoteNotesDataSource
import io.ktor.client.HttpClient

class KtorRemoteNoteDataSource(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : RemoteNotesDataSource {
    override suspend fun postNote(note: Note): Result<Note, DataError.Network> {
        val result =
            httpClient.post<NoteRequestDto, NoteResponseDto>(
                route = constructRoute(Routes.CREATE_NOTE),
                body = note.toNoteRequestDto()
            )

        return result.map {
            it.toNote()
        }
    }

    override suspend fun getNotes(page: Int, size: Int): Result<PaginatedNotes, DataError.Network> {
        val queryParams = mutableMapOf<String, String>()
        
        if (page != -1) {
            queryParams["page"] = page.toString()
            queryParams["size"] = size.toString()
        }
        
        val result = httpClient.get<NotesListResponseDto>(
            route = Routes.GET_NOTES,
            queryParams = queryParams
        )

        return result.map { response ->
            PaginatedNotes(
                notes = response.notes.map { it.toNote() },
                total = response.total
            )
        }
    }

    override suspend fun logout(): Result<Unit, DataError.Network> {
        val refreshToken = sessionStorage.getRefreshToken()
        val result = httpClient.post<String?, Unit>(
            route = constructRoute(Routes.LOGOUT),
            body = refreshToken
        )
        return result
    }
}