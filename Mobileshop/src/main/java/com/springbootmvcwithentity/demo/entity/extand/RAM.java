package com.springbootmvcwithentity.demo.entity.extand;

import javax.persistence.*;

@Entity
@Table(name = "ram")
public class RAM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "capacity")
    private String capacity;

    public RAM() {
    }

    public RAM(String capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "id=" + id +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}