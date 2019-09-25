package com.demo.event.demoevent.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class User {

   @Size(max = 4,min = 2)
    String name;

}
