package com.example.testmvvm

import io.reactivex.subjects.PublishSubject

object RxEvent {
    val bus = PublishSubject.create<String>()

    fun sendEvent(str: String) {
        bus.onNext(str)
    }
}