package com.springbootmvcwithentity.demo.ClassSuport;

import com.springbootmvcwithentity.demo.entity.Phones;

import java.util.List;

public class SeriMissing {
    private Phones phone;
    private int missing;

    public SeriMissing() {
    }

    public SeriMissing(Phones phone, int missing) {
        this.phone = phone;
        this.missing = missing;
    }

    public Phones getPhone() {
        return phone;
    }

    public void setPhone(Phones phone) {
        this.phone = phone;
    }

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    @Override
    public String toString() {
        return "SeriMissing{" +
                "phone=" + phone +
                ", missing=" + missing +
                '}';
    }
}
