package com.covid.cohelp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private long postId;
    private String description;
    private String owner_name;
    private long owner_mob;
    private String owner_email;
    private String location;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "item_id")
    private Item item;

    private String item_name;

}
