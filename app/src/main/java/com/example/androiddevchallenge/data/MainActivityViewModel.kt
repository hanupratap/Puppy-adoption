/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _puppies: MutableLiveData<List<Dog>> = MutableLiveData()

    private val LOREM: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. \n\n " +
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

    val livedogs: LiveData<List<Dog>> get() = _puppies

    init {
        viewModelScope.launch {
            _puppies.value =
                listOf(
                    Dog(9, false, "Tom", "I like sleeping", LOREM, "https://th.bing.com/th/id/Rba5c720bcdcf23e9b85cb1b893f152b4?rik=sXoHXh48N7iyyQ&riu=http%3a%2f%2fdogletter.com%2fimages%2fnewborn-puppy-see.jpg&ehk=cStTxIqYdEn9huqja75isb2QBPbvBed8L1VwikA5ZfU%3d&risl=&pid=ImgRaw", "Bernese Mountain Dog", 0),
                    Dog(10, false, "Thomson", "Waddup Hooman!", LOREM, "https://th.bing.com/th/id/R29af8ec63f445b3abe4893ec36bcca0b?rik=bYOSTdY12c9R%2bg&riu=http%3a%2f%2ffarm1.staticflickr.com%2f78%2f196036497_f5f090e6c0.jpg&ehk=JLkq6Lo%2fwL709%2bkCMFiJSfXF1Zhn39ln9EywVOrY0dk%3d&risl=&pid=ImgRaw", "Scotch Collie", 4),
                    Dog(1, true, "Amos", "Howdy Hooman!!", LOREM, "https://www.readersdigest.ca/wp-content/uploads/2013/03/owning-a-puppy-means-more-patience.jpg", "Labrador Retriever", 2),
                    Dog(2, false, "Richie", "I like richie rich", LOREM, "https://www.thelabradorsite.com/wp-content/uploads/2018/03/how-to-play-with-your-puppy.jpg", "Golden Retriever", 3),
                    Dog(3, false, "Raul", "I am a distinguished gentleman", LOREM, "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw", "Bernese Mountain Dog", 3),
                    Dog(4, false, "Shera", "I love playing basketball", LOREM, "https://th.bing.com/th/id/R3f67ddb98a957cc9e9821a15735be193?rik=jEY%2b2ro%2buUP17Q&riu=http%3a%2f%2fipadwallpapergallery.com%2fuploads%2fBogdan_Suditu_Puppy.jpg&ehk=Qgz4t6Njve2sVgUJae9WSuIaINgYgOMTuh6VQcSoxq4%3d&risl=&pid=ImgRaw", "Scotch Collie", 3),
                    Dog(5, false, "Hriday", " I love messing aroung", LOREM, "https://th.bing.com/th/id/R2c04e238e2a4a02c61368794210d1fbe?rik=IZ%2f3zZYolLT1mQ&riu=http%3a%2f%2fimages2.fanpop.com%2fimage%2fphotos%2f9700000%2fSad-Puppy-puppies-9726248-1600-1200.jpg&ehk=kwUQRKHCc2t7102LpRI6R1VUQUNvCiHy7wgo6iEubT0%3d&risl=&pid=ImgRaw", "Great Dane", 2),
                    Dog(6, true, "Alice", "Hi there!", LOREM, "https://th.bing.com/th/id/OIP.ocLNql3tT9Ns14hGBfU0cQHaHa?pid=ImgDet&rs=1", "Great Dane", 1),
                    Dog(7, true, "Abby", "I am the cutest :)", LOREM, "https://media.istockphoto.com/photos/yorkie-newborn-picture-id186836160?k=6&m=186836160&s=612x612&w=0&h=y-YpevhPU-MRzWJXm9ylHC7leyfpGCT-9VfxapyrXDM=", "Newfoundland", 0),
                    Dog(8, false, "Bugs Bunny", "I love carrots", LOREM, "https://marissahein.files.wordpress.com/2013/02/dog_laughing.jpg", "Newfoundland", 1),
                )
        }
    }

    fun getDogByID(id: Int): Dog? {
        if (livedogs.value == null)
            return null

        livedogs.value!!.map {
            if (it.id == id)
                return it
        }

        return null
    }
}
