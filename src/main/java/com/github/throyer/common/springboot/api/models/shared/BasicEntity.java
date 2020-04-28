package com.github.throyer.common.springboot.api.models.shared;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BasicEntity {

    public static final String NON_DELETED_CLAUSE = "deleted_at IS NULL";
    public static final String SET_ALL_DELETED_SQL = "UPDATE #{#entityName} SET\ndeleted_at = CURRENT_TIMESTAMP\n";
    public static final String SET_DELETED_SQL = SET_ALL_DELETED_SQL + "WHERE id = ?1";

    public abstract Long getId();

    @CreationTimestamp
    @JsonInclude(Include.NON_NULL)
    @Column(name = "created_at")
    @JsonFormat(shape = Shape.STRING)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonInclude(Include.NON_NULL)
    @Column(name = "updated_at")
    @JsonFormat(shape = Shape.STRING)
    private LocalDateTime updatedAt;

    @JsonInclude(Include.NON_NULL)
    @Column(name = "deleted_at")
    @JsonFormat(shape = Shape.STRING)
    private LocalDateTime deletedAt;

    @Column(name = "active", nullable = false)
    private Boolean ativo = true;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}