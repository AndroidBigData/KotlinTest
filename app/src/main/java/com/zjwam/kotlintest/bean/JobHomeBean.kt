package com.zjwam.kotlintest.bean

class JobHomeBean {
    private var count: Int = 0
    private var resume: List<Resume>? = null

    fun getCount(): Int {
        return count
    }

    fun getResume(): List<Resume>? {
        return resume
    }

    inner class Resume {
        var job_name: String? = null
        var create_time: String? = null
        var company_name: String? = null
        var salary: String? = null
        var area: String? = null
        var type: String? = null
        var benefit: List<Benefit>? = null
        var id: Long = 0
        var hold_id: Long = 0
    }

    inner class Benefit {
        var type: String? = null
    }
}