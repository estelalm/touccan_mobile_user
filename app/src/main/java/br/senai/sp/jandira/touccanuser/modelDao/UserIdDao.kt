package br.senai.sp.jandira.touccanuser.modelDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_usuario")
data class UserIdDao(
    @PrimaryKey(autoGenerate = false) val id: Int = 0
)
