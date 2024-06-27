package core.database.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun encode(value: LocalDateTime): String {
        return value.toString()
    }

    @TypeConverter
    fun decode(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }
}
