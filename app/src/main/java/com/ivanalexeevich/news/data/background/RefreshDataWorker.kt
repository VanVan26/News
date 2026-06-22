package com.ivanalexeevich.news.data.background

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ivanalexeevich.news.domain.usecase.GetSettingsUseCase
import com.ivanalexeevich.news.domain.usecase.UpdateArticlesForAllSubscriptionsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val updateArticlesForAllSubscriptionsUseCase: UpdateArticlesForAllSubscriptionsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val notificationsHelper: NotificationsHelper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val settings = getSettingsUseCase().first()
        val updatedTopics =  updateArticlesForAllSubscriptionsUseCase()
        if(updatedTopics.isNotEmpty() && settings.notificationsEnabled){
            notificationsHelper.showNewArticlesNotification(updatedTopics)
        }


        return Result.success()
    }
}