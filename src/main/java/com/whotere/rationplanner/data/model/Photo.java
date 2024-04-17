package com.whotere.rationplanner.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class Photo extends BaseEntity {

    @Lob
    private byte[] content;

    @OneToOne(mappedBy = "photo")
    private Product product;

    @OneToOne(mappedBy = "photo")
    private Meal meal;
}
