package com.sample.cloud.customers.resource;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.sample.cloud.customers.CustomersApplication;

import lombok.Data;

@Data
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = CustomersApplication.APPLICATION_VERSION;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	/*
	 * TODO find out if this is still needed in spite of SerializationFeature.WRITE_DATES_AS_TIMESTAMPS.
	 * If removed, we get errors when POSTing or PUTing date values.
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateOfBirth;
}
