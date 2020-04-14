package ru.naumen.compass.entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

public class Ride {
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Integer views;
    private Boolean isValid;
}
