package com.fatalzero.rsandroidfinal_task

import com.fatalzero.rsandroidfinal_task.data.network.model.Flags
import com.fatalzero.rsandroidfinal_task.data.network.model.JokeNet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

object JokeGenerator {
    private val counter = AtomicInteger(0)

    fun getFakeJoke(id: Int): JokeNet {
        return JokeNet(
            "delivery $id",
            Flags(
                Random.nextBoolean(),
                Random.nextBoolean(),
                Random.nextBoolean(),
                Random.nextBoolean(),
                Random.nextBoolean(),
                Random.nextBoolean()
            ),
            Random.nextBoolean(),
            null,
            "jokes $id",
            id,
            "category $id",
            "type $id",
            "lang"
        )
    }
}
