package com.example.todo.presentation.common

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData

class ObservableLiveData<T : BaseObservable> : MutableLiveData<T> {

    constructor(): super()

    constructor(value: T) : super(value)

    private val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            this@ObservableLiveData.value = value
        }
    }

    override fun setValue(value: T?) {
        super.setValue(value)

        value?.addOnPropertyChangedCallback(callback)
    }

    init {
        setValue(value)
    }
}