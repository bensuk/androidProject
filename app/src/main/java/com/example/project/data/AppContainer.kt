package com.example.project.data
//
//import android.content.Context
//import com.example.project.user.UserRepository
//
//interface AppContainer {
//    val userRepository: UserRepository
//}
//
//
//class AppDataContainer(private val context: Context) : AppContainer {
//    override val userRepository: UserRepository by lazy {
//        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
//    }
//}