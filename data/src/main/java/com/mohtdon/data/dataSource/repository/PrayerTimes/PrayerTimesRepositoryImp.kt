package com.mohtdon.data.dataSource.repository.PrayerTimes

import android.os.Build
import androidx.annotation.RequiresApi
import com.mohtdon.data.dataSource.remote.mapper.DayPrayerTimesMapper
import com.mohtdon.data.dataSource.remote.mapper.MonthPrayerTimesMapper
import com.mohtdon.data.dataSource.remote.service.PrayerTimesService
import com.mohtdon.domain.models.prayerTimes.MonthPrayerTimes
import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes
import com.mohtdon.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class PrayerTimesRepositoryImp @Inject constructor(
    private val prayerTimesService: PrayerTimesService
) : PrayerTimesRepository {

    lateinit var dayPrayerTimesMapper: DayPrayerTimesMapper
    lateinit var monthPrayerTimesMapper: MonthPrayerTimesMapper

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes {


        val response = prayerTimesService.getDayPrayerTimes(date, latitude, longitude).body()
        dayPrayerTimesMapper = DayPrayerTimesMapper()
        if (response != null) {
            dayPrayerTimesMapper = DayPrayerTimesMapper()
            return dayPrayerTimesMapper.map(response)
        } else {
            throw RuntimeException("Prayer times response is null.")
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getMonthPrayerTimes(
        month: Int, year: Int, latitude: Double, longitude: Double
    ): MonthPrayerTimes {

        monthPrayerTimesMapper = MonthPrayerTimesMapper()

//        Log.i("ahmed", "getMonthPrayerTimes 1 ")
        val response = prayerTimesService.getMonthPrayerTimes(
            month, year, latitude, longitude
        ).body()
//            .body()
//        Log.i("ahmed", "getMonthPrayerTimes 2 ")
//        Log.i("ahmed", response.body()?.code.toString())
        return monthPrayerTimesMapper.map(response!!)
    }
}