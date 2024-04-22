package com.template.apptemplate.repository

import com.template.apptemplate.models.TemplateModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository: JpaRepository<TemplateModel, Long> {
}