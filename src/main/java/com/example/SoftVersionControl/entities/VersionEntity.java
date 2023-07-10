package com.example.SoftVersionControl.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "versions")
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "version")
    private String version;

    @Column(name = "blacklist")
    private Boolean blacklist;

    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private ModuleEntity module;

    public VersionEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Boolean blacklist) {
        this.blacklist = blacklist;
    }

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }

}
