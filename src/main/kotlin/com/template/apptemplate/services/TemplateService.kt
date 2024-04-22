package com.template.apptemplate.services

import com.template.apptemplate.exceptions.NotFoundException
import com.template.apptemplate.models.TemplateModel
import com.template.apptemplate.repository.TemplateRepository
import org.springframework.stereotype.Service

@Service
class TemplateService(
    private val repository: TemplateRepository
) {

    fun getAll(): List<TemplateModel> {
        return repository.findAll()
    }

    fun getById(id: Long): TemplateModel {
        return repository.findById(id).orElseThrow {
            throw NotFoundException()
        }
    }

    fun save(model: TemplateModel): TemplateModel {
        return repository.save(model)
    }

    fun remove(id: Long) {
        val data = getById(id)
        return repository.delete(data)
    }
}