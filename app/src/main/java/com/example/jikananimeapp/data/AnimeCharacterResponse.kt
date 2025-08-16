package com.example.jikananimeapp.data

data class AnimeCharacterResponse(
    val data: List<CharacterRoleDto>
)

data class CharacterRoleDto(
    val character: CharacterDto?,
    val voice_actors: List<VoiceActorDto>?
)

data class CharacterDto(
    val name: String?
)

data class VoiceActorDto(
    val person: PersonDto?,
    val language: String?
)

data class PersonDto(
    val name: String?
)