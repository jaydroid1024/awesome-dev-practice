package com.jay.biz_android.memory.oom

/**
 * @author jaydroid
 * @version 1.0
 * @date 2021/10/8
 */
class ReplaceEnum {

}

fun main() {
    setJob(Job.TEACHER)
}

fun setJob(job: Job) {
    if (job == Job.TEACHER) {
        println("teacher")
    }
}

@JvmInline
value class Job(val value: Int) {
    companion object {
        val TEACHER = Job(0)
    }
}