package com.demo.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Optional;

public class AwareAuditConfig implements Auditable {

    @Override
    public Optional getCreatedBy() {
        return Optional.empty();
    }

    @Override
    public void setCreatedBy(Object createdBy) {

    }

    @Override
    public Optional getCreatedDate() {
        return Optional.of(new Date());
    }

    @Override
    public void setCreatedDate(TemporalAccessor creationDate) {

    }

    @Override
    public Optional getLastModifiedBy() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedBy(Object lastModifiedBy) {

    }

    @Override
    public Optional getLastModifiedDate() {
        return Optional.empty();
    }

    @Override
    public void setLastModifiedDate(TemporalAccessor lastModifiedDate) {

    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
