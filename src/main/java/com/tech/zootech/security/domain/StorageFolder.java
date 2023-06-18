package com.tech.zootech.security.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "storage_folder")
@NamedEntityGraph(name = "StorageFolder.files",
        attributeNodes = {@NamedAttributeNode("files")})
public class StorageFolder extends AbstractEntity {

    private String name;

    private boolean required;

    private boolean rootDirectory;

    private String path;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<StorageFolder> folders;

    @ManyToOne
    private StorageFolder parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<StorageFile> files;
}
