package com.tech.zootech.security.domain;

import com.tech.zootech.security.domain.enums.FileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "storage_file")
public class StorageFile extends AbstractEntity {

    private String name;

    private String path;

    private boolean isArchived;

    @ManyToOne
    private StorageFolder parent;

    @Transient
    private FileStatus status;
}
