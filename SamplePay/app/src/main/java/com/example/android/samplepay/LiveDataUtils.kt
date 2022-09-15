/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.samplepay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <Input1, Input2, Return> combine(
    input1: LiveData<Input1>,
    input2: LiveData<Input2>,
    body: (Input1?, Input2?) -> Return
): LiveData<Return> {
    return MediatorLiveData<Return>().apply {
        var cache1: Input1? = null
        var cache2: Input2? = null
        fun update() {
            value = body(cache1, cache2).also { println(it) }
        }
        addSource(input1) { cache1 = it; update() }
        addSource(input2) { cache2 = it; update() }
    }
}
