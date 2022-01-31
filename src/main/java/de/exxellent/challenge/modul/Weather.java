package de.exxellent.challenge.modul;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {

    @Id
    private Integer day;
    private Integer mxt;
    private Integer mnt;

    public Weather(Integer day, Integer mxt, Integer mnt) {
        this.day = day;
        this.mxt = mxt;
        this.mnt = mnt;
    }

    public Weather() {
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMxt() {
        return mxt;
    }

    public void setMxt(Integer mxt) {
        this.mxt = mxt;
    }

    public Integer getMnt() {
        return mnt;
    }

    public void setMnt(Integer mnt) {
        this.mnt = mnt;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "day=" + day +
                ", mxt=" + mxt +
                ", mnt=" + mnt +
                '}';
    }
}
