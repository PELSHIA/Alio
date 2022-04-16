package com.project.alio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Alarm
import com.example.domain.useCase.DeleteAlarmUseCase
import com.example.domain.useCase.GetAllAlarmListUseCase
import com.example.domain.useCase.InsertAlarmUseCase
import com.example.domain.useCase.UpdateAlarmUseCase
import com.project.alio.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val getAllAlarmListUseCase: GetAllAlarmListUseCase,
    private val insertAlarmUseCase: InsertAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase,
    private val updateAlarmUseCase: UpdateAlarmUseCase
) : BaseViewModel() {

    private val _alarmList = MutableLiveData<List<Alarm>>()
    val alarmList: LiveData<List<Alarm>>
        get() = _alarmList

    fun allAlarmList() {
        compositeDisposable.add(
            getAllAlarmListUseCase.execute().subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        _alarmList.value = it
                    }
                }, {
                    Log.d("Error", it.message.toString())
                })
        )
    }

    fun insertAlarm(alarm: Alarm) {
        compositeDisposable.add(
            insertAlarmUseCase.execute(alarm).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }

    fun deleteAlarm(alarm: Alarm) {
        compositeDisposable.add(
            deleteAlarmUseCase.execute(alarm).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }

    fun updateAlarm(alarm: Alarm) {
        compositeDisposable.add(
            updateAlarmUseCase.execute(alarm).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }
}