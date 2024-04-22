package com.template.apptemplate.controllers

import com.template.apptemplate.models.TemplateModel
import com.template.apptemplate.services.TemplateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/template")
class TemplateController(
    private val service: TemplateService
) {

    @GetMapping
    fun getAll(): List<TemplateModel> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getItem(@PathVariable("id") id: Long): TemplateModel {
        return service.getById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody model: TemplateModel): TemplateModel {
        return service.save(model)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<TemplateModel> {
        service.remove(id)
        return ResponseEntity<TemplateModel>(HttpStatusCode.valueOf(204))
    }

}