package com.example.SoftVersionControl.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "blackList")
public class BlackListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private ModuleEntity module;

    @Column(name = "version")
    private String version;

    public BlackListEntity() {
    }

    public Integer getId() {
        return id;
    }

    public ModuleEntity getModule() {
        return module;
    }

    public void setModule(ModuleEntity module) {
        this.module = module;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
