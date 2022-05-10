package com.project.alio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Alarm
import com.example.domain.useCase.*
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
    private val updateAlarmUseCase: UpdateAlarmUseCase,
    private val selectAlarmUseCase: SelectAlarmUseCase
) : BaseViewModel() {

    private val _alarmList = MutableLiveData<List<Alarm>>()
    val alarmList: LiveData<List<Alarm>>
        get() = _alarmList

    private val _alarm = MutableLiveData<Alarm>()
    val alarm: LiveData<Alarm>
        get() = _alarm

    private val _alarmId = MutableLiveData<Long>()
    val alarmId: LiveData<Long>
        get() = _alarmId

    fun allAlarmList() {
        compositeDisposable.add(
            getAllAlarmListUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        _alarmList.postValue(it)
                    }
                }, {
                    Log.d("Error", it.message.toString())
                })
        )
    }

    fun insertAlarm(alarm: Alarm) {
        compositeDisposable.add(
            insertAlarmUseCase.execute(alarm).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe({
                    _alarmId.postValue(it)
                }, {
                    Log.d("Error", it.message.toString())
                })
        )
    }

    fun deleteAlarm(alarm: Alarm) {
        compositeDisposable.add(
            deleteAlarmUseCase.execute(alarm).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }

    fun updateAlarm(alarm: Alarm) {
        compositeDisposable.add(
            updateAlarmUseCase.execute(alarm).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }

    fun selectAlarm(id: Int) {
        compositeDisposable.add(
            selectAlarmUseCase.execute(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe({
                    _alarm.postValue(it)
                }, {})
        )
    }
}