package de.exxellent.challenge.dataAccessLayer.modul;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {

    @Id
    private Integer day;
    private Double mxt;
    private Double mnt;

    public Weather(Integer day, Double mxt, Double mnt) {
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

    public Double getMxt() {
        return mxt;
    }

    public void setMxt(Double mxt) {
        this.mxt = mxt;
    }

    public Double getMnt() {
        return mnt;
    }

    public void setMnt(Double mnt) {
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
