package com.springbootmvcwithentity.demo.entity.extand;

import javax.persistence.*;

@Entity
@Table(name = "operatingsystems")
public class OperatingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "os_type")
    private String osType;

    public OperatingSystem(String osType) {
        this.osType = osType;
    }

    public OperatingSystem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "id=" + id +
                ", osType='" + osType + '\'' +
                '}';
    }
    // Constructors, getters, and setters
}
