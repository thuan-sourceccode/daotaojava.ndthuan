package com.javaspring.appserver.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="note")
public class noteEntity extends baseEntity {

	private String note;
}
