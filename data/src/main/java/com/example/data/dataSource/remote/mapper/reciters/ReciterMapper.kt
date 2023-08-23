package com.example.data.dataSource.remote.mapper.reciters

import com.example.data.dataSource.remote.mapper.Mapper
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.domain.entity.reciters.ReciterEntity


object ReciterMapper : Mapper<Reciter, ReciterEntity> {

    override fun map(input: Reciter): ReciterEntity {
        return ReciterEntity(
            id = input.id,
            moshafEnitity = input.moshaf.map { moshaf ->
                MoshafEnitity(
                    reciterName = input.name,
                    id = moshaf.id,
                    moshaf_type = moshaf.moshaf_type,
                    name = moshaf.name,
                    server = moshaf.server,
                    surah_list = moshaf.surah_list,
                    surah_total = moshaf.surah_total
                )
            },
            name = input.name,
            isExpandable = false
        )
    }

    fun mapToReciterEntityList(reciterList: List<Reciter>): List<ReciterEntity> {
        return reciterList.map {
            map(it)
        }
    }

}