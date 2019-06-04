package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
public class Altitude {

	private String value;

	public static boolean isValid(String altitude) {
		return true; // TODO implement real validation method for altitude
	}

}
