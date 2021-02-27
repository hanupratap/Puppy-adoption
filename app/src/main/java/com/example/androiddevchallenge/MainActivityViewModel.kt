package com.example.androiddevchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.Dog
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _puppies: MutableLiveData<List<Dog>> = MutableLiveData()

    val livedogs : LiveData<List<Dog>> get() = _puppies

    init {
        viewModelScope.launch {
            _puppies.value =
                listOf(
                    Dog(1, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(2, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(3, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(4, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(5, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(6, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(7, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(8, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                    Dog(9, "Tommy", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "https://th.bing.com/th/id/R6c101d1e27bdad589f2224c771312bfa?rik=isoNyTjWLe3GCA&riu=http%3a%2f%2fweneedfun.com%2fwp-content%2fuploads%2f2015%2f10%2fCute-puppy-Pictures-29.jpg&ehk=c4Pri2hAYt5j%2b1ZUn%2bq04AcxiTD1gA6Nve6HmotswRE%3d&risl=&pid=ImgRaw"),
                )
        }
    }


    fun getDogByID(id:Int): Dog? {
        if(livedogs.value==null)
            return null

            livedogs.value!!.map {
                if(it.id == id)
                    return it
            }

            return null
    }

}