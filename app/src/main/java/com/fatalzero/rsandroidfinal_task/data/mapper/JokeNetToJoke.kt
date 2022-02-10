package com.fatalzero.rsandroidfinal_task.data.mapper

import com.fatalzero.rsandroidfinal_task.data.database.JokeDbModel
import com.fatalzero.rsandroidfinal_task.data.network.model.JokeNet
import com.fatalzero.rsandroidfinal_task.domain.model.Joke

class JokeMapper {

    companion object {
        private fun jokeNetToJoke(jokeNet: JokeNet) = Joke(
            joke = jokeNet.setup ?: jokeNet.joke,
            id = jokeNet.id,
            category = jokeNet.category
        )

        fun mapJsonContainerToListJoke(listJokeNet: List<JokeNet>): List<Joke> {
            val result = mutableListOf<Joke>()
            for (joke in listJokeNet) {
                result.add(jokeNetToJoke(joke))
            }
            return result
        }

        fun jokeToJokeDbModel(joke: Joke?) = JokeDbModel(joke?.joke, joke?.id, joke?.category)

        fun jokeDbModelToJoke(jokeDbModel: JokeDbModel?) = Joke(jokeDbModel?.joke, jokeDbModel?.id, jokeDbModel?.category)


    }

}
