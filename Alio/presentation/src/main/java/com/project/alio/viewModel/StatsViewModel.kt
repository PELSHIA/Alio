package com.project.alio.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Stats
import com.example.domain.useCase.DeleteAllStatsUseCase
import com.example.domain.useCase.GetAllStatsListUseCase
import com.example.domain.useCase.InsertStatsUseCase
import com.project.alio.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val getAllStatsListUseCase: GetAllStatsListUseCase,
    private val insertStatsUseCase: InsertStatsUseCase,
    private val deleteAllStatsUseCase: DeleteAllStatsUseCase
) : BaseViewModel() {

    private val _statsList = MutableLiveData<List<Stats>>()
    val statsList: LiveData<List<Stats>>
        get() = _statsList

    fun allStatsList() {
        compositeDisposable.add(
            getAllStatsListUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe({
                    if (it.isNotEmpty()) {
                        _statsList.postValue(it)
                    }
                }, {
                    Log.d("Error", it.message.toString())
                })
        )
    }

    fun insertStats(stats: Stats) {
        compositeDisposable.add(
            insertStatsUseCase.execute(stats).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }

    fun deleteAllStats(stats: Stats) {
        compositeDisposable.add(
            deleteAllStatsUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading() }
                .doAfterTerminate { stopLoading() }
                .subscribe()
        )
    }
}